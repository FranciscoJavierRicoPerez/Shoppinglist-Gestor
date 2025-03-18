package es.franricodev.shopping_list_gestor_service.shoppinglist.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ShoppinglistDTO {
    private Long id;
    private Date creationDate;
    private String code;
    private Date closeDate;
    private Double totalPrice;
    private Boolean isActive;
}
