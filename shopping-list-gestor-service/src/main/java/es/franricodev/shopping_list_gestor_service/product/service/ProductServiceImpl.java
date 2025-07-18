package es.franricodev.shopping_list_gestor_service.product.service;

import es.franricodev.shopping_list_gestor_service.product.dto.request.CreateProductInfo;
import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.repository.ProductRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
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

    // ********************* VERSION 2 **********************
    @Override
    public Product createProductV2(CreateProductInfo createProductInfo) throws ProductException {
        log.info("V2: Creating a new product service method");
        Product product = null;
        if (createProductInfo.isAlreadyExists()) {
            log.info("The product already exist, returning the product with name: {} from the database", createProductInfo.getProductName());
            product = findProductByName(createProductInfo.getProductName());
        } else {
            log.info("The product not exists in the database, proceeds to the creating of the product");
            product = createProduct(createProductInfo.getProductName());
        }
        return  product;
    }

}
