package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.messages.ItemUnitMessagesError;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitUpGrouped;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
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
import java.util.Optional;

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
            // Se necesita una verificación previa para comprobar si ya existe algun item unit up con ese precio
            // en el caso de que NO EXISTA se crea y si existiese ye debe de actualizar el registro con la nueva informacion
            // aumentando el quantity
            log.info("The item unit is a UP type, started the proccess for the verification if there is a item unit up with the unitary price: {}", createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice());
            Optional<UpItemUnit> itemUnitUpOpt = upItemUnitService.findByUnitaryPrice(createItemUnitData.getCreateUpItemUnitData().getUnitaryPrice());
            if (itemUnitUpOpt.isPresent()) {
                log.info("The item unit up with id: {} have the unitary price requested, started the update process", itemUnitUpOpt.get().getId());
                UpItemUnit upItemUnit = itemUnitUpOpt.get();
                upItemUnit.setQuantity(upItemUnit.getQuantity() + createItemUnitData.getCreateUpItemUnitData().getQuantity());
                upItemUnitService.updateUpItemUnit(upItemUnit);
            } else {
                log.info("The aren't any item unit up with the requested unitary price, so will proceed with his creation");
                upItemUnitCreated = upItemUnitService.createUpItemUnit(createItemUnitData.getCreateUpItemUnitData());
            }
        }

        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setInfoBlock(false);
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
        Double totalPriceCalculated = 0D;
        if (isUPItem) {
            List<ItemUnit> itemUnits = shoppinglistItem.getItemUnitList();
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
            itemUnit.setInfoBlock(true);
            if (itemUnit.isWpItem()) {
                wpItemUnitService.deleteLogicWpItemUnit(itemUnit.getWpItemUnit());
            } else {
                upItemUnitService.deleteLogicUpItemUnit(itemUnit.getUpItemUnit());
            }
            itemUnitRepository.save(itemUnit);
        }
    }

    private ResponseItemUnitUpGrouped createResponseItemUnitUpGrouped(List<ItemUnit> itemUnits, Double unitaryPrice) {
        ResponseItemUnitUpGrouped responseItemUnitUpGrouped = new ResponseItemUnitUpGrouped();
        int quantity = 0;
        Long id = null;
        for(ItemUnit itemUnit : itemUnits) {
            if(itemUnit.getUpItemUnit().getUnityPrice().doubleValue() == unitaryPrice.doubleValue()) {
                id = itemUnit.getUpItemUnit().getId();
                quantity += itemUnit.getUpItemUnit().getQuantity();
            }
        }
        responseItemUnitUpGrouped.setPrice(unitaryPrice);
        responseItemUnitUpGrouped.setQuantity(quantity);
        responseItemUnitUpGrouped.setCalculatedPrice(unitaryPrice * quantity);
        responseItemUnitUpGrouped.setIdItemUnitUp(id);
        return responseItemUnitUpGrouped;
    }

    private List<Double> getAllUnitaryPrices(List<ItemUnit> itemUnits) {
        List<Double> unitaryPrices = new ArrayList<>();
        for (ItemUnit itemUnit : itemUnits) {
            double unitaryPriceToCheck = itemUnit.getUpItemUnit().getUnityPrice();
            if(!checkUnitaryPriceAlreadyExistsInList(unitaryPriceToCheck, unitaryPrices)) {
                unitaryPrices.add(unitaryPriceToCheck);
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
        if(itemUnit.getUpItemUnit() != null) {
            totalPriceCalculated = itemUnit.getUpItemUnit().getUnityPrice() * itemUnit.getUpItemUnit().getQuantity();
        }
        if(itemUnit.getWpItemUnit() != null) {
            totalPriceCalculated = itemUnit.getWpItemUnit().getPriceKg() * itemUnit.getWpItemUnit().getWeight();
        }
        return totalPriceCalculated;
    }

    private void addItemUnitToShoppinglistItem(ItemUnit itemUnit, ShoppinglistItem shoppinglistItem) {
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
