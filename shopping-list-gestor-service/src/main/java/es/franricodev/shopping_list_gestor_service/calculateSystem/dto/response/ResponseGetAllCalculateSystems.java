package es.franricodev.shopping_list_gestor_service.calculateSystem.dto.response;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;

import java.util.List;

public record ResponseGetAllCalculateSystems(
        List<CalculateSystemDTO> calculateSystemDTOList
) {
}
