package es.franricodev.shopping_list_gestor_service.itemUnit.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.itemUnit.repository.ItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ItemUnitServiceImpl implements ItemUnitService {

    @Autowired
    private ItemUnitRepository itemUnitRepository;

    private static final Logger logger = LoggerFactory.getLogger(ItemUnitServiceImpl.class);

    @Override
    public ItemUnit createItemUnit() {
        ItemUnit itemUnit = new ItemUnit();
        itemUnit.setUnitPrice(0D);
        itemUnit.setShoppinglistItems(new HashSet<>());
        return itemUnitRepository.save(itemUnit);
    }

    @Override
    public void assignShoppinglistItemToItemUnit(ShoppinglistItem shoppinglistItem, ItemUnit itemUnit) {
        if (itemUnit.getShoppinglistItems() != null) {
            itemUnit.getShoppinglistItems().add(shoppinglistItem);
        } else {
            itemUnit.setShoppinglistItems(new HashSet<ShoppinglistItem>());
            itemUnit.getShoppinglistItems().add(shoppinglistItem);
        }
        itemUnitRepository.save(itemUnit);
    }
}
