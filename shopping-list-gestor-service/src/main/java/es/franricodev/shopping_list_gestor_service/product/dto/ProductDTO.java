package es.franricodev.shopping_list_gestor_service.product.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Date creationDate;
}
