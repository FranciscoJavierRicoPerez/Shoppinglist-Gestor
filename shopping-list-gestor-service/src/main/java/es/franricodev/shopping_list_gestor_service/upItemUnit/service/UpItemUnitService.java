package es.franricodev.shopping_list_gestor_service.upItemUnit.service;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestCreateUpItemUnitData;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;

public interface UpItemUnitService {
    UpItemUnit createUpItemUnit(RequestCreateUpItemUnitData requestCreateUpItemUnitData);

    UpItemUnit updateUpItemUnit(UpItemUnit upItemUnit);
}
