package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppinglistController {

    @Autowired
    private ShoppinglistService shoppinglistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppinglistController.class);

    @GetMapping("/v1")
    public ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglistActive() {
        LOGGER.info("Getting all actives shoppinglists");
        HttpStatus httpStatus = HttpStatus.OK;
        List<ShoppinglistDTO> shoppinglistDTOS = new ArrayList<>();
        try {
            shoppinglistDTOS = shoppinglistService.findActiveShoppinglists();
        } catch (ShoppinglistException e) {
            LOGGER.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(shoppinglistDTOS, httpStatus);
    }

    @PostMapping("/v1/create")
    public ResponseEntity<ShoppinglistDTO> createShoppinglist(@NotNull @RequestBody RequestCreateShoppinglistDTO request) {
        LOGGER.info("Creation of the new shoppinglist");
        return new ResponseEntity<>(shoppinglistService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<?> deleteShoppinglist(@PathVariable Long id) {
        LOGGER.info("Delete of the shoppinglist with id: {}", id);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistService.deleteShoppinglist(id);
        }catch (ShoppinglistException e) {
            LOGGER.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ShoppinglistDTO> updateShoppinglist(@RequestBody RequestUpdateShoppinglistDTO request) {
        LOGGER.info("Update the shoppinglist with id: {}", request.getId());
        HttpStatus httpStatus = HttpStatus.CREATED;
        ShoppinglistDTO updated = null;
        try {
            updated = shoppinglistService.updateShoppinglist(request);
        } catch (ShoppinglistException e) {
            LOGGER.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(updated, httpStatus);
    }



}
