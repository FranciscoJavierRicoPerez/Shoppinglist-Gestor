package es.franricodev.shopping_list_gestor_service.itemUnit.messages;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ItemUnitMessagesError {
    public static final String ITEMUNIT_NO_WP_OR_UP_ITEM_DATA = "No existe informacion ni de precio unitario ni de precio por peso";
    public static final String ITEMUNIT_CREATION_DATA_WITH_ERRORS = "Hay errores en la información indicada para la creación del item, revise los datos proporcionados";

}
