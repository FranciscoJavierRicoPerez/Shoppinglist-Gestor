package es.franricodev.shopping_list_gestor_service.calculateSystem.mapper;

import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.CalculateSystemDTO;
import es.franricodev.shopping_list_gestor_service.calculateSystem.model.CalculateSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalculateSystemMapper {

    // CalculateSystemMapper INSTANCE = Mappers.getMapper(CalculateSystemMapper.class);

    CalculateSystemDTO calculateSystemToCalculateSystemDTO(CalculateSystem calculateSystem);

    List<CalculateSystemDTO> calculateSystemListToCalculateSystemDTOList(List<CalculateSystem> calculateSystemList);
}
