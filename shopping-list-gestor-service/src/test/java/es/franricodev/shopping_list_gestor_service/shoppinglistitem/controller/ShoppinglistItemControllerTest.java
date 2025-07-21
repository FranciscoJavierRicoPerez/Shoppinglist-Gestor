package es.franricodev.shopping_list_gestor_service.shoppinglistitem.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistItemControllerTest {

    @InjectMocks
    private ShoppinglistItemControllerImpl shoppinglistItemController;

    @Mock
    private ShoppinglistServiceImpl shoppinglistService;

    @Test
    void createItemV2Test() {
    }

}
