package es.franricodev.shopping_list_gestor_service.wpItemUnit.service;

import es.franricodev.shopping_list_gestor_service.itemUnit.exception.ItemUnitException;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestCreateWpItemUnitData;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;

import java.util.Optional;

public interface WpItemUnitService {
    WpItemUnit createWpItemUnit(RequestCreateWpItemUnitData requestCreateWpItemUnitData);

    WpItemUnit updateWpItemUnit(WpItemUnit wpItemUnit);

    void deleteLogicWpItemUnit(WpItemUnit wpItemUnit);

    Optional<WpItemUnit> findById(Long id);

    double getCalcuatedValue(Long idWpItem) throws ItemUnitException;

}
