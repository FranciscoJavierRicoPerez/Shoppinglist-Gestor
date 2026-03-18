package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.constants.api.ApiShoppinglistItemConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.*;
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
        name = ApiShoppinglistItemConstants.API_NAME,
        description = ApiShoppinglistItemConstants.API_DESCRIPTION
)
public interface ShoppinglistItemController {

    @Operation(summary = ApiShoppinglistItemConstants.DELETE_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseDeleteShoppinglistItem.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON)
                    })
    })
    @DeleteMapping(ApiShoppinglistItemConstants.DELETE_V1)
    ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(
            @PathVariable(ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistItemConstants.ADD_ITEM_UNIT_UP_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_204,
                    description = GeneralConstants.DESC_204
            )
    })
    @PostMapping(ApiShoppinglistItemConstants.ADD_ITEM_UNIT_UP_V1)
    ResponseEntity<Void> addItemUnit(
            @PathVariable(ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @RequestBody CreateItemUnitData request
    );

    @Operation(summary = ApiShoppinglistItemConstants.REMOVE_ITEM_UNIT_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_204,
                    description = GeneralConstants.DESC_204
            )
    })
    @DeleteMapping(ApiShoppinglistItemConstants.REMOVE_ITEM_UNIT_V1)
    ResponseEntity<?> removeItemUnit(
            @PathVariable(ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @PathVariable(ApiShoppinglistItemConstants.ID_ITEM_UNIT) Long idItemUnit
    );

    @Operation(summary = ApiShoppinglistItemConstants.GET_ALL_ITEM_UNITS_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseGetAllItemsUnit.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    })
    })
    @GetMapping(ApiShoppinglistItemConstants.GET_ALL_ITEM_UNITS_V1)
    ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(
            @PathVariable(ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );

    @Operation(summary = ApiShoppinglistItemConstants.ADD_ITEM_UNIT_WP_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_204,
                    description = GeneralConstants.DESC_204
            )
    })
    @PostMapping(ApiShoppinglistItemConstants.ADD_ITEM_UNIT_WP_V1)
    ResponseEntity<Void> addItemUnitWPToShoppinglistItem(
            @PathVariable(ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem,
            @RequestBody RequestAddItemUnitWP requestAddItemUnitWP
    );

    @Operation(
            summary = ApiShoppinglistItemConstants.GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V1_OP_SUMMARY
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    description = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseGetAllItemUnitUpGroupedByPrice.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            )
    })
    @GetMapping(ApiShoppinglistItemConstants.GET_ALL_ITEMS_UNIT_UP_GROUPED_BY_PRICE_V1)
    ResponseEntity<ResponseGetAllItemUnitUpGroupedByPrice> getItemsUnitsUpGroupedByPrice(
            @PathVariable(name = ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem);

    @Operation(summary = ApiShoppinglistItemConstants.GET_ITEM_UNITS_WP_METADATA_V1_OP_SUMAMRY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    description = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseItemUnitWpMetadata.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            )
    })
    @GetMapping(ApiShoppinglistItemConstants.GET_ITEM_UNITS_WP_METADATA_V1)
    ResponseEntity<ResponseItemUnitWpMetadata> getItemsUnitsWpMetadata(
            @PathVariable(name = ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem);

    @Operation(summary = ApiShoppinglistItemConstants.CREATE_SHOPPINGLIST_ITEM_METADATA_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_201,
                    description = GeneralConstants.DESC_201,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseCreateShoppinglistItem.class)
                            )
                    }
            )
    })
    @PostMapping(ApiShoppinglistItemConstants.CREATE_SHOPPINGLIST_ITEM_METADATA_V1)
    ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItemMetadata(
            @RequestBody RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem);


    @Operation(summary = ApiShoppinglistItemConstants.GET_ITEM_UNITS_WP_METADATA_V2_OP_SUMAMRY)
    @GetMapping(ApiShoppinglistItemConstants.GET_ITEM_UNITS_WP_METADATA_V2)
    ResponseEntity<ResponseGetItemUnitWpMetadata> getItemUnitWpMetadata(@PathVariable(name = ApiShoppinglistItemConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem);

}
