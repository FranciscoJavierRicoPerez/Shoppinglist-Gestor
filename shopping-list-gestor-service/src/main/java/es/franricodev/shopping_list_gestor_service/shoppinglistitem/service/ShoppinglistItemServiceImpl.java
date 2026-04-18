package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemService;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.response.ResponseVerifyExistsItemUnitUpWithUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.mapper.ItemUnitMapper;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.ShoppinglistItemMetadataDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestQuickCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpItemUnitUpdateMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpdateShoppinglistItemItemUnitsUp;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.ShoppinglistItemMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.constants.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository.ShoppinglistItemRepository;
import es.franricodev.shopping_list_gestor_service.utils.DateUtils;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class ShoppinglistItemServiceImpl implements ShoppinglistItemService {

    @Autowired
    private ItemUnitService itemUnitService;

    @Autowired
    private CalculateSystemService calculateSystemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppinglistItemRepository shoppinglistItemRepository;

    @Autowired
    private ShoppinglistItemMapper shoppinglistItemMapper;

    @Autowired
    private ItemUnitMapper itemUnitMapper;

    @Override
    public void addItemUnitUpToShoppinglistItem(CreateItemUnitData createItemUnitData, Long idShoppinglistItem) throws ShoppinglistItemException, ItemUnitException, ShoppinglistException {
        log.info("Add a new item unit to the shoppinglist item: {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = findShoppinglistItemByIdInfoBlockFalse(idShoppinglistItem);
        List<ItemUnit> filteredItemUnit = shoppinglistItem.getItemUnitList().stream().filter(itemUnit -> !itemUnit.getInfoBlock()).toList();
        ResponseVerifyExistsItemUnitUpWithUnitaryPrice response = itemUnitService.verifyExistsAnItemUnitUpWithUnitaryPrice(filteredItemUnit, createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice());
        ItemUnit itemUnit = null;
        if (response == null) {
            log.info("There are not any Item Units UP associated to the SHOPPINGLIST-ITEM with id : {} with unitary price : {}, create Item Unit UP process started", shoppinglistItem.getId(), createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice());
            itemUnit = itemUnitService.createItemUnitV2(createItemUnitData, false, shoppinglistItem);
        } else {
            log.info("There are at least one Item Unit UP with an unitary price of : {} in the SHOPPINGLIST-ITEM with id : {}, adding quantity process started for the ITEM UNIT UP with id: {}", createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice(), shoppinglistItem.getId(), response.idUpItemUnit());
            itemUnit = itemUnitService.updateItemUnitUpValues(response.idItemUnit(), response.idUpItemUnit(), createItemUnitData.getCreateUpItemUnitData().getQuantity());
        }
        if(itemUnit != null) {
            ArrayList<ItemUnit> itemsUnitCreated = new ArrayList<>();
            itemsUnitCreated.add(itemUnit);
            shoppinglistItem.setItemUnitList(itemsUnitCreated);
            double result = getShoppinglistItemCalculatedPrice(shoppinglistItem);
            shoppinglistItem.setCalculatedPrice(result);
            updateShoppinglistItem(shoppinglistItem);
        }
    }

    @Override
    public List<ItemUnitDTO> getAllItemUnitsFromShoppinglistItem(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Get all item units from shoppinglist item: {}", idShoppinglistItem);
        Optional<ShoppinglistItem> optionalShoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem);
        if (optionalShoppinglistItem.isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
        }
        ShoppinglistItem shoppinglistItem = optionalShoppinglistItem.get();
        if(!shoppinglistItem.getCalculateSystem().getCode().equalsIgnoreCase("UP")) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTEM_INCORRRECT_CALCULATE_SYSTEM);
        }
        List<ItemUnit> itemUnits = shoppinglistItem.getItemUnitList();
        return itemUnitMapper.itemUnitListToItemUnitDtoList(itemUnits);
    }

    @Override
    public void removeItemUnitFromShoppinglistItem(Long idShoppinglistItem, Long idItemUnit) throws ShoppinglistItemException {
        log.info("Removing the item unit: {} from the shoppinglist: {}", idItemUnit, idShoppinglistItem);
        try {
            ShoppinglistItem shoppinglistItem = findShoppinglistItemByIdInfoBlockFalse(idShoppinglistItem);
            ItemUnit itemUnit = itemUnitService.findItemUnitByIdAndInfoBlockFalse(idItemUnit);
            if(!shoppinglistItem.getItemUnitList().remove(itemUnit)){
                throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISITEM_DELETE_ITEM_UNIT_ERR);
            }
            shoppinglistItemRepository.save(shoppinglistItem);
            itemUnitService.deleteItemUnit(itemUnit);
        } catch (Exception e) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_GENERIC_ERR);
        }
    }

    @Override
    public void addItemUnitWPToShoppinglistItem(Long idShoppinglistItem, RequestAddItemUnitWP requestAddItemUnitWP) throws ShoppinglistItemException {
        log.info("Adding new item unit WP to the shoppinglistitem with id: {}", idShoppinglistItem);
        Optional<ShoppinglistItem> optionalShoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem);
        if (optionalShoppinglistItem.isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
        }
        ShoppinglistItem shoppinglistItem = optionalShoppinglistItem.get();
        // En este caso, solo puede existir un unico item unit de tipo WP, unicamente se ira actualizando el precio o el peso.
        // por lo tanto hay que buscar si existe un item unit previo
        if(shoppinglistItem.getItemUnitList().isEmpty()) {
            // TODO : SUSTITUIR createItemUnit POR createItemUnitV2
            ItemUnit itemUnit = itemUnitService.createItemUnit(shoppinglistItem, 0D, shoppinglistItem.getCalculateSystem());
            shoppinglistItem.setItemUnitList(Collections.singletonList(itemUnit));
        } else {
            log.info("The shoppinglist item already have a item unit WP, this is gonna be updated with the new values of weight and pricekg");
            // En este caso se tiene que actualizar el shoppinglist item
            itemUnitService.updateItemUnit(shoppinglistItem.getItemUnitList().get(0), requestAddItemUnitWP);
        }
        recalculateShoppinglistItemsTotalPrice(shoppinglistItem);
    }

    private void recalculateShoppinglistItemsTotalPrice(ShoppinglistItem shoppinglistItem) {
        double totalShoppinglistPrice = 0D;
        List<ItemUnit> itemUnitList = shoppinglistItem.getItemUnitList();
        for(ItemUnit itemUnit : itemUnitList) {
            if(itemUnit != null) {
                totalShoppinglistPrice += itemUnitService.calculateItemUnitTotalPrice(itemUnit);
            }
        }
        shoppinglistItem.setCalculatedPrice(totalShoppinglistPrice);
        shoppinglistItemRepository.save(shoppinglistItem);
    }

    @Override
    public ResponseGetAllItemUnitUpGroupedByPrice getItemsUnitsUpGroupedByPrice(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Getting all the items units UP type of the shoppinglist item with id {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = findShoppinglistItemByIdInfoBlockFalse(idShoppinglistItem);
        return itemUnitService.getAllItemsUnitUpGroupedByPrice(shoppinglistItem);

    }

    @Transactional
    @Override
    public ShoppinglistItem updateShoppinglistItem(ShoppinglistItem shoppinglistItem) {
        log.info("Update the information of the shoppinglist item with id: {}", shoppinglistItem.getId());
        return shoppinglistItemRepository.save(shoppinglistItem);
    }

    @Override
    public ResponseItemUnitWpMetadata getItemUnitWpMetadata(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Getting the metadata of one item unit wp from the shoppinglistItem with id: {}", idShoppinglistItem);
        ResponseItemUnitWpMetadata response = null;
        ShoppinglistItem shoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));
        if(shoppinglistItem.getItemUnitList().isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_WITHOUT_ITEM_UNITS);
        }
        log.info("The shoppinglist with id: {}, have one item unit", idShoppinglistItem);
        ItemUnit itemUnit = shoppinglistItem.getItemUnitList().get(0);
        if (itemUnit.isWpItem()) {
            log.info("The item unit with id: {} is a WP item", itemUnit.getId());
            WpItemUnit wpItemUnit = itemUnit.getWpItemUnit();
            response = ResponseItemUnitWpMetadata.builder()
                    .weight(wpItemUnit.getWeight())
                    .priceKg(wpItemUnit.getPriceKg())
                    .calculatedPrice(wpItemUnit.getPriceKg() * wpItemUnit.getWeight())
                    .idItemUnitWp(wpItemUnit.getId())
                    .idItemUnit(itemUnit.getId())
                    .build();
        }
        return response;
    }

    @Override
    public void deleteLogicAllShoppinglistItem(List<ShoppinglistItem> shoppinglistItemList) {
        log.info("Logic deletion of all shoppinglist items from the shoppinglistItem list");
        for(ShoppinglistItem shoppinglistItem : shoppinglistItemList) {
            deleteLogicShoppinglistItem(shoppinglistItem);
        }
    }

    @Override
    public void deleteLogicShoppinglistItem(ShoppinglistItem shoppinglistItem) {
        log.info("Logic deletion of the entity shoppinglist item with id: {}", shoppinglistItem.getId());
        shoppinglistItem.setInfoBlock(true);
        itemUnitService.deleteLogicItemUnitList(shoppinglistItem.getItemUnitList());
        shoppinglistItemRepository.save(shoppinglistItem);
    }

    @Override
    public ResponseDeleteShoppinglistItem deleteLogicShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Logic deletion of the shoppinglist item with id: {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = shoppinglistItemRepository.findByIdAndInfoBlockFalse(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));
        deleteLogicShoppinglistItem(shoppinglistItem);
        return ResponseDeleteShoppinglistItem.builder()
                .delete(true)
                .message(Strings.EMPTY)
                .build();
    }

    @Override
    public ShoppinglistItem findShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException {
        return shoppinglistItemRepository.findByIdAndInfoBlockFalse(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));
    }

    @Override
    public ShoppinglistItem findShoppinglistItemByIdInfoBlockFalse(Long idShoppinglistItem) {
        log.info("Getting the information of the shoppinglist item {}", idShoppinglistItem);
        return shoppinglistItemRepository.findByIdAndInfoBlockFalse(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR)
        );
    }

    @Override
    public ResponseCreateShoppinglistItem createShoppinglistItemMetadata(RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem) throws ShoppinglistItemException {
        log.info("Creation of the shoppinglist item metadata");
        try {
            Product product = productService.createProductV2(requestCreateShoppinglistItem.getProductInfo());
            CalculateSystem calculateSystem = calculateSystemService.findCalculateSystemById(requestCreateShoppinglistItem.getSelectedCalculateSystem());
            ShoppinglistItem shoppinglistItem = createShoppinglistItemMetadata(product, calculateSystem);
            // TODO: Creamos el ItemUnit y su WP O UP item asociado
            ItemUnit itemUnitCreated = null;
            if (requestCreateShoppinglistItem.getCreateItemUnitData() != null && requestCreateShoppinglistItem.getCreateItemUnitData().isCreateItemUnit()) {
                // TODO: Se procede a la creación del item unit
                itemUnitCreated = itemUnitService.createItemUnitV2(
                        requestCreateShoppinglistItem.getCreateItemUnitData(),
                        calculateSystem.getCode().equalsIgnoreCase("WP"),
                        shoppinglistItem
                );
            }
            if(itemUnitCreated != null) {
                ArrayList<ItemUnit> itemsUnitCreated = new ArrayList<>();
                itemsUnitCreated.add(itemUnitCreated);
                shoppinglistItem.setItemUnitList(itemsUnitCreated);
                shoppinglistItem.setCalculatedPrice(shoppinglistItem.getCalculatedPrice() + itemUnitCreated.getTotalPrice());
                shoppinglistItemRepository.save(shoppinglistItem);
            }
            if (shoppinglistItem.getCalculateSystem().getCode().equals("UP")) {
                log.info("The shoppinglist item have a UP Calculate System");
                shoppinglistItem.setCalculatedPrice(getShoppinglistItemCalculatedPrice(shoppinglistItem));
            } else {
                log.info("The shoppinglist item have a WP Calculate System");
                shoppinglistItem.setCalculatedPrice(getShoppinglistItemCalculatedPriceWp(shoppinglistItem));
            }
            shoppinglistItemRepository.save(shoppinglistItem);
            // TODO: Faltaría indicar a que lista de la compra se debe de asignar este SLI
            return ResponseCreateShoppinglistItem.builder()
                    .created(true)
                    .idShoppinglistItemCreated(shoppinglistItem.getId())
                    .shoppinglistItemCalculatedPrice(shoppinglistItem.getCalculatedPrice())
                    .creationDate(DateUtils.formatDate(shoppinglistItem.getAssignationToListDate()))
                    .build();
        } catch (CalculateSystemException | ItemUnitException e) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
        }
    }

    @Override
    public List<ShoppinglistItemMetadataDTO> getShoppinglistItemMetadataDTO(Long idShoppinglist) throws ShoppinglistItemException {
        log.info("Building a list with the metainformation of the shoppinglist items of the shoppinglist with id: {}", idShoppinglist);
        List<ShoppinglistItem> shoppinglistItems =
                shoppinglistItemRepository.findAllShoppinglistItemByShoppinglistIdAndInfoBlockFalse(idShoppinglist).orElseThrow(
                        () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR)
                );
        return shoppinglistItems.stream().map(this::buildShoppinglistItemMetadataDTO).toList();
    }

    @Override
    public void updateShoppinglistItemCalculatedPrice(Long idShoppinglistItem) {
        log.info("Update the calculate price of the shoppinglist item with id: {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = findShoppinglistItemByIdInfoBlockFalse(idShoppinglistItem);
        shoppinglistItem.setCalculatedPrice(getShoppinglistItemCalculatedPrice(shoppinglistItem));
        updateShoppinglistItem(shoppinglistItem);
    }

    @Override
    public void updateShoppinglistItemUpItemsUnitData(Long idShoppinglistItem, RequestUpdateShoppinglistItemItemUnitsUp request) {
        log.info("Update the shoppinglist item {} items units up values", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = findShoppinglistItemByIdInfoBlockFalse(idShoppinglistItem);
        if (shoppinglistItem.getCalculateSystem().getCode().equals("UP")) {
            log.info("The shoppinglist item {} found have the calculate system up, lets proceed getting all the items units associated", idShoppinglistItem);
            List<ItemUnit> itemsUnits = itemUnitService.findAllItemUnitsByShoppinglistItemAndInfoBlockFalse(shoppinglistItem);
            for (ItemUnit itemUnit : itemsUnits) {
                for(RequestUpItemUnitUpdateMetadata requestData : request.requestUpItemUnitUpdateMetadataList()) {
                    if (itemUnit.getId().equals(requestData.idItemUnit())) {
                        if (!requestData.removeItemUnitUp()) {
                            itemUnitService.updateItemUnitUpValues(requestData.idItemUnit(), requestData.idItemUnitUp(), requestData.newQuantity());
                        } else {
                            itemUnitService.deleteLogicItemUnit(itemUnit);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void quickCreateShoppinglistItem(RequestQuickCreateShoppinglistItem request) {
        log.info("Creating a new shoppinglist item using with the quick version mode");
        Product product = productService.createProductV2(request.createProductInfo());
        CalculateSystem calculateSystem = calculateSystemService.findCalculateSystemById(request.selectedCalculateSystem());
        createShoppinglistItemMetadata(product, calculateSystem);
    }

    private ShoppinglistItem createShoppinglistItemMetadata(Product product, CalculateSystem calculateSystem) {
        log.info("Create new shoppinglist item with product id : {} and calculate system id: {}", product.getId(), calculateSystem.getId());
        ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
        shoppinglistItem.setInfoBlock(false);
        shoppinglistItem.setName(product.getName());
        shoppinglistItem.setAssignationToListDate(new Date());
        shoppinglistItem.setCalculateSystem(calculateSystem);
        shoppinglistItem.setCalculatedPrice(0.0);
        shoppinglistItem = shoppinglistItemRepository.save(shoppinglistItem);
        productService.assignProductToShoppinglistItem(shoppinglistItem, product);
        return shoppinglistItem;
    }

    private ShoppinglistItemMetadataDTO buildShoppinglistItemMetadataDTO(ShoppinglistItem shoppinglistItem) {
        return ShoppinglistItemMetadataDTO.builder()
                .id(shoppinglistItem.getId())
                .name(shoppinglistItem.getName())
                .assignationToListDate(DateUtils.formatDate(shoppinglistItem.getAssignationToListDate()))
                .calculatedPrice(shoppinglistItem.getCalculatedPrice())
                .calculateSystemCode(shoppinglistItem.getCalculateSystem().getCode())
                .build();
    }

    private double getShoppinglistItemCalculatedPrice(ShoppinglistItem shoppinglistItem) {
        log.info("Calculate of the calculate price value of the shoppinglist item with id : {}, the calculate system is UP", shoppinglistItem.getId());
        double calculatedPrice = 0.0;
        try {
            List<ItemUnit> itemsUnits = itemUnitService.findAllItemUnitsByShoppinglistItemAndInfoBlockFalse(shoppinglistItem);
            for (ItemUnit itemUnit : itemsUnits) {
                calculatedPrice += itemUnit.getTotalPrice();
            }
        } catch (ItemUnitException e) {
            log.info(e.getMessage());
        }
        return calculatedPrice;
    }

    private double getShoppinglistItemCalculatedPriceWp(ShoppinglistItem shoppinglistItem) {
        log.info("Calculate of the calculate price value of the shoppinglist item with id : {}, the calculate system is WP", shoppinglistItem.getId());
        double calculatedPrice = 0.0;
        if(!shoppinglistItem.getItemUnitList().isEmpty()) {
            for(ItemUnit itemUnit : shoppinglistItem.getItemUnitList()) {
                log.info("Getting the total price of the item unit: {}", itemUnit.getId());
                calculatedPrice += itemUnit.getTotalPrice();
            }
        }
        return calculatedPrice;
    }


}
