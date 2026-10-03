package es.franricodev.shopping_list_gestor_service.unitary.shoppinglist.v3.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistExceptionV2;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl.ShoppinglistV3ServiceImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistV3ServiceImplTest {

    @Mock
    private ShoppinglistRepository shoppinglistRepository;

    @Mock
    private ShoppinglistItemService shoppinglistItemService;

    @InjectMocks
    private ShoppinglistV3ServiceImpl shoppinglistV3Service;

    private static Shoppinglist shoppinglist;

    @BeforeEach
    void setup() {
        shoppinglist = new Shoppinglist();
        shoppinglist.setId(1L);
        shoppinglist.setTotalPrice(100D);
        shoppinglist.setItems(List.of());
        shoppinglist.setIsActive(true);
        shoppinglist.setCode("TEST_CODE");
        shoppinglist.setCreationDate(null);
        shoppinglist.setCloseDate(null);
    }

    @Test
    void when_delete_shoppinglist_item_success_return_response_delete_shoppinglist_item(){
        ResponseDeleteShoppinglistItem expectedResult =
                ResponseDeleteShoppinglistItem.builder().delete(true).message("test message").build();
        Mockito.when(shoppinglistRepository.findByIdAndInfoBlockFalse(Mockito.anyLong())).thenReturn(Optional.of(shoppinglist));
        Mockito.when(shoppinglistItemService.deleteLogicShoppinglistItemById(Mockito.anyLong())).thenReturn(expectedResult);
        ResponseDeleteShoppinglistItem realResult = shoppinglistV3Service.deleteShoppinglistItem(1L, 1L);
        Assertions.assertTrue(realResult.isDelete());
    }

    @Test
    void when_delete_shoppinglist_item_and_shoppinglist_is_not_active_throw_shoppinglist_exception_v2(){
        shoppinglist.setIsActive(false);
        Mockito.when(shoppinglistRepository.findByIdAndInfoBlockFalse(Mockito.anyLong())).thenReturn(Optional.of(shoppinglist));
        Assertions.assertThrows(ShoppinglistExceptionV2.class, () -> {
           shoppinglistV3Service.deleteShoppinglistItem(1L, 1L);
        });
    }

    @Test
    void when_delete_shoppinglist_item_and_shoppinglist_is_not_found_throw_shoppinglist_exception() {
        Mockito.doThrow(ShoppinglistException.class).when(shoppinglistRepository).findByIdAndInfoBlockFalse(Mockito.anyLong());
        Assertions.assertThrows(ShoppinglistException.class, () -> {
           shoppinglistV3Service.deleteShoppinglistItem(1L, 1L);
        });
    }

}
