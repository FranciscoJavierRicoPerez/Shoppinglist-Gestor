package es.franricodev.shopping_list_gestor_service.product.service;

import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductByName(String name) throws ProductException {
        return productRepository.findByName(name).orElseThrow(() -> new ProductException("ERR_FIND_PRODUCT"));
    }

    @Override
    public Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        product.setCreationDate(new Date());
        return productRepository.save(product);
    }
}
