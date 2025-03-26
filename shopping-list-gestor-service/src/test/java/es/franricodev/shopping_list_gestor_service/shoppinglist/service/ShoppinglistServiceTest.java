package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ShoppinglistServiceTest {

    @Mock
    private ShoppinglistRepository shoppinglistRepository;

    @InjectMocks
    private ShoppinglistServiceImpl shoppinglistService;

    private ShoppinglistDTO shoppinglistDTO;

    private RequestCreateShoppinglistDTO requestCreateShoppinglistDTO;

    private RequestUpdateShoppinglistDTO requestUpdateShoppinglistDTO;

    private Shoppinglist shoppinglist;

    private ShoppinglistDetailsDTO shoppinglistDetailsDTO;

    @BeforeEach
    void setUp() {

        shoppinglist = new Shoppinglist();
        shoppinglist.setId(1L);
        shoppinglist.setIsActive(true);
        shoppinglist.setCode("SHOPPINGLIST-1");
        shoppinglist.setCreationDate(new Date());
        shoppinglist.setCloseDate(null);
        shoppinglist.setTotalPrice(1D);

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
    void findActiveShoppinglistsTest() throws ShoppinglistException {
        List<ShoppinglistDTO> expected = Collections.singletonList(shoppinglistDTO);
        Mockito.when(shoppinglistRepository.IsActiveTrue()).thenReturn(Optional.of(shoppinglist));
        List<ShoppinglistDTO> real = shoppinglistService.findActiveShoppinglists();
        Assertions.assertEquals(expected.size(), real.size());
    }

    @Test
    void createShoppinglistTest(){
        ShoppinglistDTO expected = shoppinglistDTO;
        Mockito.when(shoppinglistRepository.save(Mockito.any())).thenReturn(shoppinglist);
        ShoppinglistDTO real = shoppinglistService.create(requestCreateShoppinglistDTO);
        Assertions.assertEquals(expected.getId(), real.getId());
    }

    @Test
    void deleteShoppinglistTest() throws ShoppinglistException {
        Mockito.when(shoppinglistRepository.findById(1L)).thenReturn(Optional.of(shoppinglist));
        shoppinglistService.deleteShoppinglist(1L);
        Mockito.verify(shoppinglistRepository, Mockito.times(1)).delete(shoppinglist);
    }

    @Test
    void updateShoppinglistTest() throws ShoppinglistException {
        ShoppinglistDTO expected = shoppinglistDTO;
        Mockito.when(shoppinglistRepository.findById(1L)).thenReturn(Optional.of(shoppinglist));
        Mockito.when(shoppinglistRepository.save(Mockito.any())).thenReturn(shoppinglist);
        ShoppinglistDTO real = shoppinglistService.updateShoppinglist(requestUpdateShoppinglistDTO);
        Assertions.assertEquals(expected.getId(), real.getId());
    }

    @Test
    void getShoppinglistDetailsTest() throws ShoppinglistException {
        ShoppinglistDetailsDTO expected = shoppinglistDetailsDTO;
        Mockito.when(shoppinglistRepository.findById(Mockito.any())).thenReturn(Optional.of(shoppinglist));
        ShoppinglistDetailsDTO real = shoppinglistService.getShoppinglistDetails(1L);
        Assertions.assertEquals(expected.getId(), real.getId());
    }

    @Test
    void updateShoppinglistIsActiveValueTest() throws ShoppinglistException {
        shoppinglistDTO.setIsActive(false);
        ShoppinglistDTO expected = shoppinglistDTO;
        Mockito.when(shoppinglistRepository.findById(Mockito.any())).thenReturn(Optional.of(shoppinglist));
        Mockito.when(shoppinglistRepository.save(Mockito.any())).thenReturn(shoppinglist);
        ShoppinglistDTO real = shoppinglistService.updateShoppinglistIsActive(1L);
        Assertions.assertEquals(expected.getIsActive(), real.getIsActive());
    }

}
