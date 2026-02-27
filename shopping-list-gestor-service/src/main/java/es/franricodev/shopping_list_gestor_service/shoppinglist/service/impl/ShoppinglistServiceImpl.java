package es.franricodev.shopping_list_gestor_service.shoppinglist.service.impl;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.*;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseCreateShoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetFilteredShoppinglistMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistTableMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ShoppinglistMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.mapper.ShoppinglistMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.messages.ErrorMessages;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.repository.ShoppinglistRepository;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.specifications.ShoppinglistSpecifications;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.service.ShoppinglistItemService;
import es.franricodev.shopping_list_gestor_service.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ShoppinglistServiceImpl implements ShoppinglistService {

    @Autowired
    private ShoppinglistRepository shoppinglistRepository;

    @Autowired
    private ShoppinglistItemService shoppinglistItemService;

    @Autowired
    private ShoppinglistMapper shoppinglistMapper;

    @Override
    public List<ShoppinglistDTO> findAllShoppinglists() throws ShoppinglistException {
        log.info("Find all the shoppinglists actives");
        List<Shoppinglist> shoppinglistList = shoppinglistRepository.findAllByInfoBlockFalse().orElseThrow(
                () -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
        return shoppinglistMapper.toDTOList(shoppinglistList);
    }

    @Override
    public ShoppinglistDTO create(RequestCreateShoppinglistDTO request) {
        log.info("Creation of the new shoppinglist");
        return shoppinglistMapper.toDTO(
                shoppinglistRepository.save(
                        shoppinglistMapper.createShoppinglist(request)
                )
        );
    }

    @Override
    public ResponseCreateShoppinglist createV2(RequestCreateShoppinglistDTO request) {
        log.info("Creation of the new shoppinglist V2");
        Shoppinglist shoppinglist = shoppinglistRepository.save(shoppinglistMapper.createShoppinglist(request));
        return ResponseCreateShoppinglist
                .builder()
                .shoppinglistCreated(
                        ShoppinglistMetadata
                                .builder()
                                .idShoppinglist(shoppinglist.getId())
                                .totalPrice(shoppinglist.getTotalPrice())
                                .code(shoppinglist.getCode())
                                .isActive(shoppinglist.getIsActive())
                                .creationDate(String.valueOf(shoppinglist.getCreationDate()))
                                .build()
                )
                .build();
    }

    @Override
    public void deleteShoppinglist(Long id) throws ShoppinglistException {
        log.info("Delete the shoppinglist with id: {}", id);
        Optional<Shoppinglist> shoppinglistOptional = shoppinglistRepository.findById(id);
        if (shoppinglistOptional.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        shoppinglistRepository.delete(shoppinglistOptional.get());
    }

    @Override
    public ShoppinglistDTO updateShoppinglist(RequestUpdateShoppinglistDTO request) throws ShoppinglistException {
        log.info("Update the shoppinglist with id: {}", request.getId());
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
    public ResponseGetFilteredShoppinglistMetadata filterShoppinglist(RequestFilterShoppinglistDTO request) throws ShoppinglistException {
        log.info("Filter shoppinglist");
        List<Shoppinglist> shoppinglistFiltered =
                shoppinglistRepository.findAll(ShoppinglistSpecifications.withFilter(request));
        if (shoppinglistFiltered.isEmpty()) {
            throw new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND);
        }
        return new ResponseGetFilteredShoppinglistMetadata(shoppinglistFiltered.stream().map(this::createShoppinglistMetadataFromShoppinglist).toList());
    }

    @Override
    public ShoppinglistDetailsDTO getShoppinglistDetails(Long id) throws ShoppinglistException {
        log.info("Getting the shoppinglist details");
        Shoppinglist shoppinglist = shoppinglistRepository.findById(id).orElseThrow(() -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_DETAILS));
        shoppinglist.setItems(shoppinglist.getItems().stream().filter(shoppinglistItem -> !shoppinglistItem.getInfoBlock()).toList());
        return shoppinglistMapper.shoppinglistToShoppinglistDetailsDTO(shoppinglist);
    }

    @Override
    public ShoppinglistDTO updateShoppinglistIsActive(Long idShoppinglist) throws ShoppinglistException {
        log.info("Updating the isActive value from the shoppinglist with id: {}", idShoppinglist);
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
        log.info("Calculate the shoppinglist total price");
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
        return shoppinglist.getItems().stream().filter(shoppinglistItem -> !Objects.equals(shoppinglistItem.getId(), idShoppinglistItem)).toList();
    }

    @Transactional
    @Override
    public Shoppinglist updateShoppinglist(Shoppinglist shoppinglist) {
        System.out.println(shoppinglist.toString());
        log.info("Updating the values of the shoppinglist with id: {}", shoppinglist.getId());
        return shoppinglistRepository.save(shoppinglist);
    }

    @Override
    public void addShoppinglistItemToShoppinglist(ShoppinglistItem shoppinglistItem, Shoppinglist shoppinglist) {
        if(shoppinglistItem != null && shoppinglist != null) {
            if(shoppinglist.getItems().isEmpty()){
                ArrayList<ShoppinglistItem> shoppinglistItemArrayList = new ArrayList<>();
                shoppinglistItemArrayList.add(shoppinglistItem);
                shoppinglist.setItems(shoppinglistItemArrayList);
            } else {
                shoppinglist.getItems().add(shoppinglistItem);
            }
            shoppinglistRepository.save(shoppinglist);
        }
    }

    @Override
    public void updateShoppinglistTotalPrice(Shoppinglist shoppinglist) {
        if(shoppinglist != null) {
            log.info("Updating shoppinglist total price");
            shoppinglist.setTotalPrice(calculateShoppinglistTotalPrice(shoppinglist));
            updateShoppinglist(shoppinglist);
        }
    }

    @Override
    public void deleteLogicShoppinglist(Long id) throws ShoppinglistException {
        log.info("Logic deletion of the shoppinglist with id: {}", id);
        Shoppinglist shoppinglist = shoppinglistRepository.findById(id).orElseThrow(
                () -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND)
        );
        shoppinglist.setInfoBlock(true);
        log.info("Logic deletion of all shoppinglist items from the shoppinglist with id: {}", id);
        shoppinglistItemService.deleteLogicAllShoppinglistItem(shoppinglist.getItems());
        updateShoppinglistTotalPrice(shoppinglist);
    }

    @Override
    public void addShoppinglistItem(Long idShoppinglistItem, Long idShoppinglist) throws ShoppinglistException, ShoppinglistItemException {
        log.info("Add shoppinglist item with id: {} to the shoppinglist with id: {}", idShoppinglistItem, idShoppinglist);
        ShoppinglistItem shoppinglistItem = shoppinglistItemService.findShoppinglistItemById(idShoppinglistItem);
        Shoppinglist shoppinglist = shoppinglistRepository
                .findById(idShoppinglist)
                .orElseThrow(() -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
        addShoppinglistItemToShoppinglist(shoppinglistItem, shoppinglist);
    }

    @Override
    public ShoppinglistMetadata getShoppinglistMetadata(Long idShoppinglist) throws ShoppinglistException {
        log.info("Building ShoppinglistMetadata of the shoppinglist with id: {}", idShoppinglist);
        Shoppinglist shoppinglist =
                shoppinglistRepository.findByIdAndInfoBlockFalse(idShoppinglist)
                        .orElseThrow( () -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
        return createShoppinglistMetadataFromShoppinglist(shoppinglist);
    }

    @Override
    public List<ShoppinglistMetadata> getAllShoppinglistMetadata() throws ShoppinglistException {
        log.info("Building a list of ShoppinglistMetadata with all the shoppinglist in the database");
        List<Shoppinglist> shoppinglistList = shoppinglistRepository.findAllByInfoBlockFalse()
                .orElseThrow(() -> new ShoppinglistException(ErrorMessages.ERR_SHOPPINGLIST_NOT_FOUND));
        return shoppinglistList.stream().map(this::createShoppinglistMetadataFromShoppinglist).toList();
    }

    private ShoppinglistMetadata createShoppinglistMetadataFromShoppinglist(Shoppinglist shoppinglist) {
        return ShoppinglistMetadata.builder()
                .code(shoppinglist.getCode())
                .creationDate(DateUtils.formatLocalDate(shoppinglist.getCreationDate(), "dd/MM/yyyy"))
                .isActive(shoppinglist.getIsActive())
                .totalPrice(shoppinglist.getTotalPrice())
                .idShoppinglist(shoppinglist.getId())
                .build();
    }

    private double calculateShoppinglistTotalPrice(Shoppinglist shoppinglist) {
        log.info("Calculate the new total cost of the shoppinglist with id {}, the actual value is: {}"
                , shoppinglist.getId(), shoppinglist.getTotalPrice());
        double totalPriceCalculated = 0.0;
        if(!shoppinglist.getItems().isEmpty()) {
            for(ShoppinglistItem shoppinglistItem : shoppinglist.getItems()) {
                if (!shoppinglistItem.getInfoBlock()) {
                    totalPriceCalculated += shoppinglistItem.getCalculatedPrice();
                }
            }
            log.info("The new total cost will be {}", totalPriceCalculated);
        }
        return totalPriceCalculated;
    }

}
