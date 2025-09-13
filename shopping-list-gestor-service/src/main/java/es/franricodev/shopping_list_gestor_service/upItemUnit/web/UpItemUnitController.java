package es.franricodev.shopping_list_gestor_service.upItemUnit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UpItemUnitController {
    @DeleteMapping("/v1/removeUpItemUnitByPrice")
    ResponseEntity<Void> removeUpItemUnitByPrice(@RequestParam(name = "unitaryPrice") Double price);
}
