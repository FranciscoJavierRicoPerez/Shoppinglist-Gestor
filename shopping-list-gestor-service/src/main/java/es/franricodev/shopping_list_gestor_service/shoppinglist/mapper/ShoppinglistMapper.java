package es.franricodev.shopping_list_gestor_service.shoppinglist.mapper;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShoppinglistMapper {

    ShoppinglistMapper INSTANCE = Mappers.getMapper(ShoppinglistMapper.class);

    ShoppinglistDTO toDTO(Shoppinglist shoppinglist);

    Shoppinglist toEntity(ShoppinglistDTO shoppinglistDTO);

    List<ShoppinglistDTO> toDTOList(List<Shoppinglist> shoppinglistList);

    List<Shoppinglist> toEntityList(List<ShoppinglistDTO> shoppinglistDTOS);

    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    Shoppinglist createShoppinglist(RequestCreateShoppinglistDTO request);

    void updateShoppinglist(@MappingTarget Shoppinglist shoppinglist, RequestUpdateShoppinglistDTO request);

    ShoppinglistDetailsDTO shoppinglistToShoppinglistDetailsDTO(Shoppinglist shoppinglist);

}
