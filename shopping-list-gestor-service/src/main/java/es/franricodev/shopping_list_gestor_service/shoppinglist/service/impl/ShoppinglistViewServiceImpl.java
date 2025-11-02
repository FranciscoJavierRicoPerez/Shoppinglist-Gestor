package es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistDetailsMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistTableMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistViewException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.message.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistViewService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShoppinglistViewServiceImpl implements ShoppinglistViewService {

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Override
    public ResponseGetShoppinglistTableMetadata getShoppinglistTableMetadata() throws ShoppinglistViewException {
        log.info("Build of the ResponseGetShoppinglistTableMetadata");
        try {
            return ResponseGetShoppinglistTableMetadata.builder().shoppinglistTableMetadataList(shoppinglistService.getAllShoppinglistMetadata()).build();
        } catch (ShoppinglistException e) {
            throw new ShoppinglistViewException(ErrorMessages.ERR_SHOPPINGLIST_TABLE_NO_BUILD);
        }
    }

    @Override
    public ResponseGetShoppinglistDetailsMetadata getShoopinglistDetailsMetadata(Long idShoppinglist) throws ShoppinglistViewException {
        log.info("Build of the ResponseGetShoppinglistDetailsMetadata");
        try {
            return ResponseGetShoppinglistDetailsMetadata.builder()
                    .shoppinglistMetadata(shoppinglistService.getShoppinglistMetadata(idShoppinglist))
                    .items(shoppinglistItemService.getShoppinglistItemMetadataDTO(idShoppinglist))
                    .build();
        } catch (ShoppinglistItemException | ShoppinglistException e) {
            throw new ShoppinglistViewException(ErrorMessages.ERR_SHOPPINGLIST_DETAILS_VIEW_NO_BUILD);
        }
    }
}
