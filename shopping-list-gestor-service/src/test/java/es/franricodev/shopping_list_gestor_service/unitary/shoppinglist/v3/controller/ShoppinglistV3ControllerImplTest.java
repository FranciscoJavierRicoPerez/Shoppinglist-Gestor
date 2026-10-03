package es.franricodev.shopping_list_gestor_service.unitary.shoppinglist.v3.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3.impl.ShoppinglistV3ControllerImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetAllItemsUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistV3Service;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseGetAllItemUnitUpGroupedByPrice;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseItemUnitWpMetadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

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

    @Test
    void when_get_all_item_units_from_shoppinglist_item_return_200_ok() {
        HttpStatus expectedResult = HttpStatus.OK;
        Mockito.when(shoppinglistV3Service.getAllItemUnitsFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(ResponseGetAllItemsUnit.builder().itemUnitList(List.of()).message("").build());

        ResponseEntity<?> realResult = shoppinglistV3Controller.getAllItemUnitsFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong());
        Assertions.assertEquals(expectedResult, realResult.getStatusCode());
    }

    @Test
    void when_add_item_unit_wp_to_shoppinglist_item_return_201_created() {
        HttpStatus expectedResult = HttpStatus.CREATED;
        Mockito.doNothing().when(shoppinglistV3Service).addItemUnitWpToShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());
        ResponseEntity<?> realResult = shoppinglistV3Controller.addItemUnitWpToShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());
        Assertions.assertEquals(expectedResult, realResult.getStatusCode());
    }

    @Test
    void when_get_all_items_unit_up_grouped_by_price_return_200_ok(){
        HttpStatus expectedResult = HttpStatus.OK;
        Mockito.when(shoppinglistV3Service.getItemsUnitGroupedByPrice(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ResponseGetAllItemUnitUpGroupedByPrice.builder().build());
        ResponseEntity<?> realResult = shoppinglistV3Controller.getAllItemsUnitUpGroupedByPrice(Mockito.anyLong(), Mockito.anyLong());
        Assertions.assertEquals(expectedResult, realResult.getStatusCode());
    }

    @Test
    void when_get_items_units_wp_metadata_return_200_ok() {
        HttpStatus expectResult = HttpStatus.OK;
        Mockito.when(shoppinglistV3Service.getItemUnitsWpMetadata(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ResponseItemUnitWpMetadata.builder().build());
        ResponseEntity<?> realResult = shoppinglistV3Controller.getItemUnitsWpMetadata(Mockito.anyLong(), Mockito.anyLong());
        Assertions.assertEquals(expectResult, realResult.getStatusCode());
    }

    @Test
    void when_update_item_unit_up_data_from_shoppinglist_item_return_200_ok() {
        HttpStatus expectResult = HttpStatus.OK;
        Mockito.doNothing().when(shoppinglistV3Service).updateItemUnitUpDataFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());
        ResponseEntity<?> realResult = shoppinglistV3Controller.updateItemUnitUpDataFromShoppinglistItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any());
        Assertions.assertEquals(expectResult, realResult.getStatusCode());
    }

}
