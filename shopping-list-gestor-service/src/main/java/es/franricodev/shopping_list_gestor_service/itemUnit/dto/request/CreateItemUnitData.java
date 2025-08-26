package es.franricodev.shopping_list_gestor_service.itemUnit.dto.request;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestCreateUpItemUnitData;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestCreateWpItemUnitData;
import lombok.Data;

@Data
public class CreateItemUnitData {
    // Indicate if the user want to create a item unit or not
    private boolean createItemUnit;
    // References the data for the creation of a UP item unit
    private RequestCreateUpItemUnitData createUpItemUnitData;
    // References the data for the creation of a WP item unit
    private RequestCreateWpItemUnitData createWpItemUnitData;
}
