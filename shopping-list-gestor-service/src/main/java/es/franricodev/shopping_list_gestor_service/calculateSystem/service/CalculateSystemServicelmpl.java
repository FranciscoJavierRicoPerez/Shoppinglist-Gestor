package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.error.CalculateSystemError;
import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.repository.CalculateSystemRepository;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CalculateSystemServicelmpl implements CalculateSystemService{

    @Autowired
    private CalculateSystemRepository calculateSystemRepository;

    private final static Logger logger = LoggerFactory.getLogger(CalculateSystemServicelmpl.class);

    @Override
    public Double calculateProductPrice(CalculateSystem calculateSystem, Product product) {
        return 0.0;
    }

    @Override
    public CalculateSystem findCalculateSystemByCode(String code) throws CalculateSystemException {
        logger.info("Find the calculate system with code: {}", code);
        return calculateSystemRepository.findByCode(code).orElseThrow(() -> new CalculateSystemException(CalculateSystemError.CALCULATE_SYSTEM_NOT_FOUND));
    }

    @Override
    public void assignShoppinglistItemToCalculateSystem(ShoppinglistItem shoppinglistItem, CalculateSystem calculateSystem) {
        if(calculateSystem.getShoppinglistItems() != null) {
            calculateSystem.getShoppinglistItems().add(shoppinglistItem);
        } else {
            calculateSystem.setShoppinglistItems(new HashSet<>());
            calculateSystem.getShoppinglistItems().add(shoppinglistItem);
        }
        calculateSystemRepository.save(calculateSystem);
    }
}
