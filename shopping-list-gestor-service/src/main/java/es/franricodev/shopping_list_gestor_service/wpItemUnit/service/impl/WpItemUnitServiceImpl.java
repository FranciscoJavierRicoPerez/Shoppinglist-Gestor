package es.franricodev.shopping_list_gestor_service.wpItemUnit.service.impl;

import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.itemUnit.constants.messages.ItemUnitMessagesError;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.constants.messages.error.WpItemUnitErrorMessages;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestCreateWpItemUnitData;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestUpdateItemUnitWp;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.exception.WpItemUnitException;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.repository.WpItemUnitRepository;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.service.WpItemUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class WpItemUnitServiceImpl implements WpItemUnitService {

    @Autowired
    private WpItemUnitRepository repository;

    @Override
    public WpItemUnit createWpItemUnit(RequestCreateWpItemUnitData requestCreateWpItemUnitData) {
        log.info("Creating a new wp item unit");
        WpItemUnit wpItemUnit = new WpItemUnit();
        wpItemUnit.setInfoBlock(false);
        wpItemUnit.setPriceKg(requestCreateWpItemUnitData.getPriceKg());
        wpItemUnit.setWeight(requestCreateWpItemUnitData.getWeight());
        return repository.save(wpItemUnit);
    }

    @Override
    public WpItemUnit updateWpItemUnit(WpItemUnit wpItemUnit) {
        log.info("Update the item unit wp {}", wpItemUnit.getId());
        return repository.save(wpItemUnit);
    }

    @Override
    public void deleteLogicWpItemUnit(WpItemUnit wpItemUnit) {
        log.info("Logic delete of the item unit wp with id :{}", wpItemUnit.getId());
        wpItemUnit.setInfoBlock(true);
        repository.save(wpItemUnit);
    }

    @Override
    public Optional<WpItemUnit> findById(Long id) {
        log.info("Search the item unit wp with id {}", id);
        return repository.findById(id);
    }

    @Override
    public double getCalcuatedValue(Long idWpItem) throws ItemUnitException {
        WpItemUnit wpItemUnitOptional = findById(idWpItem).orElseThrow(() -> new ItemUnitException(ItemUnitMessagesError.ITEMUNIT_NOT_FOUND));
        return wpItemUnitOptional.getPriceKg() * wpItemUnitOptional.getWeight();
    }

    @Override
    public void updateWpItemUnit(Long idItemUnitWp, RequestUpdateItemUnitWp requestUpdateItemUnitWp) {
        log.info("Updating the item unit wp with id: {}", idItemUnitWp);
        WpItemUnit wpItemUnit = findById(idItemUnitWp).orElseThrow(
                () -> new WpItemUnitException(WpItemUnitErrorMessages.WP_ITEM_UNIT_NOT_FOUND));
        log.info("Found the item unit wp with id {}, proceed with the update",idItemUnitWp);
        wpItemUnit.setPriceKg(requestUpdateItemUnitWp.newPriceKg());
        wpItemUnit.setWeight(requestUpdateItemUnitWp.newWeight());
        updateWpItemUnit(wpItemUnit);
    }

}
