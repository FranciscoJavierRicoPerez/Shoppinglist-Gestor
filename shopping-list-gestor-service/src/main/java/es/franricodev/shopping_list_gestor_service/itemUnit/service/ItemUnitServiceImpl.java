package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.RequestUpdateItemUnitWpTotalPrice;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.response.ResponseVerifyExistsItemUnitUpWithUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.constants.messages.ItemUnitMessagesError;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitUpGrouped;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.UpdateItemUnitUpValues;
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

    @Lazy // REVISAR ESTE LAZY NO DEBERIA DE ESTAR AQUI - ¿crear un servicio de integracion que se encarg de añadir los item unit a item i los sli a la lista???? se usa en : addItemUnitToShoppinglistItem
    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Autowired
    private ItemUnitRepository itemUnitRepository;

    @Autowired
    private WpItemUnitService wpItemUnitService;

    @Autowired
    private UpItemUnitService upItemUnitService;

    // TODO: DEPRECATED
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
            // itemUnit.setUpItemUnit(upItemUnitService.updateUpItemUnit(upItemUnit));
            itemUnit.setUpItemUnitList(List.of(upItemUnitService.updateUpItemUnit(upItemUnit)));
        }
        // TODO: REFACTOR -> DOBLE SAVE!!
        calculateItemUnitTotalPrice(itemUnit);
        return itemUnitRepository.save(itemUnit);
    }

    @Override
    public ItemUnit findItemUnitByIdAndInfoBlockFalse(Long idItemUnit)  {
        log.info("Search item unit with id: {}", idItemUnit);
        return itemUnitRepository.findByIdAndInfoBlockFalse(idItemUnit).orElseThrow(() -> new ItemUnitException(ItemUnitMessagesError.ITEMUNIT_NOT_FOUND));
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

    @Override
    public Double calculateItemUnitTotalPrice(ItemUnit itemUnit) {
        double totalPriceCalculated = 0.0;
        if(itemUnit.getUpItemUnitList() != null) {
            totalPriceCalculated = upItemUnitService.getItemUnitUpCalculatedPrice(itemUnit.getUpItemUnitList());
        } else {
            totalPriceCalculated = wpItemUnitService.getItemUnitWpCalculatedPrice(itemUnit.getWpItemUnit());
        }
        itemUnit.setTotalPrice(totalPriceCalculated);
        itemUnitRepository.save(itemUnit);
        return totalPriceCalculated;
    }

    @Override
    public ItemUnit createItemUnitV2(
            CreateItemUnitData createItemUnitData,
            boolean isWpItemUnit,
            ShoppinglistItem shoppinglistItem
    ) throws ItemUnitException {
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
            log.info("The item unit expected is WP type, started the proccess for the creation of the item unit wp");
            wpItemUnitCreated = wpItemUnitService.createWpItemUnit(createItemUnitData.getCreateWpItemUnitData());
        }
        if(!isWpItemUnit && createItemUnitData.getCreateUpItemUnitData() != null) {
            log.info("The item unit expected is UP type, so will proceed with his creation");
            upItemUnitCreated = upItemUnitService.createUpItemUnit(createItemUnitData.getCreateUpItemUnitData());
        }

        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setInfoBlock(false);
        itemUnit.setWpItemUnit(wpItemUnitCreated);
        List<UpItemUnit> upItemsUnitsList = new ArrayList<>();
        upItemsUnitsList.add(upItemUnitCreated);
        itemUnit.setUpItemUnitList(upItemUnitCreated != null ? upItemsUnitsList : null);
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
        Double totalPriceCalculated = 0D;
        if (isUPItem) {
            log.info("The shoppinglist item {} is UP Type", shoppinglistItem.getId());
            log.info("Proceed to filter the item unit list, actually the item unit list have {} elements", shoppinglistItem.getItemUnitList().size());
            List<ItemUnit> itemUnits = shoppinglistItem.getItemUnitList().stream().filter(itemUnit -> !itemUnit.getInfoBlock()).toList();
            log.info("Item unit list filtered, the new list size is : {}", itemUnits.size());
            List<Double> unitaryPrices = getAllUnitaryPrices(itemUnits);
            for (Double unitaryPrice : unitaryPrices) {
                ResponseItemUnitUpGrouped responseItemUnitUpGrouped = createResponseItemUnitUpGrouped(itemUnits, unitaryPrice);
                totalPriceCalculated += responseItemUnitUpGrouped.getCalculatedPrice();
                responseItemUnitUpGroupedList.add(responseItemUnitUpGrouped);
            }
        }
        return ResponseGetAllItemUnitUpGroupedByPrice.builder()
                .itemsUpGrouped(responseItemUnitUpGroupedList)
                .totalPrice(totalPriceCalculated)
                .build();
    }

    @Override
    public void deleteLogicItemUnitList(List<ItemUnit> itemUnitList) {
        log.info("Logic deletion of the items unit from the list");
        for (ItemUnit itemUnit : itemUnitList) {
            deleteLogicItemUnit(itemUnit);
        }
    }

    @Override
    public void deleteLogicItemUnit(ItemUnit itemUnit) {
        log.info("Logic deletion of the item unit {}", itemUnit.getId());
        itemUnit.setInfoBlock(true);
        itemUnitRepository.save(itemUnit);
        if (itemUnit.isWpItem()) {
            wpItemUnitService.deleteLogicWpItemUnit(itemUnit.getWpItemUnit());
        } else {
            for (UpItemUnit upItemUnit : itemUnit.getUpItemUnitList()) {
                upItemUnitService.deleteLogicUpItemUnit(upItemUnit);
            }
        }
    }

    @Override
    public void updateItemUnit(ItemUnit itemUnit) {
        log.info("Update item unit with id {}", itemUnit.getId());
        itemUnitRepository.save(itemUnit);
    }

    @Override
    public void updateItemUnitTotalPrice(Long idItemUnit, RequestUpdateItemUnitWpTotalPrice request) {
        log.info("Setting the new total price value [new total price: {}] to the item unit with id: {}", request.newTotalPrice(), idItemUnit);
        ItemUnit itemUnit = findItemUnitByIdAndInfoBlockFalse(idItemUnit);
        itemUnit.setTotalPrice(request.newTotalPrice());
        updateItemUnit(itemUnit);
    }

    // TODO -> REFACTORIZAR
    @Override
    public ResponseVerifyExistsItemUnitUpWithUnitaryPrice verifyExistsAnItemUnitUpWithUnitaryPrice(List<ItemUnit> itemsUnits, Double unitaryPrice) {
        ResponseVerifyExistsItemUnitUpWithUnitaryPrice response = null;
        for(ItemUnit itemUnit : itemsUnits) {
            Long idItemUnitUp = null;
            if (itemUnit.isUpItem()) {
                List<UpItemUnit> upItemUnitListFiltered = itemUnit.getUpItemUnitList().stream().filter(upItemUnit -> !upItemUnit.getInfoBlock()).toList();
                for(UpItemUnit upItemUnit : upItemUnitListFiltered) {
                    log.info("Verify if the item unit up: {} have the value", upItemUnit.getId());
                    idItemUnitUp = upItemUnitService.searchUnitaryPrice(upItemUnit, unitaryPrice);
                    if(idItemUnitUp != null) {
                        response = new ResponseVerifyExistsItemUnitUpWithUnitaryPrice(
                                idItemUnitUp,
                                itemUnit.getId()
                        );
                        break;
                    }
                }
            }
            if (idItemUnitUp != null) {
                break;
            }
        }
        return response;
    }

    @Override
    public ItemUnit updateItemUnitUpValues(Long idItemUnit, Long idItemUnitUp, int newQuantity) {
        log.info("Updating the item unit up {} from the item unit {} with the new quantity {}", idItemUnitUp, idItemUnit, newQuantity);
        ItemUnit itemUnit = findItemUnitByIdAndInfoBlockFalse(idItemUnit);
        if (itemUnit.isUpItem()) {
            upItemUnitService.updateUpItemUnitValues(new UpdateItemUnitUpValues(idItemUnitUp, newQuantity));
        }
        itemUnit.setInfoBlock(false);
        itemUnit.setTotalPrice(calculateItemUnitTotalPriceV2(itemUnit));
        itemUnit = itemUnitRepository.save(itemUnit);
        return itemUnit;
    }

    @Override
    public List<ItemUnit> findAllItemUnitsByShoppinglistItemAndInfoBlockFalse(ShoppinglistItem shoppinglistItem) {
        log.info("Getting all the items units associated to the shoppinglist item {}", shoppinglistItem.getId());
        return itemUnitRepository.findAllByShoppinglistItemAndInfoBlockFalse(shoppinglistItem).orElseThrow(
                () -> new ItemUnitException(ItemUnitMessagesError.NO_ITEM_UNITS_ASSOCIATED_TO_SHOPPINGLIST_ITEM)
        );
    }

    private ResponseItemUnitUpGrouped createResponseItemUnitUpGrouped(List<ItemUnit> itemUnits, Double unitaryPrice) {
        log.info("Creating the ResponseItemUnitUpGrouped, for the price value of {} ", unitaryPrice);
        ResponseItemUnitUpGrouped responseItemUnitUpGrouped = null;
        int quantity = 0;
        Long idItemUnit = null;
        Long id = null;
        for(ItemUnit itemUnit : itemUnits) {
            List<UpItemUnit> filtered = itemUnit.getUpItemUnitList().stream().filter(upItemUnit -> !upItemUnit.getInfoBlock()).toList();
            for(UpItemUnit upItemUnit : filtered) {
                if(upItemUnit.getUnityPrice().doubleValue() == unitaryPrice.doubleValue()) {
                    id = upItemUnit.getId();
                    quantity += upItemUnit.getQuantity();
                    idItemUnit = itemUnit.getId();
                }
            }
        }
        responseItemUnitUpGrouped = new ResponseItemUnitUpGrouped();
        responseItemUnitUpGrouped.setPrice(unitaryPrice);
        responseItemUnitUpGrouped.setQuantity(quantity);
        responseItemUnitUpGrouped.setCalculatedPrice(unitaryPrice * quantity);
        responseItemUnitUpGrouped.setIdItemUnitUp(id);
        responseItemUnitUpGrouped.setIdItemUnit(idItemUnit);
        return responseItemUnitUpGrouped;
    }

    private List<Double> getAllUnitaryPrices(List<ItemUnit> itemUnits) {
        log.info("Creating a list with all the different unitary prices in the item unit list");
        List<Double> unitaryPrices = new ArrayList<>();
        for (ItemUnit itemUnit : itemUnits) {
            // List<UpItemUnit> filtered = itemUnit.getUpItemUnitList().stream().filter(UpItemUnit::getInfoBlock).toList();
            List<UpItemUnit> filtered = itemUnit.getUpItemUnitList();
            for(UpItemUnit upItemUnit : filtered) {
                double unitaryPriceToCheck = upItemUnit.getUnityPrice();
                if(!checkUnitaryPriceAlreadyExistsInList(unitaryPriceToCheck, unitaryPrices)) {
                    log.info("Adding to the unitary prices list the value {}", unitaryPriceToCheck);
                    unitaryPrices.add(unitaryPriceToCheck);
                }
            }
        }
        return unitaryPrices;
    }

    private boolean checkUnitaryPriceAlreadyExistsInList(double actualUnitaryPrice, List<Double> unitaryPrices) {
        boolean alreadyExists = false;
        for (Double unitaryPrice : unitaryPrices) {
            if (unitaryPrice == actualUnitaryPrice) {
                alreadyExists = true;
                break;
            }
        }
        return alreadyExists;
    }

    private double calculateItemUnitTotalPriceV2(ItemUnit itemUnit){
        double totalPriceCalculated = 0.0;
        if (itemUnit.getUpItemUnitList() != null && !itemUnit.getUpItemUnitList().isEmpty()) {
            totalPriceCalculated = upItemUnitService.getItemUnitUpCalculatedPrice(itemUnit.getUpItemUnitList());
        }
        if(itemUnit.getWpItemUnit() != null) {
            totalPriceCalculated = wpItemUnitService.getItemUnitWpCalculatedPrice(itemUnit.getWpItemUnit());
        }
        return totalPriceCalculated;
    }

    private void addItemUnitToShoppinglistItem(ItemUnit itemUnit, ShoppinglistItem shoppinglistItem) {
        log.info("Adding the item unit {} to the shoppinglist item {}", itemUnit.getId(), shoppinglistItem.getId());
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
            itemUnitRepository.save(itemUnit);
        }
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
