package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ResponseGetAllItemsUnit {
    List<ItemUnitDTO> itemUnitList;
    String message;
}
