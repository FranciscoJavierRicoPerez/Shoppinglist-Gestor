package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemService;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.mapper.ItemUnitMapper;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.ShoppinglistItemMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages.ShoppinglistItemMessagesError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository.ShoppinglistItemRepository;
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

    //@Autowired
    //private ShoppinglistService shoppinglistService;

    @Autowired
    private ShoppinglistItemRepository shoppinglistItemRepository;

    @Autowired
    private ShoppinglistItemMapper shoppinglistItemMapper;

    @Autowired
    private ItemUnitMapper itemUnitMapper;

    /* @Override
    public void deleteShoppinglistItem(Long idItem) throws ShoppinglistItemException {
        log.info("Deleting the shoppinglist item with id: {}", idItem);
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
        Shoppinglist shoppinglist = shoppinglistService.findShoppinglistByShoppinglistItemId(shoppinglistItem.getId());
        try {
            List<ShoppinglistItem> shoppinglistItemList = shoppinglistService.removeShoppinglistItemFromShoppinglist(shoppinglist.getId(), shoppinglistItem.getId());
            shoppinglist.setItems(shoppinglistItemList);
            shoppinglistService.calculateShoppinglistTotalPrice(shoppinglist.getId());
        } catch (ShoppinglistException e) {
           throw new ShoppinglistItemException("ERROR-RECALCULANDO-EL-PRECIO-TOTAL-DE-LA-LISTA");
        }
        shoppinglistItemRepository.delete(shoppinglistItem);
        shoppinglistService.updateShoppinglist(shoppinglist);
    } */

    @Override
    public void addItemUnitToShoppinglistItem(CreateItemUnitData createItemUnitData, Long idShoppinglistItem) throws ShoppinglistItemException, ItemUnitException, ShoppinglistException {
        log.info("Add a new item unit to the shoppinglist item: {}", idShoppinglistItem);
        Optional<ShoppinglistItem> optionalShoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem);
        if (optionalShoppinglistItem.isEmpty()) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
        }
        ShoppinglistItem shoppinglistItem = optionalShoppinglistItem.get();
        ItemUnit itemUnitCreated = itemUnitService.createItemUnitV2(createItemUnitData, false, shoppinglistItem);
        if(itemUnitCreated != null) {
            ArrayList<ItemUnit> itemsUnitCreated = new ArrayList<>();
            itemsUnitCreated.add(itemUnitCreated);
            shoppinglistItem.setItemUnitList(itemsUnitCreated);
            shoppinglistItem.setCalculatedPrice(shoppinglistItem.getCalculatedPrice() + itemUnitCreated.getTotalPrice());
            shoppinglistItemRepository.save(shoppinglistItem);
        }
        // TODO: INGENIERA OTRA FORMA DE QUE SE ACTUALIZE EL PRECIO DE LA SHOPPINGLIST -> (¿OTRA PETICION A LA API SI ESTA ES CORRECTA?)
        // Shoppinglist shoppinglist = shoppinglistService.findShoppinglistByShoppinglistItemId(shoppinglistItem.getId());
        // shoppinglistService.updateShoppinglistTotalPrice(shoppinglist);
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
            Optional<ShoppinglistItem> optionalShoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem);
            if (optionalShoppinglistItem.isEmpty()) {
                throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR);
            }
            ShoppinglistItem shoppinglistItem = optionalShoppinglistItem.get();
            ItemUnit itemUnit = itemUnitService.findItemUnitById(idItemUnit);
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
            ItemUnit itemUnit = itemUnitService.createItemUnit(shoppinglistItem, 0D, shoppinglistItem.getCalculateSystem());
            shoppinglistItem.setItemUnitList(Collections.singletonList(itemUnit));
        } else {
            log.info("The shoppinglist item already have a item unit WP, this is gonna be updted with the new values of weight and pricekg");
            // En este caso se tiene que actualizar el shoppinglist item
            itemUnitService.updateItemUnit(shoppinglistItem.getItemUnitList().get(0), requestAddItemUnitWP);
        }
        recalculateShoppinglistItemsTotalPrice(shoppinglistItem);
        // TODO: REFACTORIZACION -> MIRAR COMO HACER QUE SE ACTUALIE EL TOTAL PRICE, ¿NUEVA PETICION A LA API?
        /* Shoppinglist shoppinglist = shoppinglistService.findShoppinglistByShoppinglistItemId(shoppinglistItem.getId());
        try {
            shoppinglist = shoppinglistService.calculateShoppinglistTotalPrice(shoppinglist.getId());
        } catch (ShoppinglistException e) {
            throw new RuntimeException(e);
        }
        shoppinglistService.updateShoppinglist(shoppinglist); */
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

    // **************************** VERSION 2 ENPOINTS ***************************************ç
    @Transactional
    @Override
    public ResponseCreateShoppinglistItem createShoppinglistItem(Long idShoppinglist, RequestCreateShoppinglistItemV2 requestData) throws ShoppinglistItemException {
        // TODO: HACER QUE LA CREACIÓN DEL SHOPPINGLIST ITEM VENGA DESDE UN ENDPOINT DE LA CAPA DE SHOPPINGLIST CONTROLLER - SERVIRCE
        log.info("V2: Creating a new shoppinglist item for the shoppinglist with id: {}", idShoppinglist);
        try {
            // Shoppinglist shoppinglist = shoppinglistService.findShoppinglistById(idShoppinglist);
            // TODO: Revisamos que el producto que se va a asignar en la SLI existe y si no lo creamos
            Product product = productService.createProductV2(requestData.getProductInfo());
            // TODO: Obtenemos el calculate system asociado
            CalculateSystem calculateSystem = calculateSystemService.findCalculateSystemById(requestData.getSelectedCalculateSystem());
            // TODO: Procedemos a la creacion del shoppinglist item (SLI)
            ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
            shoppinglistItem.setName(product.getName());
            shoppinglistItem.setAssignationToListDate(new Date());
            shoppinglistItem.setCalculateSystem(calculateSystem);
            shoppinglistItem.setCalculatedPrice(getShoppinglistItemCalculatedPrice(shoppinglistItem));
            // todo: shoppinglistService.addShoppinglistItemToShoppinglist(shoppinglistItem, shoppinglist);
            shoppinglistItem = shoppinglistItemRepository.save(shoppinglistItem);
            productService.assignProductToShoppinglistItem(shoppinglistItem, product);
            // TODO: Creamos el ItemUnit y su WP O UP item asociado
            ItemUnit itemUnitCreated = null;
            if (requestData.getCreateItemUnitData() != null && requestData.getCreateItemUnitData().isCreateItemUnit()) {
                // TODO: Se procede a la creación del item unit
                itemUnitCreated = itemUnitService.createItemUnitV2(
                        requestData.getCreateItemUnitData(),
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
            /* if(shoppinglist != null) {
                log.info("Updating the total price of the shoppinglist with the data of the new shoppinglist item");
                // todo: shoppinglistService.updateShoppinglistTotalPrice(shoppinglistService.findShoppinglistById(shoppinglist.getId()));
            } */
            return ResponseCreateShoppinglistItem.builder()
                    .idShoppinglistItemCreated(shoppinglistItem.getId())
                    .created(true)
                    .shoppinglistItemCalculatedPrice(shoppinglistItem.getCalculatedPrice())
                    // .totalPrice(shoppinglist!= null ? shoppinglist.getTotalPrice() : -1)
                    .build();
        } catch ( CalculateSystemException | ItemUnitException e) {
            log.info(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
        }
    }

    // TODO: ERROR AL EJECUTAR ESO
    @Override
    public ResponseGetAllItemUnitUpGroupedByPrice getItemsUnitsUpGroupedByPrice(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Getting all the items units UP type of the shoppinglist item with id {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem)
                .orElseThrow(() -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));

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
                    .calculatedPrice(wpItemUnit.getPriceKg() * wpItemUnit.getWeight()).build();
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
        log.info("Logic deletion of the shoppinglist item with id: {}", shoppinglistItem.getId());
        shoppinglistItem.setInfoBlock(true);
        itemUnitService.deleteLogicItemUnitList(shoppinglistItem.getItemUnitList());
        shoppinglistItemRepository.save(shoppinglistItem);
    }

    @Override
    public ResponseDeleteShoppinglistItem deleteLogicShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException {
        log.info("Logic deletion of the shoppinglist item with id: {}", idShoppinglistItem);
        ShoppinglistItem shoppinglistItem = shoppinglistItemRepository.findById(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));
        deleteLogicShoppinglistItem(shoppinglistItem);
        return ResponseDeleteShoppinglistItem.builder()
                .delete(true)
                .message(Strings.EMPTY)
                .build();
    }

    @Override
    public ShoppinglistItem findShoppinglistItemById(Long idShoppinglistItem) throws ShoppinglistItemException {
        return shoppinglistItemRepository.findById(idShoppinglistItem).orElseThrow(
                () -> new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_NOT_FOUND_ERR));
    }

    @Override
    public ResponseCreateShoppinglistItem createShoppinglistItemMetadata(RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem) throws ShoppinglistItemException {
        log.info("Creation of the shoppinglist item metadata");
        try {
            ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
            Product product = productService.createProductV2(requestCreateShoppinglistItem.getProductInfo());
            CalculateSystem calculateSystem = calculateSystemService.findCalculateSystemById(requestCreateShoppinglistItem.getSelectedCalculateSystem());
            shoppinglistItem.setCalculateSystem(calculateSystem);
            shoppinglistItem.setInfoBlock(false);
            // TODO: Procedemos a la creacion del shoppinglist item (SLI)
            shoppinglistItem.setName(product.getName());
            shoppinglistItem.setAssignationToListDate(new Date());
            shoppinglistItem.setCalculateSystem(calculateSystem);
            shoppinglistItem.setCalculatedPrice(getShoppinglistItemCalculatedPrice(shoppinglistItem));
            shoppinglistItem = shoppinglistItemRepository.save(shoppinglistItem);
            productService.assignProductToShoppinglistItem(shoppinglistItem, product);
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
            return ResponseCreateShoppinglistItem.builder()
                    .created(true)
                    .idShoppinglistItemCreated(shoppinglistItem.getId())
                    .shoppinglistItemCalculatedPrice(shoppinglistItem.getCalculatedPrice())
                    .build();
        } catch (CalculateSystemException | ItemUnitException e) {
            throw new ShoppinglistItemException(ShoppinglistItemMessagesError.SHOPPINGLISTITEM_CREATE_ERR);
        }
    }

    private double getShoppinglistItemCalculatedPrice(ShoppinglistItem shoppinglistItem) {
        double calculatedPrice = 0.0;
        if(!shoppinglistItem.getItemUnitList().isEmpty()) {
           for(ItemUnit itemUnit : shoppinglistItem.getItemUnitList()) {
               calculatedPrice += itemUnit.getTotalPrice();
           }
        }
        return calculatedPrice;
    }


}
