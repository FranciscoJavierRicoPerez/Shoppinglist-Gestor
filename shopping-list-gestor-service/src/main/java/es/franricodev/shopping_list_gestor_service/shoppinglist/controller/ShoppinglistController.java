package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.ApiShoppinglistConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseCreateShoppinglist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = ApiShoppinglistConstants.API_NAME,
        description = ApiShoppinglistConstants.API_DESCRIPTION
)
public interface ShoppinglistController {

    @Deprecated
    @Operation(summary = "Get all shoppinglist in the database")
    @GetMapping(ApiShoppinglistConstants.GET_ALL_V1)
    ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglist();

    @Operation(summary = "Create a shoppinglist v1")
    @PostMapping(ApiShoppinglistConstants.CREATE_V1)
    ResponseEntity<ShoppinglistDTO> createShoppinglist(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = "Create a shoppinglist v2")
    @PostMapping(ApiShoppinglistConstants.CREATE_V2)
    ResponseEntity<ResponseCreateShoppinglist> createShoppinglistV2(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = "Delete the information of a shoppinglist")
    @DeleteMapping(ApiShoppinglistConstants.DELETE_V1)
    ResponseEntity<Boolean> deleteShoppinglist(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long id);

    @Operation(summary = "Update the information of a shoppinglist")
    @PutMapping(ApiShoppinglistConstants.UPDATE_V1)
    ResponseEntity<ShoppinglistDTO> updateShoppinglist(@RequestBody RequestUpdateShoppinglistDTO request);

    //@Deprecated
    @Operation(summary = "Provides the information on all shoppinglists whose data is consistent with the filters")
    @GetMapping(ApiShoppinglistConstants.FILTER_V1)
    ResponseEntity<List<ShoppinglistDTO>> filterShoppinglist(
            @RequestParam(name = ApiShoppinglistConstants.CODE, required = false) String code,
            @RequestParam(name = ApiShoppinglistConstants.CREATION_DATE, required = false) String creationDate,
            @RequestParam(name = ApiShoppinglistConstants.CLOSE_DATE, required = false) String closeDate,
            @RequestParam(name = ApiShoppinglistConstants.TOTAL_PRICE, required = false) Double totalPrice,
            @RequestParam(name = ApiShoppinglistConstants.IS_ACTIVE, required = false) Boolean isActive);

    @Operation(summary = "Provides the details of a shoppinglist")
    @GetMapping(ApiShoppinglistConstants.GET_DETAILS_V1)
    ResponseEntity<ShoppinglistDetailsDTO> getDetails(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = "Update the shoppinglist active value")
    @PutMapping(ApiShoppinglistConstants.UPDATE_IS_ACTIVE_V1)
    ResponseEntity<Boolean> updateShoppinglistIsActiveValue(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = "Logic deletion of the shoppinglist entity")
    @DeleteMapping(ApiShoppinglistConstants.DELETE_LOGIC_V1)
    ResponseEntity<Boolean> deleteLogicShoppinglist(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = "Add a new ShoppinglistItem to the Shoppinglist")
    @PostMapping(ApiShoppinglistConstants.ADD_SHOPPINGLIST_ITEM_V1)
    ResponseEntity<Void> addShoppinglistItemToShoppinglist(
            @PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist,
            @RequestParam(name = ApiShoppinglistConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem);

    @Operation(summary = "Update the total price of the shoppinglist")
    @PutMapping(ApiShoppinglistConstants.UPDATE_TOTAL_PRICE_V1)
    ResponseEntity<Void> updateTotalPrice(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);
    
}
