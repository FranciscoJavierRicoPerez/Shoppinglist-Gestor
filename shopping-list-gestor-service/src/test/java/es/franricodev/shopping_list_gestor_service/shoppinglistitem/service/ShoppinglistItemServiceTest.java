package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.error.CalculateSystemError;
import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemService;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemServicelmpl;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductServiceImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.message.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistServiceImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistItemServiceTest {

    @InjectMocks
    private ShoppinglistItemServiceImpl shoppinglistItemService;

    @Mock
    private ShoppinglistServiceImpl shoppinglistService;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private CalculateSystemServicelmpl calculateSystemService;

    private static Shoppinglist shoppinglist;

    private static Product product;

    @BeforeAll
    static void setup() {
        shoppinglist = new Shoppinglist();
        product = new Product();
    }

    @Test
    void createShoppinglistItemV2_shoppinglistNotFound_test() throws ShoppinglistException {

        Mockito.doThrow(new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND)).when(shoppinglistService).findShoppinglistById(Mockito.any());
        Assertions.assertThrows(ShoppinglistItemException.class, () -> {
            shoppinglistItemService.createShoppinglistItemV2(1L, new RequestCreateShoppinglistItemV2());
        });

    }

    @Test
    void createShoppinglistItemV2_calculateSystemNotFound_test() throws ShoppinglistException, CalculateSystemException {
        Mockito.when(shoppinglistService.findShoppinglistById(Mockito.any())).thenReturn(shoppinglist);
        Mockito.when(productService.createProductV2(Mockito.any())).thenReturn(product);
        Mockito.doThrow(new CalculateSystemException(CalculateSystemError.CALCULATE_SYSTEM_NOT_FOUND))
                .when(calculateSystemService)
                .findCalculateSystemById(Mockito.any());

        Assertions.assertThrows(ShoppinglistItemException.class, () -> {
           shoppinglistItemService.createShoppinglistItemV2(1L, new RequestCreateShoppinglistItemV2());
        });
    }



}
