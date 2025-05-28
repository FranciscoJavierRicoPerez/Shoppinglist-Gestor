package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CalculateSystemServicelmpl implements CalculateSystemService{
    @Override
    public Double calculateProductPrice(CalculateSystem calculateSystem, Product product) {
        return 0.0;
    }

    @Override
    public CalculateSystem findCalculateSystemById(Long id) {
        return CalculateSystem.UNITARY_PRICE;
    }
}
