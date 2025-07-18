package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestAddItemUnitUnitaryPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestAddItemUnitWP;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SHOPPINGLIST_ITEM_CONTROLLER", description = "Management of the operation related with the items of the shoppinglist")
public interface ShoppinglistItemController {

    @Operation(summary = "Creation of the shoppinglist item entity in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseCreateShoppinglistItem.class), mediaType = "application/json")})
    })
    @PostMapping("/v1/{idShoppinglist}/createItem")
    ResponseEntity<ResponseCreateShoppinglistItem> createShoppinglistItem(@PathVariable(name = "idShoppinglist") Long idShoppinglist, @RequestBody RequestCreateShoppinglistItem requestCreateShoppinglistItem);

    @Operation(summary = "Remove a shoppinglist item from the shoppinglist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseDeleteShoppinglistItem.class), mediaType = "application/json")})
    })
    @DeleteMapping("/v1/{idItem}/delete")
    ResponseEntity<ResponseDeleteShoppinglistItem> deleteShoppinglistItem(@PathVariable("idItem") Long idItem);

    @Operation(summary = "Add new item unit of UP type in the shoppinglist item")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitUP")
    ResponseEntity<Void> addItemUnit(@PathVariable("idShoppinglistItem") Long idShoppinglistItem, @RequestBody RequestAddItemUnitUnitaryPrice request);

    @Operation(summary = "Remove a item unit in the shoppinglist item from the shoppinglist")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @DeleteMapping("/v1/{idItem}/removeItemUnit/{idItemUnit}")
    ResponseEntity<?> removeItemUnit(@PathVariable("idItem") Long idItem, @PathVariable("idItemUnit") Long idItemUnit);

    @Operation(summary = "Get all the item units from the shoppinglist items")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseGetAllItemsUnit.class), mediaType = "application/json")})
    })
    @GetMapping("/v1/{idShoppinglistItem}/itemsUnits")
    ResponseEntity<ResponseGetAllItemsUnit> getAllItemUnitsFromShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem);

    @Operation(summary = "Add new item unit of WP type in the shoppinglist item")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/v1/{idShoppinglistItem}/addItemUnitWP")
    ResponseEntity<Void> addItemUnitWPToShoppinglistItem(@PathVariable("idShoppinglistItem") Long idShoppinglistItem, @RequestBody RequestAddItemUnitWP requestAddItemUnitWP);

    @Operation(summary = "Create a new shoppinglist item in a shoppinglist ", description = "This is a V2 endpoint")
    @PostMapping("/v2/{idShoppinglist}/createItem")
    void createItemV2(@PathVariable(name = "idShoppinglist") Long idShoppinglist, @RequestBody RequestCreateShoppinglistItemV2 requestCreateShoppinglistItem);

}
