package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;
import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

import java.util.List;

public interface CalculateSystemService {

    Double calculateProductPrice(CalculateSystem calculateSystem, Product product);

    CalculateSystem findCalculateSystemByCode(String code) throws CalculateSystemException;

    void assignShoppinglistItemToCalculateSystem(ShoppinglistItem shoppinglistItem,CalculateSystem calculateSystem);

    List<CalculateSystemDTO> findAll();

    // *********************** V2 **************************
    CalculateSystem findCalculateSystemById(Long id) throws CalculateSystemException;

    // void update(CalculateSystem calculateSystem);


}
