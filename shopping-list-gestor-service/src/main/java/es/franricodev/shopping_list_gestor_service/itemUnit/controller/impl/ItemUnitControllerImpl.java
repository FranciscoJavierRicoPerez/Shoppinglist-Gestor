package es.franricodev.shopping_list_gestor_service.itemUnit.controller.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.controller.ItemUnitController;
import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/itemunit")
public class ItemUnitControllerImpl implements ItemUnitController {

    @Autowired
    private ItemUnitService itemUnitService;

    @DeleteMapping("/{idItemUnit}/removeItemUnit")
    public ResponseEntity<?> removeItemUnit(@PathVariable("idItemUnit") Long id) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> updateItemUnit(Long idItemUnit) {
        log.info("Updating the item unit {} values", idItemUnit);
        return null;
    }
}
