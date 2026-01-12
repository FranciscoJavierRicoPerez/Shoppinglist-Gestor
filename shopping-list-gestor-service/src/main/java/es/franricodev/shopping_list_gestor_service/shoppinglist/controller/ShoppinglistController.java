package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

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
        name = "Shoppinglist Controller API Documentation",
        description = "Management of the database modifications operations (Create, Update, Delete) related with the shoppinglist entity"
)
public interface ShoppinglistController {

    @Deprecated
    @Operation(summary = "Get all shoppinglist in the database")
    @GetMapping("/v1")
    ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglist();

    @Operation(summary = "Create a shoppinglist")
    @PostMapping("/v1/create")
    ResponseEntity<ShoppinglistDTO> createShoppinglist(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = "Create a shoppinglist")
    @PostMapping("/v2/create")
    ResponseEntity<ResponseCreateShoppinglist> createShoppinglistV2(@NotNull @RequestBody RequestCreateShoppinglistDTO request);

    @Operation(summary = "Delete the information of a shoppinglist")
    @DeleteMapping("/v1/delete/{id}")
    ResponseEntity<Boolean> deleteShoppinglist(@PathVariable Long id);

    @Operation(summary = "Update the information of a shoppinglist")
    @PutMapping("/v1/update")
    ResponseEntity<ShoppinglistDTO> updateShoppinglist(@RequestBody RequestUpdateShoppinglistDTO request);

    //@Deprecated
    @Operation(summary = "Provides the information on all shoppinglists whose data is consistent with the filters")
    @GetMapping("/v1/filter")
    ResponseEntity<List<ShoppinglistDTO>> filterShoppinglist(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "creationDate", required = false) String creationDate,
            @RequestParam(name = "closeDate", required = false) String closeDate,
            @RequestParam(name = "totalPrice", required = false) Double totalPrice,
            @RequestParam(name = "isActive", required = false) Boolean isActive);

    @Operation(summary = "Provides the details of a shoppinglist")
    @GetMapping("/v1/{id}/details")
    ResponseEntity<ShoppinglistDetailsDTO> getDetails(@PathVariable(name = "id") Long idShoppinglist);

    @Operation(summary = "Update the shoppinglist active value")
    @PutMapping("/v1/{id}/update/isActive")
    ResponseEntity<Boolean> updateShoppinglistIsActiveValue(@PathVariable(name = "id") Long idShoppinglist);

    @Operation(summary = "Logic deletion of the shoppinglist entity")
    @DeleteMapping("/v1/{id}/delete")
    ResponseEntity<Boolean> deleteLogicShoppinglist(@PathVariable(name = "id") Long idShoppinglist);

    @Operation(summary = "Add a new ShoppinglistItem to the Shoppinglist")
    @PostMapping("/v1/{idShoppinglist}/addShoppinglistItem")
    ResponseEntity<Void> addShoppinglistItemToShoppinglist(
            @PathVariable(name = "idShoppinglist") Long idShoppinglist,
            @RequestParam(name = "idShoppinglistItem") Long idShoppinglistItem);

    @Operation(summary = "Update the total price of the shoppinglist")
    @PutMapping("/v1/{id}/updateTotalPrice")
    ResponseEntity<Void> updateTotalPrice(@PathVariable(name = "id") Long idShoppinglist);
    
}
