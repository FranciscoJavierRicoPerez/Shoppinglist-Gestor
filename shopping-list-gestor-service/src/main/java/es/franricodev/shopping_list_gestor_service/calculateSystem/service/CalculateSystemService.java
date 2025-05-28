package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.product.model.Product;

public interface CalculateSystemService {

    Double calculateProductPrice(CalculateSystem calculateSystem, Product product);

    CalculateSystem findCalculateSystemById(Long id);

}
