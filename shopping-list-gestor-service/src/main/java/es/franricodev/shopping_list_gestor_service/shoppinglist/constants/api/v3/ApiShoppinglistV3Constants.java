package es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3;

public class ApiShoppinglistV3Constants {
    // --------------- GENERAL CONSTANTS -------------------------------------------------------------
    public static final String API_NAME = "Shoppinglist Controller [Version 3] API Documentation";
    public static final String API_DESCRIPTION = "Management of the main functionalities related with the shoppinglist entity";
    // --------------- URL CONSTANTS -----------------------------------------------------------------
    public static final String BASE_URL = "/api/shoppinglist/v3";
    public static final String DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/delete";
    public static final String ADD_ITEM_UNIT_UP_TO_SHOPPINGLIST_ITEM_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/itemUnit/{idItemUnit}/addUPItemUnit";
    public static final String DELETE_ITEM_UNIT_FROM_SHOPPINGLIST_ITEM_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/itemUnit/{idItemUnit}/removeItemUnit";
    public static final String GET_ALL_ITEM_UNITS_FROM_SHOPPINGLIST_ITEM_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/getAllItemUnits";
    public static final String ADD_ITEM_UNIT_WP_TO_SHOPPINGLOST_ITEM_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/addWPItemUnit";
    public static final String GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/getItemsUnitUpGropuedByPrice";
    public static final String GET_ITEM_UNITS_WP_METADATA_V3 = "/{idShoppinglist}/shoppinglistItem/{idShoppinglistItem}/getItemUnitWPMetadata";
    // ------------ PATH VARIABLE CONSTANTS -----------------------------------------------------------
    public static final String ID_SHOPPINGLIST = "idShoppinglist";
    public static final String ID_SHOPPINGLIST_ITEM = "idShoppinglistItem";
    public static final String ID_ITEM_UNIT = "idItemUnit";
    // ------------ REQUEST PARAMS CONSTANTS ----------------------------------------------------------
    // ------------ OPERATIONS DESCRIPTIONS -----------------------------------------------------------
    public static final String DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3_OP_SUMMARY = "Logic delete of a shoppinglist item from an specific shoppinglist";
}
