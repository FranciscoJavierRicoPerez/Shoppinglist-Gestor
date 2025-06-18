package es.franricodev.shopping_list_gestor_service.shoppinglist.dto;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.ShoppinglistItemMetadataDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppinglistDetailsDTO {
    private Long id;
    private Date creationDate;
    private String code;
    private Date closeDate;
    private Double totalPrice;
    private Boolean isActive;
    private List<ShoppinglistItemMetadataDTO> items;
}
