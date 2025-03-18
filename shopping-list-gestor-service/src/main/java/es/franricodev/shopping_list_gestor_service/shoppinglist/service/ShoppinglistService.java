package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;

import java.util.List;

public interface ShoppinglistService {
    List<ShoppinglistDTO> findActiveShoppinglists() throws ShoppinglistException;

    ShoppinglistDTO create(RequestCreateShoppinglistDTO request);

    void deleteShoppinglist(Long id) throws ShoppinglistException;

    ShoppinglistDTO updateShoppinglist(RequestUpdateShoppinglistDTO request) throws ShoppinglistException;
}
