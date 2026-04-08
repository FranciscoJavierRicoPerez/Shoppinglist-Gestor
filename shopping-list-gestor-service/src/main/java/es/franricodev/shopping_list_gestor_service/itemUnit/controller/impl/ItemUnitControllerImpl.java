package es.franricodev.shopping_list_gestor_service.itemUnit.controller.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.constants.api.ApiItemUnitConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.controller.ItemUnitController;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.RequestUpdateItemUnitWpTotalPrice;
import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.service.ItemUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = {"http://localhost:8100/", "http://192.168.18.7:9000/", "*"})
@RestController
@RequestMapping(ApiItemUnitConstants.BASE_URL)
public class ItemUnitControllerImpl implements ItemUnitController {

    @Autowired
    private ItemUnitService itemUnitService;

    @Override
    public ResponseEntity<Void> updateItemUnit(Long idItemUnit, RequestUpdateItemUnitWpTotalPrice request) {
        log.info("Updating the item unit {} values", idItemUnit);
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            itemUnitService.updateItemUnitTotalPrice(idItemUnit, request);
        } catch (ItemUnitException e) {
               status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
