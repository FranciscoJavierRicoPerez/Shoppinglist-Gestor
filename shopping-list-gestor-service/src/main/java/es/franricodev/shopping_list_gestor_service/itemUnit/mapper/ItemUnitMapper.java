package es.franricodev.shopping_list_gestor_service.itemUnit.mapper;

import es.franricodev.shopping_list_gestor_service.calculateSystem.mapper.CalculateSystemMapper;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.ItemUnitDTO;
import es.franricodev.shopping_list_gestor_service.itemUnit.model.ItemUnit;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.ShoppinglistItemMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CalculateSystemMapper.class})
public interface ItemUnitMapper {

    // ItemUnitMapper INSTANCE = Mappers.getMapper(ItemUnitMapper.class);

    ItemUnit itemUnitToItemUnitDto(ItemUnitDTO itemUnitDTO);

    ItemUnitDTO itemUnitDtoToItemUnit(ItemUnit itemUnit);

    List<ItemUnitDTO> itemUnitListToItemUnitDtoList(List<ItemUnit> itemUnits);

    List<ItemUnit> itemUnitDtoListToItemUnitDto(List<ItemUnitDTO> itemUnitDTOS);

}
