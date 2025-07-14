package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.mapper.ShoppinglistMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglist.message.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.specifications.ShoppinglistSpecifications;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppinglistServiceImpl implements ShoppinglistService {

    @Autowired
    private ShoppinglistRepository shoppinglistRepository;

    @Autowired
    private ShoppinglistMapper shoppinglistMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppinglistServiceImpl.class);

    @Override
    public List<ShoppinglistDTO> findAllShoppinglists() throws ShoppinglistException {
        LOGGER.info("Find all the shoppinglists actives");
        List<Shoppinglist> shoppinglistList = shoppinglistRepository.findAll();
        if (shoppinglistList.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        return shoppinglistMapper.toDTOList(shoppinglistList);
    }

    @Override
    public ShoppinglistDTO create(RequestCreateShoppinglistDTO request) {
        LOGGER.info("Creation of the new shoppinglist");
        return shoppinglistMapper.toDTO(
                shoppinglistRepository.save(
                        shoppinglistMapper.createShoppinglist(request)
                )
        );
    }

    @Override
    public void deleteShoppinglist(Long id) throws ShoppinglistException {
        LOGGER.info("Delete the shoppinglist with id: {}", id);
        Optional<Shoppinglist> shoppinglistOptional = shoppinglistRepository.findById(id);
        if (shoppinglistOptional.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        shoppinglistRepository.delete(shoppinglistOptional.get());
    }

    @Override
    public ShoppinglistDTO updateShoppinglist(RequestUpdateShoppinglistDTO request) throws ShoppinglistException {
        LOGGER.info("Update the shoppinglist with id: {}", request.getId());
        Optional<Shoppinglist> shoppinglistOptional = shoppinglistRepository.findById(request.getId());
        if (shoppinglistOptional.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        Shoppinglist toUpdate = shoppinglistOptional.get();
        shoppinglistMapper.updateShoppinglist(toUpdate, request);
        toUpdate = shoppinglistRepository.save(toUpdate);
        return shoppinglistMapper.toDTO(toUpdate);
    }

    @Override
    public List<ShoppinglistDTO> filterShoppinglist(RequestFilterShoppinglistDTO request) throws ShoppinglistException {
        LOGGER.info("Filter shoppinglist");
        List<Shoppinglist> optionalShoppinglistsFiltered =
                shoppinglistRepository.findAll(ShoppinglistSpecifications.withFilter(request));
        if (optionalShoppinglistsFiltered.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        return shoppinglistMapper.toDTOList(optionalShoppinglistsFiltered);
    }

    @Override
    public ShoppinglistDetailsDTO getShoppinglistDetails(Long id) throws ShoppinglistException {
        LOGGER.info("Getting the shoppinglist details");
        Optional<Shoppinglist> optionalShoppinglist = shoppinglistRepository.findById(id);
        if (optionalShoppinglist.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_DETAILS);
        }
        return shoppinglistMapper.shoppinglistToShoppinglistDetailsDTO(optionalShoppinglist.get());
    }

    @Override
    public ShoppinglistDTO updateShoppinglistIsActive(Long idShoppinglist) throws ShoppinglistException {
        LOGGER.info("Updating the isActive value from the shoppinglist with id: {}", idShoppinglist);
        Optional<Shoppinglist> optionalShoppinglist = shoppinglistRepository.findById(idShoppinglist);
        if (optionalShoppinglist.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        Shoppinglist shoppinglist = optionalShoppinglist.get();
        shoppinglist.setIsActive(!shoppinglist.getIsActive());
        shoppinglistRepository.save(shoppinglist);
        return shoppinglistMapper.toDTO(shoppinglist);
    }

    @Override
    public Shoppinglist findShoppinglistById(Long id) throws ShoppinglistException {
        return shoppinglistRepository.findById(id).orElseThrow(() -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
    }

    @Override
    public Shoppinglist calculateShoppinglistTotalPrice(Long id) throws ShoppinglistException {
        LOGGER.info("Calculate the shoppinglist total price");
        Shoppinglist shoppinglist = findShoppinglistById(id);
        Double price = 0D;
        List<ShoppinglistItem> shoppinglistItemList = shoppinglist.getItems();
        for(ShoppinglistItem shoppinglistItem : shoppinglistItemList) {
            price += shoppinglistItem.getCalculatedPrice();
        }
        shoppinglist.setTotalPrice(price);
        return shoppinglist;
    }

    // TODO: REFACTORIZAR A PROGRAMACION FUNCIONAL O QUERY QUE DEVUELVA ESTO CONCRETAMENTE
    @Override
    public Shoppinglist findShoppinglistByShoppinglistItemId(Long idItem) {
        List<Shoppinglist> shoppinglistList = shoppinglistRepository.findAll();
        for(Shoppinglist shoppinglist : shoppinglistList) {
            for (ShoppinglistItem shoppinglistItem : shoppinglist.getItems()) {
                if (shoppinglistItem.getId().equals(idItem)) {
                    return shoppinglist;
                }
            }
        }
        return null;
    }

    @Override
    public List<ShoppinglistItem> removeShoppinglistItemFromShoppinglist(Long idShoppinglist, Long idShoppinglistItem) throws ShoppinglistException {
        Optional<Shoppinglist> optionalShoppinglist = shoppinglistRepository.findById(idShoppinglist);
        if (optionalShoppinglist.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        Shoppinglist shoppinglist = optionalShoppinglist.get();
        List<ShoppinglistItem> shoppinglistItemList = shoppinglist.getItems().stream().filter(shoppinglistItem -> !Objects.equals(shoppinglistItem.getId(), idShoppinglistItem)).toList();
        return shoppinglistItemList;
    }

    @Override
    public Shoppinglist updateShoppinglist(Shoppinglist shoppinglist) {
        return shoppinglistRepository.save(shoppinglist);
    }

}
