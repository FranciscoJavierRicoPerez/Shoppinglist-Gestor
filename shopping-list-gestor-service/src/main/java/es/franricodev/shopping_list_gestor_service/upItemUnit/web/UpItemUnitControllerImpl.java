package es.franricodev.shopping_list_gestor_service.upItemUnit.web;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestUpdateItemUnitUpValues;
import es.franricodev.shopping_list_gestor_service.upItemUnit.exception.UpItemUnitException;
import es.franricodev.shopping_list_gestor_service.upItemUnit.service.UpItemUnitService;
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
@RequestMapping("/api/upitemunit")
public class UpItemUnitControllerImpl implements UpItemUnitController{
    @Autowired
    private UpItemUnitService upItemUnitService;

    @Override
    public ResponseEntity<Void> removeUpItemUnitByPrice(Double price) {
        try {
            upItemUnitService.deleteUpItemUnitGroupeByPrice(price);
        } catch (UpItemUnitException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateItemUnitUpValues(RequestUpdateItemUnitUpValues request) {
        log.info("Updating the values of the all item units up indicated in the request");
        upItemUnitService.updateItemUnitUpValues(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
