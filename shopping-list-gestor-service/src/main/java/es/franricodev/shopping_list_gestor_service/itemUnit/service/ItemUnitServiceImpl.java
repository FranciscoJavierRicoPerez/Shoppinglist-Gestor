package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.messages.ItemUnitMessagesError;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitUpGrouped;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemServiceImpl;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.upItemUnit.service.UpItemUnitService;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.service.WpItemUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemUnitServiceImpl implements ItemUnitService {

    @Lazy
    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Autowired
    private ItemUnitRepository itemUnitRepository;

    @Autowired
    private WpItemUnitService wpItemUnitService;

    @Autowired
    private UpItemUnitService upItemUnitService;

    @Override
    public ItemUnit createItemUnit(ShoppinglistItem shoppinglistItem, Double unitaryPrice, CalculateSystem calculateSystem) {
        log.info("Creating a item unit form the shoppinglist item: {}", shoppinglistItem.getId());
        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setShoppinglistItem(shoppinglistItem);
        if(calculateSystem.getCode().equalsIgnoreCase("WP")) {
            itemUnit.setWpItemUnit(wpItemUnitService.updateWpItemUnit(new WpItemUnit()));
        } else {
            UpItemUnit upItemUnit = new UpItemUnit();
            upItemUnit.setQuantity(1);
            upItemUnit.setUnityPrice(unitaryPrice);
            itemUnit.setUpItemUnit(upItemUnitService.updateUpItemUnit(upItemUnit));
        }
        // TODO: REFACTOR -> DOBLE SAVE!!
        calculateItemUnitTotalPrice(itemUnit);
        return itemUnitRepository.save(itemUnit);
    }

    @Override
    public ItemUnit findItemUnitById(Long id) throws Exception {
        return itemUnitRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public void deleteItemUnit(ItemUnit itemUnit) {
        itemUnitRepository.delete(itemUnit);
    }

    @Override
    public void updateItemUnit(ItemUnit itemUnit, RequestAddItemUnitWP requestAddItemUnitWP) {
        WpItemUnit wpItemUnit = itemUnit.getWpItemUnit();
        wpItemUnit.setPriceKg(requestAddItemUnitWP.getPriceKg());
        wpItemUnit.setWeight(requestAddItemUnitWP.getWeight());
        calculateItemUnitTotalPrice(itemUnit);
        wpItemUnitService.updateWpItemUnit(wpItemUnit);
    }

    // TODO: REFACTORIZAR -> MODIFICAR METODO POR UNO QUE NO HAGA ESTE SAVE QUE SENCILLAMENTE CALCULE EL TOTAL PRICE Y LO DEVUELVA
    @Override
    public Double calculateItemUnitTotalPrice(ItemUnit itemUnit) {
        double totalPriceCalculated = 0.0;
        if(itemUnit.getUpItemUnit() != null) {
            totalPriceCalculated = itemUnit.getUpItemUnit().getUnityPrice() * itemUnit.getUpItemUnit().getQuantity();
        } else {
            totalPriceCalculated = itemUnit.getWpItemUnit().getPriceKg() * itemUnit.getWpItemUnit().getWeight();
        }
        itemUnit.setTotalPrice(totalPriceCalculated);
        itemUnitRepository.save(itemUnit);
        return totalPriceCalculated;
    }

    @Override
    public ItemUnit createItemUnitV2(CreateItemUnitData createItemUnitData, boolean isWpItemUnit, ShoppinglistItem shoppinglistItem) throws ItemUnitException {
        validateCorrectItemUnitCreationData(createItemUnitData, isWpItemUnit);
        if(!createItemUnitData.isCreateItemUnit()) {
            return null;
        }
        if(createItemUnitData.getCreateUpItemUnitData() == null && createItemUnitData.getCreateWpItemUnitData() == null) {
            throw new ItemUnitException(ItemUnitMessagesError.ITEMUNIT_NO_WP_OR_UP_ITEM_DATA);
        }
        WpItemUnit wpItemUnitCreated = null;
        UpItemUnit upItemUnitCreated = null;
        if(isWpItemUnit && createItemUnitData.getCreateWpItemUnitData() != null) {
            wpItemUnitCreated = wpItemUnitService.createWpItemUnit(createItemUnitData.getCreateWpItemUnitData());
        }
        if(!isWpItemUnit && createItemUnitData.getCreateUpItemUnitData() != null) {
            upItemUnitCreated = upItemUnitService.createUpItemUnit(createItemUnitData.getCreateUpItemUnitData());
        }

        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setWpItemUnit(wpItemUnitCreated);
        itemUnit.setUpItemUnit(upItemUnitCreated);
        itemUnit.setTotalPrice(calculateItemUnitTotalPriceV2(itemUnit));
        itemUnit = itemUnitRepository.save(itemUnit);
        addItemUnitToShoppinglistItem(itemUnit, shoppinglistItem);
        return itemUnit;
    }

    @Override
    public ResponseGetAllItemUnitUpGroupedByPrice getAllItemsUnitUpGroupedByPrice(ShoppinglistItem shoppinglistItem) {
        log.info("Getting all the items units from the shoppinglist item with id {} grouped by quantity and unitary price", shoppinglistItem.getId());
        List<ResponseItemUnitUpGrouped> responseItemUnitUpGroupedList = new ArrayList<>();
        boolean isUPItem = shoppinglistItem.getCalculateSystem().getCode().equalsIgnoreCase("UP");
        if (isUPItem) {
            List<ItemUnit> itemUnits = shoppinglistItem.getItemUnitList();
            for(ItemUnit itemUnit : itemUnits) {
                ResponseItemUnitUpGrouped responseItemUnitUpGrouped = new ResponseItemUnitUpGrouped();
                responseItemUnitUpGrouped.setQuantity(itemUnit.getUpItemUnit().getQuantity());
                responseItemUnitUpGrouped.setPrice(itemUnit.getUpItemUnit().getUnityPrice());
                responseItemUnitUpGrouped.setCalculatedPrice(itemUnit.getTotalPrice());
                responseItemUnitUpGroupedList.add(responseItemUnitUpGrouped);
            }
        }
        return ResponseGetAllItemUnitUpGroupedByPrice.builder()
                .itemsUpGrouped(responseItemUnitUpGroupedList)
                .totalPrice(shoppinglistItem.getCalculatedPrice())
                .build();
    }

    private double calculateItemUnitTotalPriceV2(ItemUnit itemUnit){
        double totalPriceCalculated = 0.0;
        if(itemUnit.getUpItemUnit() != null) {
            totalPriceCalculated = itemUnit.getUpItemUnit().getUnityPrice() * itemUnit.getUpItemUnit().getQuantity();
        }
        if(itemUnit.getWpItemUnit() != null) {
            totalPriceCalculated = itemUnit.getWpItemUnit().getPriceKg() * itemUnit.getWpItemUnit().getWeight();
        }
        return totalPriceCalculated;
    }

    private ItemUnit addItemUnitToShoppinglistItem(ItemUnit itemUnit, ShoppinglistItem shoppinglistItem) {
        if(itemUnit != null && shoppinglistItem != null) {
            if(shoppinglistItem.getItemUnitList().isEmpty()) {
                ArrayList<ItemUnit> itemList = new ArrayList<>();
                itemList.add(itemUnit);
                shoppinglistItem.setItemUnitList(itemList);
            } else {
                shoppinglistItem.getItemUnitList().add(itemUnit);
            }
            shoppinglistItem = shoppinglistItemService.updateShoppinglistItem(shoppinglistItem);
            itemUnit.setShoppinglistItem(shoppinglistItem);
            itemUnit = itemUnitRepository.save(itemUnit);
        }
        return itemUnit;
    }

    private void validateCorrectItemUnitCreationData(CreateItemUnitData createItemUnitData, boolean isWpItemUnit) throws ItemUnitException {
        if(isWpItemUnit && (createItemUnitData.getCreateWpItemUnitData().getPriceKg() == -1 || createItemUnitData.getCreateWpItemUnitData().getWeight() == -1)) {
            log.error(ItemUnitMessagesError.ITEMUNIT_CREATION_DATA_WITH_ERRORS);
            throw new ItemUnitException(ItemUnitMessagesError.ITEMUNIT_CREATION_DATA_WITH_ERRORS);
        }
        if(!isWpItemUnit && (createItemUnitData.getCreateUpItemUnitData().getQuantity() == -1 || createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice() == -1)) {
            log.error(ItemUnitMessagesError.ITEMUNIT_CREATION_DATA_WITH_ERRORS);
            throw new ItemUnitException(ItemUnitMessagesError.ITEMUNIT_CREATION_DATA_WITH_ERRORS);
        }
    }


}
