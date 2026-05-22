package es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v1;

public class ApiShoppinglistViewConstants {
    // ------------------- GENERAL CONSTANTS -------------------------
    public static final String API_NAME = "Shoppinglist View Controller API Documentation";
    public static final String API_DESCRIPTION = "Management of all the visible data related with the shoppinglist and his details in the client";
    // ------------------- URL CONSTANTS -----------------------------
    public static final String BASE_URL = "/api/shoppinglistview/";
    public static final String GET_ALL_V1 = "v1/all";
    public static final String GET_DETAILS_V1 = "v1/{idShoppinglist}/details";
    // ------------ PATH VARIABLE CONSTANTS -----------------------------------------------------------
    public static final String ID_SHOPPINGLIST = "idShoppinglist";
    // ------------ OPERATIONS DESCRIPTIONS -----------------------------------------------------------
    public static final String GET_ALL_V1_OP_SUMMARY  = "Get the metainformation necesary for the build of the shoppinglist table view";
    public static final String GET_DETAILS_V1_OP_SUMMARY = "Get the metainformation necessary for the build of the shoppinglist details view";
}
