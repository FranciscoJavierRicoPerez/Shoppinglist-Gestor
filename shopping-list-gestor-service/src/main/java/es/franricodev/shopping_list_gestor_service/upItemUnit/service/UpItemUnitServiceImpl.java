package es.franricodev.shopping_list_gestor_service.upItemUnit.service;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestCreateUpItemUnitData;
import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestUpdateItemUnitUpValues;
import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.UpdateItemUnitUpValues;
import es.franricodev.shopping_list_gestor_service.upItemUnit.exception.UpItemUnitException;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.upItemUnit.repository.UpItemUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UpItemUnitServiceImpl implements UpItemUnitService{

    @Autowired
    private UpItemUnitRepository upItemUnitRepository;

    @Override
    public UpItemUnit createUpItemUnit(RequestCreateUpItemUnitData requestCreateUpItemUnitData) {
        log.info("Creating a new up item unit");
        UpItemUnit upItemUnit = new UpItemUnit();
        upItemUnit.setInfoBlock(false);
        upItemUnit.setQuantity(requestCreateUpItemUnitData.getQuantity());
        upItemUnit.setUnityPrice(requestCreateUpItemUnitData.getUnitaryPrice());
        return upItemUnitRepository.save(upItemUnit);
    }

    @Override
    public UpItemUnit updateUpItemUnit(UpItemUnit upItemUnit) {
        log.info("Update item unit UP with id: {}", upItemUnit);
        return upItemUnitRepository.save(upItemUnit);
    }

    @Override
    public void deleteUpItemUnitGroupeByPrice(Double price) throws UpItemUnitException {
        log.info("Deleting the up items unit with price: {}",price);
        List<UpItemUnit> upItemUnitList =
                upItemUnitRepository.findByunityPrice(price).orElseThrow(() -> new UpItemUnitException("ERROR-NOT-ITEM-UNITS-UP-FOUND-WITH-THAT-UNITARY-PRICE"));
        log.info("Found {} up items unit with {} price, proceed with his removing from the database", upItemUnitList.size(), price);
        // Me ha borrado todos los up item units independientemente del precio (BORRADO EN CASCADA DE LOS DUROS)
        upItemUnitRepository.deleteAll(upItemUnitList);
    }

    @Override
    public void deleteLogicUpItemUnit(UpItemUnit upItemUnit) {
        upItemUnit.setInfoBlock(true);
        upItemUnitRepository.save(upItemUnit);
    }

    @Override
    public Optional<UpItemUnit> findByUnitaryPrice(double price) {
        return upItemUnitRepository.findOneByunityPrice(price);
    }

    @Override
    public void updateItemUnitUpValues(RequestUpdateItemUnitUpValues request) {
        log.info("Update item units up values");
        request.itemUnitUpValues().forEach(this::updateUpItemUnitValues);
    }

    private void updateUpItemUnitValues(UpdateItemUnitUpValues request) {
        log.info("Updating the values of the item unit up with id: {}", request.idItemUnitUp());
        try {
            UpItemUnit upItemUnit = upItemUnitRepository.findById(request.idItemUnitUp()).orElseThrow(
                    () ->  new UpItemUnitException("NOT_FOUND_ITEM_UNIT_UP_WITH_THE_REFERENCED_ID")
            );
            upItemUnit.setQuantity(upItemUnit.getQuantity() + request.quantity());
            updateUpItemUnit(upItemUnit);
        } catch (UpItemUnitException e) {
            log.error(e.getMessage());
        }
    }

}
