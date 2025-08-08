package es.franricodev.shopping_list_gestor_service.product.controller;

import es.franricodev.shopping_list_gestor_service.product.dto.ProductDTO;
import es.franricodev.shopping_list_gestor_service.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping("/v1/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("Getting all the products from the database");
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(productService.getAllProducts(), httpStatus);
    }
}
