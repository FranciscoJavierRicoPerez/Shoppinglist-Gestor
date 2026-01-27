package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.CreateItemUnitData;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
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
        name = "Shoppinglist Item Controller API Documentation",
        description = "Management of the operation related with the items of the shoppinglist"
)
public interface ShoppinglistItemController {

    @Operation(summary = "Remove a shoppinglist item from the shoppinglist")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseDeleteShoppinglistItem.class),
                                    mediaType = "application/json")
                    })
    })
    @DeleteMapping("/v1/{idItem}/delete")
    ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(
            @PathVariable("idItem") Long idItem
    );

    @Operation(summary = "Add new item unit of UP type in the shoppinglist item")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    })
    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitUP")
    ResponseEntity<Void> addItemUnit(
            @PathVariable("idShoppinglistItem") Long idShoppinglistItem,
            @RequestBody CreateItemUnitData request
    );

    @Operation(summary = "Remove a item unit in the shoppinglist item from the shoppinglist")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    })
    @DeleteMapping("/v1/{idItem}/removeItemUnit/{idItemUnit}")
    ResponseEntity<?> removeItemUnit(
            @PathVariable("idItem") Long idItem,
            @PathVariable("idItemUnit") Long idItemUnit
    );

    @Operation(summary = "Get all the item units from the shoppinglist items")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseGetAllItemsUnit.class),
                                    mediaType = "application/json"
                            )
                    })
    })
    @GetMapping("/v1/{idShoppinglistItem}/itemsUnits")
    ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(
            @PathVariable("idShoppinglistItem") Long idShoppinglistItem
    );

    @Operation(summary = "Add new item unit of WP type in the shoppinglist item")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            )
    })
    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitWP")
    ResponseEntity<Void> addItemUnitWPToShoppinglistItem(
            @PathVariable("idShoppinglistItem") Long idShoppinglistItem,
            @RequestBody RequestAddItemUnitWP requestAddItemUnitWP
    );

    @Operation(
            summary = "Returns all the items units of the shoppinglist item type UP grouped by price"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseGetAllItemUnitUpGroupedByPrice.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/v1/{idShoppinglistItem}/itemsUnitsUpGroupedByPrice")
    ResponseEntity<ResponseGetAllItemUnitUpGroupedByPrice> getItemsUnitsUpGroupedByPrice(
            @PathVariable(name = "idShoppinglistItem") Long idShoppinglistItem);

    @Operation(summary = "Returns the metadata of the items units wp")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseItemUnitWpMetadata.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/v1/{idShoppinglistItem}/itemsUnitsWpInfo")
    ResponseEntity<ResponseItemUnitWpMetadata> getItemsUnitsWpMetadata(
            @PathVariable(name = "idShoppinglistItem") Long idShoppinglistItem);

    @Operation(summary = "Creation of the shoppinglist item metadata")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseCreateShoppinglistItem.class)
                            )
                    }
            )
    })
    @PostMapping("/v1/createShoppinglistItemMetadata")
    ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItemMetadata(
            @RequestBody RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem);


    /* @GetMapping("/v1/{idShoppinglistItem}/itemsUnitWpInfo")
    ResponseEntity<ResponseGetItemUnitWpMetadata> getItemUnitWpMetadata(@PathVariable(name = "idShoppinglistItem") Long idShoppinglistItem); */

}
