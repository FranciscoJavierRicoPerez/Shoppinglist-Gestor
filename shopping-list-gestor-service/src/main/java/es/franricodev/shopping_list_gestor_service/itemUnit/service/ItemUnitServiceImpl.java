package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
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

    private static final Logger logger = LoggerFactory.getLogger(ItemUnitServiceImpl.class);

    @Override
    public ItemUnit createItemUnit(ShoppinglistItem shoppinglistItem, Double unitaryPrice, CalculateSystem calculateSystem) {
        logger.info("Creating a item unit form the shoppinglist item: {}", shoppinglistItem.getId());
        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setUnitPrice(unitaryPrice);
        itemUnit.setShoppinglistItem(shoppinglistItem);
        if(calculateSystem.getCode().equalsIgnoreCase("WP")) {
            itemUnit.setWpItemUnit(wpItemUnitRepository.save(new WpItemUnit()));
        }
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
}
