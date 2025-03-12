package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ShoppinglistControllerTest {

    @Autowired
    private ShoppinglistService shoppinglistService;

    @InjectMocks
    private ShoppinglistController shoppinglistController;

    @Test
    public void getAllShoppinglistActiveTest() throws ShoppinglistException {
        ShoppinglistDTO shoppinglistDTO = new ShoppinglistDTO();
        shoppinglistDTO.setId(1L);
        shoppinglistDTO.setIsActive(true);
        shoppinglistDTO.setCode("SHOPPINGLIST-1");
        shoppinglistDTO.setCreationDate(new Date());
        shoppinglistDTO.setCloseDate(null);

        ResponseEntity<List<ShoppinglistDTO>> expected =
                new ResponseEntity<>(List.of(shoppinglistDTO), HttpStatus.OK);

        Mockito.when(shoppinglistService.findActiveShoppinglists()).thenReturn(List.of(shoppinglistDTO));

        ResponseEntity<List<ShoppinglistDTO>> real = shoppinglistController.getAllShoppinglistActive();

        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

}
