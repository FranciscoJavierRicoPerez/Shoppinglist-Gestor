package es.franricodev.shopping_list_gestor_service.wpItemUnit.service;

import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestCreateWpItemUnitData;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.model.WpItemUnit;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.repository.WpItemUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.save(wpItemUnit);
    }

    @Override
    public void deleteLogicWpItemUnit(WpItemUnit wpItemUnit) {
        wpItemUnit.setInfoBlock(true);
        repository.save(wpItemUnit);
    }

}
