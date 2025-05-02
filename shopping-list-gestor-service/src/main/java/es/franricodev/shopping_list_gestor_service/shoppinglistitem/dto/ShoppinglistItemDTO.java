package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ShoppinglistItemDTO {
    private Long id;
    private Date assignatioToListDate;
    private Double calculatedPrice;
    private Integer quantity;
}
