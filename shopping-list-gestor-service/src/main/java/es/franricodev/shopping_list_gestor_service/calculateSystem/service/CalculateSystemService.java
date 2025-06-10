package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

public interface CalculateSystemService {

    Double calculateProductPrice(CalculateSystem calculateSystem, Product product);

    CalculateSystem findCalculateSystemById(Long id) throws CalculateSystemException;

    void assignShoppinglistItemToCalculateSystem(ShoppinglistItem shoppinglistItem,CalculateSystem calculateSystem);

}
