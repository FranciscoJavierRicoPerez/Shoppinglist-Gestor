package es.franricodev.shopping_list_gestor_service.product.dto.request;

import lombok.Data;

@Data
public class CreateProductInfo {
    private boolean alreadyExists;
    private String productName;
}
