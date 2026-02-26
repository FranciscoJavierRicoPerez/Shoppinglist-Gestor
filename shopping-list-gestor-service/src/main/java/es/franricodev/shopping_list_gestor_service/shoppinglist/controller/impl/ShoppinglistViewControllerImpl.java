package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.impl;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.ApiShoppinglistViewConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.ShoppinglistViewController;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistDetailsMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistTableMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistViewException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = {
        "http://localhost:8100/",
        "http://192.168.18.7:9000/",
        "*"}
)
@RestController
@RequestMapping(ApiShoppinglistViewConstants.BASE_URL)
public class ShoppinglistViewControllerImpl implements ShoppinglistViewController {

    @Autowired
    private ShoppinglistViewService shoppinglistViewService;

    @Override
    public ResponseEntity<ResponseGetShoppinglistTableMetadata> getShoppinglistTableMetadata() {
        log.info("Getting the meta information necessary for the build of the shoppinglist table view");
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseGetShoppinglistTableMetadata responseGetShoppinglistTableMetadata = ResponseGetShoppinglistTableMetadata.builder().build();
        try {
            responseGetShoppinglistTableMetadata = shoppinglistViewService.getShoppinglistTableMetadata();
        } catch (ShoppinglistViewException e) {
            log.error("The ResponseGetShoppinglistTableMetadata can't be created because there are some errors in the data");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseGetShoppinglistTableMetadata, httpStatus);
    }

    @Override
    public ResponseEntity<ResponseGetShoppinglistDetailsMetadata> getShoppinglistDetailsMetadata(Long idShoppinglist) {
        log.info("Getting the meta information necessary for the build of the shoppinglist {} details view", idShoppinglist);
        ResponseGetShoppinglistDetailsMetadata responseGetShoppinglistDetailsMetadata = ResponseGetShoppinglistDetailsMetadata.builder().build();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            responseGetShoppinglistDetailsMetadata = shoppinglistViewService.getShoopinglistDetailsMetadata(idShoppinglist);
        } catch (ShoppinglistViewException e) {
           log.error("The ResponseGetShoppinglistDetailsMetadata can't be created because there are some errors in the data");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseGetShoppinglistDetailsMetadata, httpStatus);
    }
}
