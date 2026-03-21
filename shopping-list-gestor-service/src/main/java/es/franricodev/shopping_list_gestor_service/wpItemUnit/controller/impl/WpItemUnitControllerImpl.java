package es.franricodev.shopping_list_gestor_service.wpItemUnit.controller.impl;

import es.franricodev.shopping_list_gestor_service.wpItemUnit.constants.api.ApiItemUnitWpConstants;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.controller.WpItemUnitController;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestUpdateItemUnitWp;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.service.WpItemUnitService;
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
@RequestMapping(ApiItemUnitWpConstants.BASE_URL)
public class WpItemUnitControllerImpl implements WpItemUnitController {

    @Autowired
    private WpItemUnitService wpItemUnitService;

    @Override
    public ResponseEntity<Void> updateItemUnitWp(Long idItemUnitWp, RequestUpdateItemUnitWp request) {
        log.info("Update of the item unit wp {}", idItemUnitWp);
        wpItemUnitService.updateWpItemUnit(idItemUnitWp, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
