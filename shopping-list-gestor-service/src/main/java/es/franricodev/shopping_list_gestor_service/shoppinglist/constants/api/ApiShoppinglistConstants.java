package es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api;

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
    // -------------------------------------------------------------------------------------------------

}
