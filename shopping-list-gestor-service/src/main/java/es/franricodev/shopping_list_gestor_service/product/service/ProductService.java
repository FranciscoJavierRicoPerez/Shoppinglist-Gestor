package es.franricodev.shopping_list_gestor_service.product.service;

import es.franricodev.shopping_list_gestor_service.product.dto.ProductDTO;
import es.franricodev.shopping_list_gestor_service.product.dto.request.CreateProductInfo;
import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import java.util.List;

public interface ProductService {

    Product findProductByName(String name) throws ProductException;

    Product createProduct(String name);

    void assignProductToShoppinglistItem(ShoppinglistItem shoppinglistItem, Product product);

    // ********************* VERSION 2 **********************
    Product createProductV2(CreateProductInfo createProductInfo);

    List<ProductDTO> getAllProducts();

    List<String> getAllProductsNames();
}
