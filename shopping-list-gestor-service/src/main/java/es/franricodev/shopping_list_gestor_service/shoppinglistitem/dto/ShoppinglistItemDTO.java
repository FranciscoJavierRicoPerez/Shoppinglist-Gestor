package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.product.dto.ProductDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppinglistItemDTO {
    private Long id;
    private String name;
    private Date assignationToListDate;
    // TODO: REFACTOR -> Modificar modelo para que un ShoppinglistItem SOLO pueda tener un PRODUCT
    private ProductDTO product;
    // TODO: REFACTOR -> Modificar modelo para que un ShoppinglistItem SOLO pueda tener un CALCULATE_SYSTEM
    private CalculateSystemDTO calculateSystem;
    private Double calculatedPrice;
    private List<ItemUnitDTO> itemUnits;
}
