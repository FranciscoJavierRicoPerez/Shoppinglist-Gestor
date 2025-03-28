package es.franricodev.shopping_list_gestor_service.shoppinglist.message;

import lombok.Getter;

@Getter
public class ErrorMessages {
    public static final String ERR_SHOPPINGLIST_NOT_FOUND = "Shoppinglist not found";

    public static final String ERR_EMPTY_SHOPPINGLIST = "Empty shoppinglist";

    public static final String ERR_SHOPPINGLIST_DETAILS = "Shoppinglist details not found";
}
