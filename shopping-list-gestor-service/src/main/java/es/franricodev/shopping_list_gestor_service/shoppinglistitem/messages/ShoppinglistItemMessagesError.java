package es.franricodev.shopping_list_gestor_service.shoppinglistitem.messages;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShoppinglistItemMessagesError {

    public static final String SHOPPINGLISTITEM_CREATE_ERR = "Ha sucedido un error en la creación del item";
    public static final String SHOPPINGLISTITEM_NOT_FOUND_ERR = "No se ha encontrado el item";
    public static final String SHOPPINGLISTITEM_DELETED_ERR = "El item no ha podido ser borrado correctamente";
}
