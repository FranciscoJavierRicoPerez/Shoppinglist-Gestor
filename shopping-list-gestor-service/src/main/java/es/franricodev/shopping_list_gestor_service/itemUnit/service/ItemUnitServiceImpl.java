package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.upItemUnit.repository.UpItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.repository.WpItemUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ItemUnitServiceImpl implements ItemUnitService {

    @Autowired
    private ItemUnitRepository itemUnitRepository;

    @Autowired
    private WpItemUnitRepository wpItemUnitRepository;

    @Autowired
    private UpItemUnitRepository upItemUnitRepository;

    private static final Logger logger = LoggerFactory.getLogger(ItemUnitServiceImpl.class);

    @Override
    public ItemUnit createItemUnit(ShoppinglistItem shoppinglistItem, Double unitaryPrice, CalculateSystem calculateSystem) {
        logger.info("Creating a item unit form the shoppinglist item: {}", shoppinglistItem.getId());
        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setShoppinglistItem(shoppinglistItem);
        if(calculateSystem.getCode().equalsIgnoreCase("WP")) {
            itemUnit.setWpItemUnit(wpItemUnitRepository.save(new WpItemUnit()));
        } else {
            UpItemUnit upItemUnit = new UpItemUnit();
            upItemUnit.setQuantity(1);
            upItemUnit.setUnityPrice(unitaryPrice);
            itemUnit.setUpItemUnit(upItemUnitRepository.save(upItemUnit));
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
        wpItemUnitRepository.save(wpItemUnit);
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
}
