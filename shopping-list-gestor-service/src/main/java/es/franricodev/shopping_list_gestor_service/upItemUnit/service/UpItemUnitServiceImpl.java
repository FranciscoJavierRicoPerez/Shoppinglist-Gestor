package es.franricodev.shopping_list_gestor_service.upItemUnit.service;

import es.franricodev.shopping_list_gestor_service.upItemUnit.dto.request.RequestCreateUpItemUnitData;
import es.franricodev.shopping_list_gestor_service.upItemUnit.model.UpItemUnit;
import es.franricodev.shopping_list_gestor_service.upItemUnit.repository.UpItemUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpItemUnitServiceImpl implements UpItemUnitService{

    @Autowired
    private UpItemUnitRepository upItemUnitRepository;

    @Override
    public UpItemUnit createUpItemUnit(RequestCreateUpItemUnitData requestCreateUpItemUnitData) {
        log.info("Creating a new up item unit");
        UpItemUnit upItemUnit = new UpItemUnit();
        upItemUnit.setQuantity(requestCreateUpItemUnitData.getQuantity());
        upItemUnit.setUnityPrice(requestCreateUpItemUnitData.getUnitaryPrice());
        return upItemUnitRepository.save(upItemUnit);
    }

    @Override
    public UpItemUnit updateUpItemUnit(UpItemUnit upItemUnit) {
        return upItemUnitRepository.save(upItemUnit);
    }

}
