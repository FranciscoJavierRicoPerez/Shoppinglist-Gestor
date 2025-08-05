package es.franricodev.shopping_list_gestor_service.calculateSystem.controller;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;
import es.franricodev.shopping_list_gestor_service.calculateSystem.service.CalculateSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/calculateSystem")
public class CalculateSystemControllerImpl implements CalculateSystemController {

    @Autowired
    private CalculateSystemService calculateSystemService;

    @GetMapping("/v1/all")
    @Override
    public ResponseEntity<List<CalculateSystemDTO>> getAllCalculateSystems() {
        log.info("Getting all the calculate systems in the database");
        List<CalculateSystemDTO> calculateSystemDTOList = null;
        HttpStatus httpStatus = HttpStatus.OK;
        calculateSystemDTOList = calculateSystemService.findAll();
        return new ResponseEntity<>(calculateSystemDTOList, httpStatus);
    }
}
