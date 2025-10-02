package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.error.CalculateSystemError;
import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemServicelmpl;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductServiceImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.message.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl.ShoppinglistServiceImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItemV2;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository.ShoppinglistItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Mock
    private ShoppinglistItemRepository shoppinglistItemRepository;

    private static Shoppinglist shoppinglist;
    private static Shoppinglist shoppinglistUpdated;
    private static Product product;
    private static CalculateSystem calculateSystem;
    private static ShoppinglistItem shoppinglistItem;

    @BeforeAll
    static void setup() {
        shoppinglist = new Shoppinglist();
        product = new Product();
        calculateSystem = new CalculateSystem();
        shoppinglistItem = new ShoppinglistItem();

        product.setId(1L);
        product.setName("Manzanas");
        product.setCreationDate(new Date());

        calculateSystem.setId(1L);
        calculateSystem.setName("Precio Unitario");
        calculateSystem.setCode("UP");

        shoppinglist.setId(1L);
        shoppinglist.setItems(new ArrayList<>());
        shoppinglist.setTotalPrice(0.0);
        shoppinglist.setIsActive(true);
        shoppinglist.setCode("CODE");
        shoppinglist.setCloseDate(null);

        shoppinglistUpdated = shoppinglist;

    }

    @Test
    void createShoppinglistItemV2_shoppinglistNotFound_test() throws ShoppinglistException {

        Mockito.doThrow(new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND)).when(shoppinglistService).findShoppinglistById(Mockito.any());
        Assertions.assertThrows(ShoppinglistItemException.class, () -> {
            shoppinglistItemService.createShoppinglistItem(1L, new RequestCreateShoppinglistItemV2());
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
           shoppinglistItemService.createShoppinglistItem(1L, new RequestCreateShoppinglistItemV2());
        });
    }

    // @Test
    void createShoppinglistItemV2_withoutItemUnit_test() throws ShoppinglistException, CalculateSystemException, ShoppinglistItemException {
        shoppinglistUpdated.setItems(List.of(shoppinglistItem));
        Mockito.when(shoppinglistService.findShoppinglistById(Mockito.any())).thenReturn(shoppinglist);
        Mockito.when(productService.createProductV2(Mockito.any())).thenReturn(product);
        Mockito.when(calculateSystemService.findCalculateSystemById(Mockito.any())).thenReturn(calculateSystem);
        Mockito.doNothing().when(shoppinglistService).addShoppinglistItemToShoppinglist(Mockito.any(), Mockito.any());
        Mockito.when(shoppinglistItemRepository.save(Mockito.any())).thenReturn(shoppinglistUpdated);
        Mockito.doNothing().when(shoppinglistService).updateShoppinglistTotalPrice(Mockito.any());

        shoppinglistItemService.createShoppinglistItem(1L, new RequestCreateShoppinglistItemV2());

        Mockito.verify(shoppinglistService, Mockito.times(1)).updateShoppinglistTotalPrice(Mockito.any());

    }



}
