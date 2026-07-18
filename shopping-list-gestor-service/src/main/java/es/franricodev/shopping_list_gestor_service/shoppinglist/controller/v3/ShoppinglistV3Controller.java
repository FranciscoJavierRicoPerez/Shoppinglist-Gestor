package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseError;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestUpdateShoppinglistItemItemUnitsUp;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = ApiShoppinglistV3Constants.API_NAME,
        description = ApiShoppinglistV3Constants.API_DESCRIPTION
)
public interface ShoppinglistV3Controller {
    @Operation(summary = ApiShoppinglistV3Constants.DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3_OP_SUMMARY)
    @ApiResponses(
            {
                @ApiResponse(
                        responseCode = GeneralConstants.HTTP_200,
                        description = GeneralConstants.DESC_200,
                        content = {
                                @Content(
                                        schema = @Schema(implementation = ResponseDeleteShoppinglistItem.class),
                                        mediaType = GeneralConstants.APPLICATION_JSON)
                        }),
                @ApiResponse(
                        responseCode = GeneralConstants.HTTP_409,
                        description = GeneralConstants.DESC_409,
                        content = {
                                @Content(
                                        schema = @Schema(implementation = ResponseError.class),
                                        mediaType = GeneralConstants.APPLICATION_JSON
                                )
                        }
                ),
                @ApiResponse (
                        responseCode = GeneralConstants.HTTP_404,
                        description = GeneralConstants.DESC_404,
                        content = {
                                @Content(
                                        schema = @Schema(implementation = ResponseError.class),
                                        mediaType = GeneralConstants.APPLICATION_JSON
                                )
                        }
                )
           }
    )
    @DeleteMapping(ApiShoppinglistV3Constants.DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3)
    ResponseEntity<?> deleteShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistV3Constants.ADD_ITEM_UNIT_UP_TO_SHOPPINGLIST_ITEM_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_201,
                            description = GeneralConstants.DESC_201,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @PostMapping(ApiShoppinglistV3Constants.ADD_ITEM_UNIT_UP_TO_SHOPPINGLIST_ITEM_V3)
    ResponseEntity<?> addItemUnitUpToShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @RequestBody CreateItemUnitData request
    );


    @Operation(summary = ApiShoppinglistV3Constants.DELETE_ITEM_UNIT_FROM_SHOPPINGLIST_ITEM_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_200,
                            description = GeneralConstants.DESC_200,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @DeleteMapping(ApiShoppinglistV3Constants.DELETE_ITEM_UNIT_FROM_SHOPPINGLIST_ITEM_V3)
    ResponseEntity<?> deleteItemUnitFromShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @PathVariable(ApiShoppinglistV3Constants.ID_ITEM_UNIT) Long idItemUnit
    );

    @Operation(summary = ApiShoppinglistV3Constants.GET_ALL_ITEM_UNITS_FROM_SHOPPINGLIST_ITEM_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_200,
                            description = GeneralConstants.DESC_200,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseGetAllItemsUnit.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @GetMapping(ApiShoppinglistV3Constants.GET_ALL_ITEM_UNITS_FROM_SHOPPINGLIST_ITEM_V3)
    ResponseEntity<?> getAllItemUnitsFromShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistV3Constants.ADD_ITEM_UNIT_WP_TO_SHOPPINGLIST_ITEM_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_201,
                            description = GeneralConstants.DESC_201,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @PostMapping(ApiShoppinglistV3Constants.ADD_ITEM_UNIT_WP_TO_SHOPPINGLIST_ITEM_V3)
    ResponseEntity<?> addItemUnitWpToShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @RequestBody RequestAddItemUnitWP requestAddItemUnitWP
    );

    @Operation(summary = ApiShoppinglistV3Constants.GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_200,
                            description = GeneralConstants.DESC_200,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseGetAllItemUnitUpGroupedByPrice.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @GetMapping(ApiShoppinglistV3Constants.GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V3)
    ResponseEntity<?> getAllItemsUnitUpGroupedByPrice(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistV3Constants.GET_ITEM_UNITS_WP_METADATA_V3_OP_SUMAMRY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_200,
                            description = GeneralConstants.DESC_200,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseItemUnitWpMetadata.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @GetMapping(ApiShoppinglistV3Constants.GET_ITEM_UNITS_WP_METADATA_V3)
    ResponseEntity<?> getItemUnitsWpMetadata(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistV3Constants.UPDATE_SHOPPINGLIST_ITEM_UP_ITEM_UNIT_DATA_V3_OP_SUMMARY)
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_200,
                            description = GeneralConstants.DESC_200,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = Void.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = GeneralConstants.HTTP_409,
                            description = GeneralConstants.DESC_409,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    ),
                    @ApiResponse (
                            responseCode = GeneralConstants.HTTP_404,
                            description = GeneralConstants.DESC_404,
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseError.class),
                                            mediaType = GeneralConstants.APPLICATION_JSON
                                    )
                            }
                    )
            }
    )
    @PutMapping(ApiShoppinglistV3Constants.UPDATE_SHOPPINGLIST_ITEM_UP_ITEM_UNIT_DATA_V3)
    ResponseEntity<?> updateItemUnitUpDataFromShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @RequestBody RequestUpdateShoppinglistItemItemUnitsUp request
    );

}
