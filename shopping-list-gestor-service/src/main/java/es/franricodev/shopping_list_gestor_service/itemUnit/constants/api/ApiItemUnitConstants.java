package es.franricodev.shopping_list_gestor_service.itemUnit.constants.api;

public class ApiItemUnitConstants {
    // --------------------- GENERAL CONSTANTS ----------------------
    public static final String API_NAME = "Item Unit Controller API Documentation";
    public static final String API_DESCRIPTION = "Management of the operations related with the items unit";
    // --------------------- URL CONSTANTS --------------------------
    public static final String BASE_URL = "/api/itemunit";
    public static final String UPDATE_ITEM_UNIT_TOTAL_PRICE_V1 = "/v1/{idItemUnit}/update/totalPrice";
    // --------------------- PATH VARIABLE CONSTANTS ----------------
    public static final String ID_ITEM_UNIT = "idItemUnit";
    // --------------------- OPERATIONS DESCRIPTIONS ----------------
    public static final String UPDATE_ITEM_UNIT_TOTAL_PRICE_V1_OP_SUMMARY = "Update the total price of the item unit indicated in the path";
}
