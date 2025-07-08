package es.franricodev.shopping_list_gestor_service.itemUnit.controller;

import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/itemunit")
public class ItemUnitController {

    @Autowired
    private ItemUnitService itemUnitService;

    @DeleteMapping("/{idItemUnit}/removeItemUnit")
    public ResponseEntity<?> removeItemUnit(@PathVariable("idItemUnit") Long id) {
        return ResponseEntity.ok(null);
    }

}
