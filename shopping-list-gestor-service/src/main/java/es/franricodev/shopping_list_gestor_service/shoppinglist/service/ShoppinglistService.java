package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;

import java.util.List;
import java.util.Optional;

public interface ShoppinglistService {
    List<ShoppinglistDTO> findAllShoppinglists() throws ShoppinglistException;

    ShoppinglistDTO create(RequestCreateShoppinglistDTO request);

    ResponseCreateShoppinglist createV2(RequestCreateShoppinglistDTO request);

    void deleteShoppinglist(Long id) throws ShoppinglistException;

    ShoppinglistDTO updateShoppinglist(RequestUpdateShoppinglistDTO request) throws ShoppinglistException;

    ResponseGetFilteredShoppinglistMetadata filterShoppinglist(RequestFilterShoppinglistDTO request) throws ShoppinglistException;

    ShoppinglistDetailsDTO getShoppinglistDetails(Long id) throws ShoppinglistException;

    ShoppinglistDTO updateShoppinglistIsActive(Long idShoppinglist) throws ShoppinglistException;

    Shoppinglist findShoppinglistById(Long id) throws ShoppinglistException;

    Shoppinglist calculateShoppinglistTotalPrice(Long id) throws ShoppinglistException;

    Shoppinglist findShoppinglistByShoppinglistItemId(Long id);

    List<ShoppinglistItem> removeShoppinglistItemFromShoppinglist(Long idShoppinglist, Long idShoppinglistItem) throws ShoppinglistException;

    Shoppinglist updateShoppinglist(Shoppinglist shoppinglist);

    void addShoppinglistItemToShoppinglist(ShoppinglistItem shoppinglistItem, Shoppinglist shoppinglist);

    void updateShoppinglistTotalPrice(Shoppinglist shoppinglist);

    void deleteLogicShoppinglist(Long id) throws ShoppinglistException;

    void addShoppinglistItem(Long idShoppinglistItem, Long idShoppinglist) throws ShoppinglistException, ShoppinglistItemException;

    ShoppinglistMetadata getShoppinglistMetadata(Long idShoppinglist) throws ShoppinglistException;

    List<ShoppinglistMetadata> getAllShoppinglistMetadata() throws ShoppinglistException;

}


