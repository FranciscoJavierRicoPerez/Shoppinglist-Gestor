package es.franricodev.shopping_list_gestor_service.shoppinglistitem.constants.api;

public class ApiShoppinglistItemConstants {
    // -------------------- GENERAL CONSTANTS ----------------------
    public static final String API_NAME = "Shoppinglist Item Controller API Documentation";
    public static final String API_DESCRIPTION = "Management of the operation related with the items of the shoppinglist";
    // -------------------- URL CONSTANTS --------------------------
    public static final String BASE_URL = "/api/shoppinglistitem";
    public static final String DELETE_V1 = "/v1/{idShoppinglistItem}/delete";
    public static final String ADD_ITEM_UNIT_UP_V1 = "/v1/{idShoppinglistItem}/addItemUnitUP";
    public static final String REMOVE_ITEM_UNIT_V1 = "/v1/{idShoppinglistItem}/removeItemUnit/{idItemUnit}";
    public static final String GET_ALL_ITEM_UNITS_V1 = "/v1/{idShoppinglistItem}/itemsUnits";
    public static final String ADD_ITEM_UNIT_WP_V1 = "/v1/{idShoppinglistItem}/addItemUnitWP";
    public static final String GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V1 = "/v1/{idShoppinglistItem}/itemsUnitsUpGroupedByPrice";
    public static final String GET_ITEM_UNITS_WP_METADATA_V1 = "/v1/{idShoppinglistItem}/itemsUnitsWpInfo";
    public static final String CREATE_SHOPPINGLIST_ITEM_METADATA_V1 = "/v1/createShoppinglistItemMetadata";
    public static final String GET_ITEM_UNITS_WP_METADATA_V2 = "/v2/{idShoppinglistItem}/itemsUnitWpInfo";
    // -------------------- PATH VARIABLE CONSTANTS ----------------
    public static final String ID_SHOPPINGLIST_ITEM = "idShoppinglistItem";
    public static final String ID_ITEM_UNIT = "idItemUnit";
    // -------------------- OPERATIONS DESCRIPTIONS ----------------
    public static final String DELETE_V1_OP_SUMMARY = "Remove a shoppinglist item from the shoppinglist";
    public static final String ADD_ITEM_UNIT_UP_V1_OP_SUMMARY = "Add new item unit of UP type in the shoppinglist item";
    public static final String REMOVE_ITEM_UNIT_V1_OP_SUMMARY = "Remove a item unit in the shoppinglist item from the shoppinglist";
    public static final String GET_ALL_ITEM_UNITS_V1_OP_SUMMARY = "Get all the item units from the shoppinglist items";
    public static final String ADD_ITEM_UNIT_WP_V1_OP_SUMMARY = "Add new item unit of WP type in the shoppinglist item";
    public static final String GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V1_OP_SUMMARY = "Returns all the items units of the shoppinglist item type UP grouped by price";
    public static final String GET_ITEM_UNITS_WP_METADATA_V1_OP_SUMAMRY = "Returns the metadata of the items units wp";
    public static final String CREATE_SHOPPINGLIST_ITEM_METADATA_V1_OP_SUMMARY = "Creation of the shoppinglist item metadata";
    public static final String GET_ITEM_UNITS_WP_METADATA_V2_OP_SUMAMRY = "Return the information of a wp item unit";

}
