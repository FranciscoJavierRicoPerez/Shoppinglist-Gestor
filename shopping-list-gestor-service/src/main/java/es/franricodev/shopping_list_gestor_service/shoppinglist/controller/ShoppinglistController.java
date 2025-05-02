package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
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

@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppinglistController {

    @Autowired
    private ShoppinglistService shoppinglistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppinglistController.class);

    @GetMapping("/v1")
    public ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglist() {
        LOGGER.info("Getting all actives shoppinglists");
        HttpStatus httpStatus = HttpStatus.OK;
        List<ShoppinglistDTO> shoppinglistDTOS = new ArrayList<>();
        try {
            shoppinglistDTOS = shoppinglistService.findAllShoppinglists();
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

    @GetMapping("/v1/filter")
    public ResponseEntity<List<ShoppinglistDTO>> filterShoppinglist(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "creationDate", required = false) String creationDate,
            @RequestParam(name = "closeDate", required = false) String closeDate,
            @RequestParam(name = "totalPrice", required = false) Double totalPrice,
            @RequestParam(name = "isActive", required = false) Boolean isActive) {
        LOGGER.info("Filter shoppinglists");
        HttpStatus httpStatus = HttpStatus.OK;
        List<ShoppinglistDTO> shoppinglistDTOList = null;
        try {
            shoppinglistDTOList = shoppinglistService.filterShoppinglist(RequestFilterShoppinglistDTO
                    .builder()
                    .createDate(creationDate)
                    .closeDate(closeDate)
                    .totalPrice(totalPrice)
                    .code(code)
                    .isActive(isActive)
                    .build());
        } catch (ShoppinglistException e) {
            LOGGER.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(shoppinglistDTOList, httpStatus);
    }

    @GetMapping("/v1/{id}/details")
    public ResponseEntity<ShoppinglistDetailsDTO> getDetails(@PathVariable(name = "id") Long idShoppinglist) {
       LOGGER.info("Get shoppinglist details");
       HttpStatus httpStatus = HttpStatus.OK;
       ShoppinglistDetailsDTO shoppinglistDetailsDTO = null;
       try {
            shoppinglistDetailsDTO = shoppinglistService.getShoppinglistDetails(idShoppinglist);
       }catch (ShoppinglistException e){
           LOGGER.error(e.getMessage());
           httpStatus = HttpStatus.BAD_REQUEST;
       }
       return new ResponseEntity<>(shoppinglistDetailsDTO, httpStatus);
    }

    @PutMapping("/v1/{id}/update/isActive")
    public ResponseEntity<Boolean> updateShoppinglistIsActiveValue(@PathVariable(name = "id") Long idShoppinglist) {
        LOGGER.info("Update the value isActive of the shoppinglist with id: {}", idShoppinglist);
        HttpStatus httpStatus = HttpStatus.OK;
        ShoppinglistDTO shoppinglistDTO = null;
        try {
            shoppinglistDTO = shoppinglistService.updateShoppinglistIsActive(idShoppinglist);
        } catch (ShoppinglistException e) {
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<>(shoppinglistDTO != null, httpStatus);
    }
}
