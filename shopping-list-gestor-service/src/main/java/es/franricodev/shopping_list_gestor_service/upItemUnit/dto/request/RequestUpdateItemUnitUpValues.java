package es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request;

import java.util.List;

public record RequestUpdateItemUnitUpValues(
        List<UpdateItemUnitUpValues> itemUnitUpValues
) {
}
