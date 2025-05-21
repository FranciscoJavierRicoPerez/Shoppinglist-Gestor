package es.franricodev.shopping_list_gestor_service.shoppinglist.service;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.mapper.ShoppinglistMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglist.message.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.specifications.ShoppinglistSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppinglistServiceImpl implements ShoppinglistService {

    @Autowired
    private ShoppinglistRepository shoppinglistRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppinglistServiceImpl.class);

    @Override
    public List<ShoppinglistDTO> findAllShoppinglists() throws ShoppinglistException {
        LOGGER.info("Find all the shoppinglists actives");
        List<Shoppinglist> shoppinglistList = shoppinglistRepository.findAll();
        if (shoppinglistList.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        return ShoppinglistMapper.INSTANCE.toDTOList(shoppinglistList);
    }

    @Override
    public ShoppinglistDTO create(RequestCreateShoppinglistDTO request) {
        LOGGER.info("Creation of the new shoppinglist");
        return ShoppinglistMapper.INSTANCE.toDTO(
                shoppinglistRepository.save(
                        ShoppinglistMapper.INSTANCE.createShoppinglist(request)
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
        ShoppinglistMapper.INSTANCE.updateShoppinglist(toUpdate, request);
        toUpdate = shoppinglistRepository.save(toUpdate);
        return ShoppinglistMapper.INSTANCE.toDTO(toUpdate);
    }

    @Override
    public List<ShoppinglistDTO> filterShoppinglist(RequestFilterShoppinglistDTO request) throws ShoppinglistException {
        LOGGER.info("Filter shoppinglist");
        List<Shoppinglist> optionalShoppinglistsFiltered =
                shoppinglistRepository.findAll(ShoppinglistSpecifications.withFilter(request));
        if (optionalShoppinglistsFiltered.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        return ShoppinglistMapper.INSTANCE.toDTOList(optionalShoppinglistsFiltered);
    }

    @Override
    public ShoppinglistDetailsDTO getShoppinglistDetails(Long id) throws ShoppinglistException {
        LOGGER.info("Getting the shoppinglist details");
        Optional<Shoppinglist> optionalShoppinglist = shoppinglistRepository.findById(id);
        if (optionalShoppinglist.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_DETAILS);
        }
        return ShoppinglistMapper.INSTANCE.shoppinglistToShoppinglistDetailsDTO(optionalShoppinglist.get());
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
        return ShoppinglistMapper.INSTANCE.toDTO(shoppinglist);
    }

    @Override
    public Shoppinglist findShoppinglistById(Long id) throws ShoppinglistException {
        return shoppinglistRepository.findById(id).orElseThrow(() -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
    }

}
