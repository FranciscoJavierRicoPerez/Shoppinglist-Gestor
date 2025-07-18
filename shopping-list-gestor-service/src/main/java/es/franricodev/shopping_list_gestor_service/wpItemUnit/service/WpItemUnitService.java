package es.franricodev.shopping_list_gestor_service.wpItemUnit.service;

import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestCreateWpItemUnitData;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;

public interface WpItemUnitService {
    WpItemUnit createWpItemUnit(RequestCreateWpItemUnitData requestCreateWpItemUnitData);
}
