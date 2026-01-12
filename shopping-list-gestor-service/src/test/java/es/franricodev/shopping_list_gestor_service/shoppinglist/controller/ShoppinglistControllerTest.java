package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.controller.impl.ShoppinglistControllerImpl;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistControllerTest {

    @Mock
    private ShoppinglistService shoppinglistService;

    @InjectMocks
    private ShoppinglistControllerImpl shoppinglistController;

    private ShoppinglistDTO shoppinglistDTO;

    private RequestCreateShoppinglistDTO requestCreateShoppinglistDTO;

    private RequestUpdateShoppinglistDTO requestUpdateShoppinglistDTO;

    private ShoppinglistDetailsDTO shoppinglistDetailsDTO;

    @BeforeEach
    void setUp() {
        shoppinglistDTO = new ShoppinglistDTO();
        shoppinglistDTO.setId(1L);
        shoppinglistDTO.setIsActive(true);
        shoppinglistDTO.setCode("SHOPPINGLIST-1");
        shoppinglistDTO.setCreationDate(new Date());
        shoppinglistDTO.setCloseDate(null);
        shoppinglistDTO.setTotalPrice(1D);

        requestCreateShoppinglistDTO = new RequestCreateShoppinglistDTO();
        requestCreateShoppinglistDTO.setCode("SHOPPINGLIST-1");
        requestCreateShoppinglistDTO.setIsActive(true);
        requestCreateShoppinglistDTO.setTotalPrice(1D);

        requestUpdateShoppinglistDTO = new RequestUpdateShoppinglistDTO();
        requestUpdateShoppinglistDTO.setId(1L);
        requestUpdateShoppinglistDTO.setCode("SHOPPINGLIST-1");
        requestUpdateShoppinglistDTO.setTotalPrice(1D);
        requestUpdateShoppinglistDTO.setIsActive(true);

        shoppinglistDetailsDTO = new ShoppinglistDetailsDTO();
        shoppinglistDetailsDTO.setId(1L);
        shoppinglistDetailsDTO.setIsActive(true);
        shoppinglistDetailsDTO.setCode("SHOPPINGLIST-1");
        shoppinglistDetailsDTO.setCreationDate(new Date());
        shoppinglistDetailsDTO.setCloseDate(null);
        shoppinglistDetailsDTO.setTotalPrice(1D);

    }

    @Test
    void getAllShoppinglistActiveTest() throws ShoppinglistException {
        ResponseEntity<List<ShoppinglistDTO>> expected =
                new ResponseEntity<>(List.of(shoppinglistDTO), HttpStatus.OK);
        Mockito.when(shoppinglistService.findAllShoppinglists()).thenReturn(List.of(shoppinglistDTO));
        ResponseEntity<List<ShoppinglistDTO>> real = shoppinglistController.getAllShoppinglist();
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

    /* @Test
    void createShoppinglistTest() {
        ResponseEntity<ShoppinglistDTO> expected = new ResponseEntity<>(shoppinglistDTO, HttpStatus.CREATED);
        Mockito.when(shoppinglistService.create(Mockito.any())).thenReturn(shoppinglistDTO);
        ResponseEntity<ShoppinglistDTO> real = shoppinglistController.createShoppinglist(requestCreateShoppinglistDTO);
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    } */

    @Test
    void deleteShoppinglistTest() throws ShoppinglistException {
        ResponseEntity<?> expected = new ResponseEntity<>(HttpStatus.OK);
        Mockito.doNothing().when(shoppinglistService).deleteShoppinglist(1L);
        ResponseEntity<?> real = shoppinglistController.deleteShoppinglist(1L);
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

    @Test
    void updateShoppinglistTest() throws ShoppinglistException {
        ResponseEntity<ShoppinglistDTO> expected = new ResponseEntity<>(shoppinglistDTO, HttpStatus.CREATED);
        Mockito.when(shoppinglistService.updateShoppinglist(requestUpdateShoppinglistDTO)).thenReturn(shoppinglistDTO);
        ResponseEntity<ShoppinglistDTO> real = shoppinglistController.updateShoppinglist(requestUpdateShoppinglistDTO);
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

    @Test
    void filterShoppinglistTest() throws ShoppinglistException {
        ResponseEntity<List<ShoppinglistDTO>> expected = new ResponseEntity<>(List.of(shoppinglistDTO), HttpStatus.OK);
        Mockito.when(shoppinglistService.filterShoppinglist(Mockito.any())).thenReturn(List.of(shoppinglistDTO));
        ResponseEntity<List<ShoppinglistDTO>> real =
                shoppinglistController.filterShoppinglist("SHOPPINGLIST-1","18/03/2025","18/03/2025",1D, null);
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

    @Test
    void getShoppinglistDetailsTest() throws ShoppinglistException {
        ResponseEntity<ShoppinglistDetailsDTO> expected = new ResponseEntity<>(shoppinglistDetailsDTO, HttpStatus.OK);
        Mockito.when(shoppinglistService.getShoppinglistDetails(Mockito.any())).thenReturn(shoppinglistDetailsDTO);
        ResponseEntity<ShoppinglistDetailsDTO> real = shoppinglistController.getDetails(1L);
        Assertions.assertEquals(expected.getStatusCode(), real.getStatusCode());
    }

    @Test
    void updateShoppinglistIsActiveTest() throws ShoppinglistException {
        shoppinglistDTO.setIsActive(false);
        Mockito.when(shoppinglistService.updateShoppinglistIsActive(1L)).thenReturn(shoppinglistDTO);
        ResponseEntity<Boolean> real = shoppinglistController.updateShoppinglistIsActiveValue(1L);
        Assertions.assertEquals(Boolean.TRUE, real.getBody());
    }


}
