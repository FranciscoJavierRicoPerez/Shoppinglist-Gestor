package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.ApiShoppinglistConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseCreateShoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetFilteredShoppinglistMetadata;
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

    @Operation(summary = ApiShoppinglistConstants.GET_ALL_V1_OP_SUMMARY, deprecated = true)
    @GetMapping(ApiShoppinglistConstants.GET_ALL_V1)
    ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglist();

    @Operation(summary = ApiShoppinglistConstants.CREATE_V1_OP_SUMMARY, deprecated = true)
    @PostMapping(ApiShoppinglistConstants.CREATE_V1)
    ResponseEntity<ShoppinglistDTO> createShoppinglist(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = ApiShoppinglistConstants.CREATE_V2_OP_SUMMARY)
    @PostMapping(ApiShoppinglistConstants.CREATE_V2)
    ResponseEntity<ResponseCreateShoppinglist> createShoppinglistV2(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = ApiShoppinglistConstants.DELETE_V1_OP_SUMMARY, deprecated = true)
    @DeleteMapping(ApiShoppinglistConstants.DELETE_V1)
    ResponseEntity<Boolean> deleteShoppinglist(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long id);

    @Operation(summary = ApiShoppinglistConstants.UPDATE_V1_OP_SUMMARY)
    @PutMapping(ApiShoppinglistConstants.UPDATE_V1)
    ResponseEntity<ShoppinglistDTO> updateShoppinglist(@RequestBody RequestUpdateShoppinglistDTO request);

    @Operation(summary = ApiShoppinglistConstants.FILTER_V1_OP_SUMMARY)
    @GetMapping(ApiShoppinglistConstants.FILTER_V1)
    ResponseEntity<ResponseGetFilteredShoppinglistMetadata> filterShoppinglist(
            @RequestParam(name = ApiShoppinglistConstants.CODE, required = false, defaultValue = "") String code,
            @RequestParam(name = ApiShoppinglistConstants.CREATION_DATE, required = false, defaultValue = "") String creationDate,
            @RequestParam(name = ApiShoppinglistConstants.CLOSE_DATE, required = false, defaultValue = "") String closeDate,
            @RequestParam(name = ApiShoppinglistConstants.TOTAL_PRICE, required = false, defaultValue = "") String totalPrice,
            @RequestParam(name = ApiShoppinglistConstants.IS_ACTIVE, required = false, defaultValue = "true") String isActive);

    @Operation(summary = ApiShoppinglistConstants.GET_DETAILS_V1_OP_SUMMARY, deprecated = true)
    @GetMapping(ApiShoppinglistConstants.GET_DETAILS_V1)
    ResponseEntity<ShoppinglistDetailsDTO> getDetails(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = ApiShoppinglistConstants.UPDATE_IS_ACTIVE_V1_OP_SUMMARY)
    @PutMapping(ApiShoppinglistConstants.UPDATE_IS_ACTIVE_V1)
    ResponseEntity<Boolean> updateShoppinglistIsActiveValue(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = ApiShoppinglistConstants.DELETE_LOGIC_V1_OP_SUMMARY)
    @DeleteMapping(ApiShoppinglistConstants.DELETE_LOGIC_V1)
    ResponseEntity<Boolean> deleteLogicShoppinglist(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);

    @Operation(summary = ApiShoppinglistConstants.ADD_SHOPPINGLIST_ITEM_V1_OP_SUMMARY)
    @PostMapping(ApiShoppinglistConstants.ADD_SHOPPINGLIST_ITEM_V1)
    ResponseEntity<Void> addShoppinglistItemToShoppinglist(
            @PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist,
            @RequestParam(name = ApiShoppinglistConstants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem);

    @Operation(summary = ApiShoppinglistConstants.UPDATE_TOTAL_PRICE_V1_OP_SUMMARY)
    @PutMapping(ApiShoppinglistConstants.UPDATE_TOTAL_PRICE_V1)
    ResponseEntity<Void> updateTotalPrice(@PathVariable(name = ApiShoppinglistConstants.ID_SHOPPINGLIST) Long idShoppinglist);
    
}
