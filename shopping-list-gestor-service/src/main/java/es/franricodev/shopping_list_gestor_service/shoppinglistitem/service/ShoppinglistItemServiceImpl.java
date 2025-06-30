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

import java.util.*;

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
            // TODO: Obtener la lista de la compra
            Shoppinglist shoppinglist = shoppinglistService.findShoppinglistById(idShoppinglist);
            // TODO: Obtener el producto, SI NO EXISTE SE CREA (ya se crea implicitamente en el metodo findProductByName)
            Product product = null;
            if (requestCreateShoppinglistItem.getProductName() == null) {
                throw new ProductException("PRODUCT_MESSAGE_ERROR_NO-SE-PUEDE-CREAR-EL-PRODUCTO");
            } else {
                product = productService.findProductByName(requestCreateShoppinglistItem.getProductName());
            }
            // TODO: Obtener el calculate system;
            CalculateSystem calculateSystem = null;
            if(requestCreateShoppinglistItem.getCalculateSystemCode() == null) {
                throw new CalculateSystemException("CALCULATE_MESSAGE_ERROR-NO-SE-PUEDE-OBTENER-EL-CALCULATE-SYSTEM");
            } else {
                calculateSystem = calculateSystemService.findCalculateSystemByCode(requestCreateShoppinglistItem.getCalculateSystemCode());
            }

            // TODO: Creamos el shoppinglist item
            ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
            shoppinglistItem.setName(product.getName());
            shoppinglistItem.setCalculatedPrice(0D);
            shoppinglistItem.setAssignationToListDate(new Date());

            List<ShoppinglistItem> shoppinglistItemList = shoppinglist.getItems();
            shoppinglistItemList.add(shoppinglistItem);
            shoppinglist.setItems(shoppinglistItemList);

            shoppinglistItem.setCalculateSystem(calculateSystem);

            shoppinglistItem =  shoppinglistItemRepository.save(shoppinglistItem);
            // TODO: Crear el item unit
            ItemUnit itemUnit = itemUnitService.createItemUnit(shoppinglistItem, requestCreateShoppinglistItem.getUnitaryPrice());

            productService.assignProductToShoppinglistItem(shoppinglistItem, product);

            return shoppinglistItemMapper.shoppinglistItemToShoppinglistItemDTO(shoppinglistItem);

        } catch (ShoppinglistException | ProductException | CalculateSystemException e) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
        }
    }

    @Override
    public void deleteShoppinglistItem(Long idItem) throws ShoppinglistItemException {
        logger.info("Deleting the shoppinglist item with id: {}", idItem);
        Optional<ShoppinglistItem> optShoppinglistItem = shoppinglistItemRepository.findById(idItem);
        if (optShoppinglistItem.isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
        }

        // TODO: Primero debo eliminar las relaciones existentes entre producto y shoppinglist items
        ShoppinglistItem shoppinglistItem = optShoppinglistItem.get();
        for(Product p : shoppinglistItem.getProducts()) {
            p.getShoppinglistItems().remove(shoppinglistItem);
        }
        shoppinglistItem.getProducts().clear();
        shoppinglistItemRepository.delete(shoppinglistItem);
    }

    @Override
    public void addItemUnitToShoppinglistItem(Long idItem, Double unitaryPrice, Integer quantity) throws ShoppinglistItemException {
        logger.info("Add a new item unit to the shoppinglist item: {}", idItem);
        Optional<ShoppinglistItem> optionalShoppinglistItem = shoppinglistItemRepository.findById(idItem);
        if (optionalShoppinglistItem.isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
        }
        ShoppinglistItem shoppinglistItem = optionalShoppinglistItem.get();
        for(int i = 0; i < quantity; i++) {
            itemUnitService.createItemUnit(shoppinglistItem, unitaryPrice);
        }
        // TODO: Aqui seria interesante llamar a un funcion que se encarge de recalcular el precio total de la shoppinglist
        recalculateShoppinglistItemsTotalPrice(shoppinglistItem);
    }

    private void recalculateShoppinglistItemsTotalPrice(ShoppinglistItem shoppinglistItem) {
        Double totalShoppinglistPrice = 0D;
        for(ItemUnit itemUnit : shoppinglistItem.getItemUnitList()) {
            totalShoppinglistPrice += itemUnit.getUnitPrice();
        }
        shoppinglistItem.setCalculatedPrice(totalShoppinglistPrice);
        shoppinglistItemRepository.save(shoppinglistItem);
    }
}
