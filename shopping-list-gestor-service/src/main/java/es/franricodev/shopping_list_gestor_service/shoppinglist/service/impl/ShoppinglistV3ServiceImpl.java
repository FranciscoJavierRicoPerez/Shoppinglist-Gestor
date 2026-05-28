package es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.messages.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistExceptionV2;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Slf4j
@Service
public class ShoppinglistV3ServiceImpl implements ShoppinglistV3Service {

    @Autowired
    private ShoppinglistRepository shoppinglistRepository;

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    /**
     * Logic delete of a shoppinglist item
     * @param idShoppinglist
     * @param idShoppinglistItem
     * @return ResponseDeleteShoppinglistItem
     */
    @Override
    public ResponseDeleteShoppinglistItem deleteShoppinglistItem(Long idShoppinglist, Long idShoppinglistItem) {
        log.info("Logic delete of the shoppinglist item : [{}]",idShoppinglistItem);
        if (!isActive(idShoppinglist)) {
            log.info("The shoppinglist [{}] is not active", idShoppinglist);
            throw new ShoppinglistExceptionV2(
                    ErrorMessages.ERR_SHOPPINGLIST_IS_NOT_ACTIVE,
                    GeneralConstants.HTTP_409,
                    GeneralConstants.DESC_409,
                    HttpStatus.CONFLICT
            );
        }
        log.info("The shoppinglist [{}] is active", idShoppinglist);
        return shoppinglistItemService.deleteLogicShoppinglistItemById(idShoppinglistItem);
    }

    /**
     * Verify if a shoppinglist is active or not
     * @param idShoppinglist
     * @return boolean
     */
    private boolean isActive(Long idShoppinglist) {
        log.info("Verifying if the shoppinglist [{}] is active or not", idShoppinglist);
        Shoppinglist shoppinglist = getShoppinglistById(idShoppinglist);
        return shoppinglist.getIsActive();
    }

    /**
     * Get a shoppinglist by his id
     * @param idShoppinglist
     * @return Shoppinglist
     */
    private Shoppinglist getShoppinglistById(Long idShoppinglist) {
        log.info("Getting the shoppinglist with id [{}]", idShoppinglist);
        return shoppinglistRepository.findById(idShoppinglist).orElseThrow(
                () -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND)
        );
    }


}
