package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemService;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.ShoppinglistItemMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository.ShoppinglistItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class ShoppinglistItemServiceImpl implements ShoppinglistItemService {

    @Autowired
    private ItemUnitService itemUnitService;

    @Autowired
    private CalculateSystemService calculateSystemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Autowired
    private ShoppinglistItemRepository shoppinglistItemRepository;

    @Autowired
    private ShoppinglistItemMapper shoppinglistItemMapper;

    private final static Logger logger = LoggerFactory.getLogger(ShoppinglistItemServiceImpl.class);

    @Override
    public ShoppinglistItemDTO createShoppinglistItem(
            RequestCreateShoppinglistItem requestCreateShoppinglistItem,
            Long idShoppinglist) throws ShoppinglistItemException {
        logger.info("Creating a new shoppinglist item");
        try {
            ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
            Shoppinglist shoppinglist = shoppinglistService.findShoppinglistById(idShoppinglist);
            logger.info("Shoppinglist with id {} finded", shoppinglist.getId());
            Product product = null;
            if(requestCreateShoppinglistItem.getProductId() == null) {
                logger.info("Creating new product with name {}", requestCreateShoppinglistItem.getProductName());
                product = productService.createProduct(requestCreateShoppinglistItem.getProductName());
            } else {
                logger.info("The product with name {} already exists", requestCreateShoppinglistItem.getProductName());
                product = productService.findProductByName(requestCreateShoppinglistItem.getProductName());
            }
            if(product != null) {
                shoppinglistItem.setName(product.getName());
            }
            shoppinglistItem.setAssignationToListDate(new Date());
            shoppinglistItem.setProducts(new HashSet<Product>());
            shoppinglistItem.getProducts().add(product);
            CalculateSystem calculateSystem = null;
            if(requestCreateShoppinglistItem.getCalculateSystemCode() != null) {
                logger.info("The calculate system with id {} is going to be assigned", requestCreateShoppinglistItem.getCalculateSystemCode());
                if(shoppinglistItem.getCalculateSystems() != null) {
                    calculateSystem = calculateSystemService.findCalculateSystemByCode(requestCreateShoppinglistItem.getCalculateSystemCode());
                    shoppinglistItem.getCalculateSystems().add(calculateSystem);
                } else {
                    shoppinglistItem.setCalculateSystems(new HashSet<>());
                    calculateSystem = calculateSystemService.findCalculateSystemByCode(requestCreateShoppinglistItem.getCalculateSystemCode());
                    shoppinglistItem.getCalculateSystems().add(calculateSystem);
                }
            }
            ItemUnit itemUnit = itemUnitService.createItemUnit();
            itemUnit.setUnitPrice(
                    calculateSystemService.calculateProductPrice(shoppinglistItem.getCalculateSystems().stream().toList().get(0), product)
            );
            shoppinglistItem.setItemUnits(new HashSet<>());
            shoppinglistItem.getItemUnits().add(itemUnit);
            shoppinglistItem.setCalculatedPrice(itemUnit.getUnitPrice());
            shoppinglistItem = shoppinglistItemRepository.save(shoppinglistItem);
            itemUnitService.assignShoppinglistItemToItemUnit(shoppinglistItem, itemUnit);
            shoppinglistService.addItemsToShoppinglist(shoppinglist, shoppinglistItem);
            productService.assignShoppinglistItemToProduct(shoppinglistItem, product);
            calculateSystemService.assignShoppinglistItemToCalculateSystem(shoppinglistItem, calculateSystem);
            ShoppinglistItemDTO shoppinglistItemDTO = shoppinglistItemMapper.shoppinglistItemToShoppinglistItemDTO(shoppinglistItem);
            return shoppinglistItemDTO;
        } catch (ShoppinglistException | ProductException | CalculateSystemException e) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
        }
    }
}
