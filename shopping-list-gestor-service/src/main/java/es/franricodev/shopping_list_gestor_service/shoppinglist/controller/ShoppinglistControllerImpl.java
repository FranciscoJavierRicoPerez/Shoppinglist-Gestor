package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppinglistControllerImpl implements ShoppinglistController{

    @Autowired
    private ShoppinglistService shoppinglistService;

    @GetMapping("/v1")
    public ResponseEntity<List<ShoppinglistDTO>> getAllShoppinglist() {
        log.info("Getting all actives shoppinglists");
        HttpStatus httpStatus = HttpStatus.OK;
        List<ShoppinglistDTO> shoppinglistDTOS = new ArrayList<>();
        try {
            shoppinglistDTOS = shoppinglistService.findAllShoppinglists();
        } catch (ShoppinglistException e) {
            log.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(shoppinglistDTOS, httpStatus);
    }

    @PostMapping("/v1/create")
    public ResponseEntity<ShoppinglistDTO> createShoppinglist(@NotNull @RequestBody RequestCreateShoppinglistDTO request) {
        log.info("Creation of the new shoppinglist");
        return new ResponseEntity<>(shoppinglistService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Boolean> deleteShoppinglist(@PathVariable Long id) {
        log.info("Delete of the shoppinglist with id: {}", id);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistService.deleteShoppinglist(id);
        }catch (ShoppinglistException e) {
            log.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(!httpStatus.isError(),httpStatus);
    }

    // TODO: Conectar con el FE
    @PutMapping("/v1/update")
    public ResponseEntity<ShoppinglistDTO> updateShoppinglist(@RequestBody RequestUpdateShoppinglistDTO request) {
        log.info("Update the shoppinglist with id: {}", request.getId());
        HttpStatus httpStatus = HttpStatus.CREATED;
        ShoppinglistDTO updated = null;
        try {
            updated = shoppinglistService.updateShoppinglist(request);
        } catch (ShoppinglistException e) {
            log.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(updated, httpStatus);
    }

    // TODO: Conectar con el FE
    @GetMapping("/v1/filter")
    public ResponseEntity<List<ShoppinglistDTO>> filterShoppinglist(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "creationDate", required = false) String creationDate,
            @RequestParam(name = "closeDate", required = false) String closeDate,
            @RequestParam(name = "totalPrice", required = false) Double totalPrice,
            @RequestParam(name = "isActive", required = false) Boolean isActive) {
        log.info("Filter shoppinglists");
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
            log.error(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(shoppinglistDTOList, httpStatus);
    }

    @GetMapping("/v1/{id}/details")
    public ResponseEntity<ShoppinglistDetailsDTO> getDetails(@PathVariable(name = "id") Long idShoppinglist) {
       log.info("Get shoppinglist details");
       HttpStatus httpStatus = HttpStatus.OK;
       ShoppinglistDetailsDTO shoppinglistDetailsDTO = null;
       try {
            shoppinglistDetailsDTO = shoppinglistService.getShoppinglistDetails(idShoppinglist);
       }catch (ShoppinglistException e){
           log.error(e.getMessage());
           httpStatus = HttpStatus.BAD_REQUEST;
       }
       return new ResponseEntity<>(shoppinglistDetailsDTO, httpStatus);
    }

    @PutMapping("/v1/{id}/update/isActive")
    public ResponseEntity<Boolean> updateShoppinglistIsActiveValue(@PathVariable(name = "id") Long idShoppinglist) {
        log.info("Update the value isActive of the shoppinglist with id: {}", idShoppinglist);
        HttpStatus httpStatus = HttpStatus.OK;
        ShoppinglistDTO shoppinglistDTO = null;
        try {
            shoppinglistDTO = shoppinglistService.updateShoppinglistIsActive(idShoppinglist);
        } catch (ShoppinglistException e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(shoppinglistDTO != null, httpStatus);
    }

    @Override
    public ResponseEntity<Boolean> deleteLogicShoppinglist(Long idShoppinglist) {
        log.info("Logic deletion of the entity shoppinglist with id: {}", idShoppinglist);
        HttpStatus httpStatus = HttpStatus.OK;
        boolean deleted = true;
        try {
            shoppinglistService.deleteLogicShoppinglist(idShoppinglist);
        } catch (ShoppinglistException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            deleted = false;
        }
        return new ResponseEntity<>(deleted, httpStatus);
    }

    @Override
    public ResponseEntity<Void> addShoppinglistItemToShoppinglist(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Add shoppinglist item with id: {}, in the shoppinglist with id: {}", idShoppinglistItem, idShoppinglist);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistService.addShoppinglistItem(idShoppinglistItem, idShoppinglist);
        } catch (ShoppinglistException | ShoppinglistItemException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(httpStatus);
    }

    @Override
    public ResponseEntity<Void> updateTotalPrice(Long idShoppinglist) {
        log.info("Update the total cost of the shoppinglist with id: {}", idShoppinglist);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            shoppinglistService.updateShoppinglistTotalPrice(shoppinglistService.findShoppinglistById(idShoppinglist));
        } catch (ShoppinglistException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(httpStatus);
    }
}
