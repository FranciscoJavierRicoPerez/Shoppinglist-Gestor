package es.franricodev.shopping_list_gestor_service.product.service;

import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

public interface ProductService {

    Product findProductByName(String name) throws ProductException;

    Product createProduct(String name);

    void assignShoppinglistItemToProduct(ShoppinglistItem shoppinglistItem, Product product);


}
