package es.franricodev.shopping_list_gestor_service.itemUnit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(
        name = "Item Unit Controller API Documentation"
)
public interface ItemUnitController {
    @Operation(summary = "Update the values for and item unit")
    @PutMapping("/v1/itemUnit/{idItemUnit}/update")
    ResponseEntity<Void> updateItemUnit(@PathVariable(name = "idItemUnit") Long idItemUnit);
}
