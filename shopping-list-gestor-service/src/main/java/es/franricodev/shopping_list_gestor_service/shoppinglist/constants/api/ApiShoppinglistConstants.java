package es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api;

// Constants related with the SHOPPINGLIST API documentation
public class ApiShoppinglistConstants {
    // --------------- GENERAL CONSTANTS -------------------------------------------------------------
    public static final String API_NAME = "Shoppinglist Controller API Documentation";
    public static final String API_DESCRIPTION = "Management of the main functionalities related with the shoppinglist entity";
    // --------------- URL CONSTANTS -----------------------------------------------------------------
    public static final String BASE_URL = "/api/shoppinglist";
    public static final String GET_ALL_V1 = "/v1/all";
    public static final String CREATE_V1 = "/v1/create";
    public static final String CREATE_V2 = "/v2/create";
    public static final String DELETE_V1 = "/v1/delete/{idShoppinglist}";
    public static final String UPDATE_V1 = "/v1/update";
    public static final String FILTER_V1 = "/v1/filter";
    public static final String GET_DETAILS_V1 = "/v1/{idShoppinglist}/details";
    public static final String UPDATE_IS_ACTIVE_V1 = "/v1/{idShoppinglist}/update/isActive";
    public static final String DELETE_LOGIC_V1 = "/v1/{idShoppinglist}/delete/logic";
    public static final String ADD_SHOPPINGLIST_ITEM_V1 = "/v1/{idShoppinglist}/addShoppinglistItem";
    public static final String UPDATE_TOTAL_PRICE_V1 = "/v1/{idShoppinglist}/updateTotalPrice";
    // ------------ PATH VARIABLE CONSTANTS -----------------------------------------------------------
    public static final String ID_SHOPPINGLIST = "idShoppinglist";
    // ------------ REQUEST PARAMS CONSTANTS ----------------------------------------------------------
    public static final String CODE = "code";
    public static final String CREATION_DATE = "creationDate";
    public static final String CLOSE_DATE = "closeDate";
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String IS_ACTIVE = "isActive";
    public static final String ID_SHOPPINGLIST_ITEM = "idShoppinglistItem";
    // ------------ OPERATIONS DESCRIPTIONS -----------------------------------------------------------
    public static final String GET_ALL_V1_OP_SUMMARY = "Get all shoppinglist in the database";
    public static final String CREATE_V1_OP_SUMMARY = "Create a shoppinglist v1";
    public static final String CREATE_V2_OP_SUMMARY = "Create a shoppinglist v2";
    public static final String DELETE_V1_OP_SUMMARY = "Delete the information of a shoppinglist";
    public static final String UPDATE_V1_OP_SUMMARY = "Update the information of a shoppinglist";
    public static final String FILTER_V1_OP_SUMMARY = "Provides the information on all shoppinglists whose data is consistent with the filters";
    public static final String GET_DETAILS_V1_OP_SUMMARY = "Provides the details of a shoppinglist";
    public static final String UPDATE_IS_ACTIVE_V1_OP_SUMMARY = "Update the shoppinglist active value";
    public static final String DELETE_LOGIC_V1_OP_SUMMARY = "Logic deletion of the shoppinglist entity";
    public static final String ADD_SHOPPINGLIST_ITEM_V1_OP_SUMMARY = "Add a new ShoppinglistItem to the Shoppinglist";
    public static final String UPDATE_TOTAL_PRICE_V1_OP_SUMMARY = "Update the total price of the shoppinglist";
}
