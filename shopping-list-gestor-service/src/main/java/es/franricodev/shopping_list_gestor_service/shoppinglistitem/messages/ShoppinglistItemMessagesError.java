package es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShoppinglistItemMessagesError {

    public static final String SHOPPINGLISTITEM_CREATE_ERR = "Ha sucedido un error en la creación del item";
    public static final String SHOPPINGLISTITEM_NOT_FOUND_ERR = "No se ha encontrado el item";
    public static final String SHOPPINGLISTITEM_DELETED_ERR = "El item no ha podido ser borrado correctamente";
    public static final String SHOPPINGLISTEM_INCORRRECT_CALCULATE_SYSTEM = "El sistema de calculo no es el esperado";
    public static final String SHOPPINGLISITEM_DELETE_ITEM_UNIT_ERR = "Error borrando un item unit de la lista de items";
    public static final String SHOPPINGLISTITEM_GENERIC_ERR = "Error generico en proceso";
    public static final String SHOPPINGLISTITEM_WITHOUT_ITEM_UNITS = "El item no tiene asociado nigun item unit";
}
