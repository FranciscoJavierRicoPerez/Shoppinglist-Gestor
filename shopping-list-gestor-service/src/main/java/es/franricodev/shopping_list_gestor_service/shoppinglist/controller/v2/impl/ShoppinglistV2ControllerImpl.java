package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v2.impl;


import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v2.ApiShoppinglistV2Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v2.ShoppinglistV2Controller;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseCreateShoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
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
@RequestMapping(ApiShoppinglistV2Constants.BASE_URL)
public class ShoppinglistV2ControllerImpl implements ShoppinglistV2Controller {

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Override
    public ResponseEntity<ResponseCreateShoppinglist> createShoppinglistV2(RequestCreateShoppinglistDTO request) {
        log.info("Creation v2 of the new shoppinglist");
        return new ResponseEntity<>(shoppinglistService.createV2(request), HttpStatus.CREATED);
    }

}
