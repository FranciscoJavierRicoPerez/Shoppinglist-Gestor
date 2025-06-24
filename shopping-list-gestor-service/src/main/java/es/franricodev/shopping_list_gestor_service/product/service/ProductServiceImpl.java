package es.franricodev.shopping_list_gestor_service.product.service;

import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.repository.ProductRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductByName(String name) throws ProductException {
        Optional<Product> productOpt = productRepository.findByName(name);
        return productOpt.orElseGet(() -> createProduct(name));
    }

    @Override
    public Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setCreationDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void assignProductToShoppinglistItem(ShoppinglistItem shoppinglistItem, Product product) {
        if(product.getShoppinglistItems() != null) {
            product.getShoppinglistItems().add(shoppinglistItem);
        } else {
            product.setShoppinglistItems(new HashSet<ShoppinglistItem>());
            product.getShoppinglistItems().add(shoppinglistItem);
        }
        productRepository.save(product);
    }

}
