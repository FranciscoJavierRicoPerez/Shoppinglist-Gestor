package es.franricodev.shopping_list_gestor_service.calculateSystem.service;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;
import es.franricodev.shopping_list_gestor_service.calculateSystem.error.CalculateSystemError;
import es.franricodev.shopping_list_gestor_service.calculateSystem.exception.CalculateSystemException;
import es.franricodev.shopping_list_gestor_service.calculateSystem.mapper.CalculateSystemMapper;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import es.franricodev.shopping_list_gestor_service.calculateSystem.repository.CalculateSystemRepository;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CalculateSystemServicelmpl implements CalculateSystemService{

    @Autowired
    private CalculateSystemRepository calculateSystemRepository;

    @Autowired
    private CalculateSystemMapper calculateSystemMapper;

    @Override
    public Double calculateProductPrice(CalculateSystem calculateSystem, Product product) {
        return 0.0;
    }

    @Override
    public CalculateSystem findCalculateSystemByCode(String code) throws CalculateSystemException {
        log.info("Find the calculate system with code: {}", code);
        return calculateSystemRepository.findByCode(code).orElseThrow(() -> new CalculateSystemException(CalculateSystemError.CALCULATE_SYSTEM_NOT_FOUND));
    }

    @Override
    public void assignShoppinglistItemToCalculateSystem(ShoppinglistItem shoppinglistItem, CalculateSystem calculateSystem) {
        List<ShoppinglistItem> shoppinglistItemList = calculateSystem.getItems();
        if (shoppinglistItemList == null) {
            shoppinglistItemList = new ArrayList<>();
            calculateSystem.setItems(shoppinglistItemList);
        }

        shoppinglistItem.setCalculateSystem(calculateSystem);
        shoppinglistItemList.add(shoppinglistItem);

        calculateSystemRepository.save(calculateSystem);

    }

    @Override
    public List<CalculateSystemDTO> findAll() {
        log.info("Getting all the calculate systems");
        return calculateSystemMapper.calculateSystemListToCalculateSystemDTOList(calculateSystemRepository.findAll());
    }

    @Override
    public CalculateSystem findCalculateSystemById(Long id) throws CalculateSystemException {
        return calculateSystemRepository.findById(id).orElseThrow(() -> new CalculateSystemException(CalculateSystemError.CALCULATE_SYSTEM_NOT_FOUND));
    }

}
