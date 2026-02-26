package es.franricodev.shopping_list_gestor_service.upItemUnit.web;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestUpdateItemUnitUpValues;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UpItemUnitController {
    @DeleteMapping("/v1/removeUpItemUnitByPrice")
    ResponseEntity<Void> removeUpItemUnitByPrice(@RequestParam(name = "unitaryPrice") Double price);

    @PutMapping("/v1/updateItemUnitsUp")
    ResponseEntity<Void> updateItemUnitUpValues(@RequestBody RequestUpdateItemUnitUpValues request);

}
