package es.franricodev.shopping_list_gestor_service.shoppinglist.constants.messages;

import lombok.Getter;

@Getter
public class ErrorMessages {
    public static final String ERR_SHOPPINGLIST_NOT_FOUND = "Shoppinglist not found";

    public static final String ERR_EMPTY_SHOPPINGLIST = "Empty shoppinglist";

    public static final String ERR_SHOPPINGLIST_DETAILS = "Shoppinglist details not found";

    public static final String ERR_SHOPPINGLIST_DETAILS_VIEW_NO_BUILD = "The are some data errors, the shoppinglist details view can't be created";

    public static final String ERR_SHOPPINGLIST_TABLE_NO_BUILD = "The are some data errors, the shoppinglist table can't be crated";
}
