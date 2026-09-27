package es.franricodev.shopping_list_gestor_service.unitary.shoppinglist.v3.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.impl.ShoppinglistV3ControllerImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistV3ControllerImplTest {

    @Mock
    private ShoppinglistV3Service shoppinglistV3Service;

    @InjectMocks
    private ShoppinglistV3ControllerImpl shoppinglistV3Controller;

    @Test
    void when_delete_shoppinglist_item_return_200_OK(){
        HttpStatus expectedResult = HttpStatus.OK;
        ResponseDeleteShoppinglistItem responseDeleteShoppinglistItem =
                ResponseDeleteShoppinglistItem
                        .builder()
                        .message("Test message")
                        .delete(true)
                        .newShoppinglistTotalPrice(0D)
                        .build();
        Mockito.when(shoppinglistV3Service.deleteShoppinglistItem(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(responseDeleteShoppinglistItem);

        Assertions.assertEquals(
                expectedResult,
                shoppinglistV3Controller.deleteShoppinglistItem(1L, 1L).getStatusCode()
        );
    }

    @Test
    void when_add_item_unit_up_to_shoppinglist_item_return_201_CREATED() {
        HttpStatus expectedResult = HttpStatus.CREATED;
        Mockito.when(shoppinglistV3Service.addItemUnitUpToShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any()))
                .thenReturn(2D);

        ResponseEntity<?> realResult = shoppinglistV3Controller.addItemUnitUpToShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());

        Assertions.assertEquals(expectedResult, realResult.getStatusCode());
        Assertions.assertEquals(2D, realResult.getBody());
    }

    @Test
    void when_delete_item_unit_from_shoppinglist_item_return_200_OK(){
        HttpStatus expectedResult = HttpStatus.OK;
        Mockito.doNothing()
                .when(shoppinglistV3Service)
                .deleteItemUnitFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong());

        ResponseEntity<?> realResult = shoppinglistV3Controller.deleteItemUnitFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong());

        Assertions.assertEquals(expectedResult, realResult.getStatusCode());
    }

    // @Test
    // void when_get_all_item

}
