package es.franricodev.shopping_list_gestor_service.upItemUnit.service;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestCreateUpItemUnitData;
import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestUpdateItemUnitUpValues;
import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.UpdateItemUnitUpValues;
import es.franricodev.shopping_list_gestor_service.upItemUnit.exception.UpItemUnitException;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;

import java.util.List;
import java.util.Optional;

public interface UpItemUnitService {
    UpItemUnit createUpItemUnit(RequestCreateUpItemUnitData requestCreateUpItemUnitData);

    UpItemUnit updateUpItemUnit(UpItemUnit upItemUnit);

    void deleteUpItemUnitGroupeByPrice(Double price) throws UpItemUnitException;

    void deleteLogicUpItemUnit(UpItemUnit upItemUnit);

    Optional<UpItemUnit> findByUnitaryPrice(double price);

    void updateItemUnitUpValues(RequestUpdateItemUnitUpValues request);

    Optional<UpItemUnit> findById(Long id);

    double upItemUnitTotalPrice(List<Long> ids);

    Long searchUnitaryPrice(UpItemUnit upItemUnit, Double unitaryPrice);

    void updateUpItemUnitValues(UpdateItemUnitUpValues request);

    Double getItemUnitUpCalculatedPrice(UpItemUnit itemUnitUp);
}
