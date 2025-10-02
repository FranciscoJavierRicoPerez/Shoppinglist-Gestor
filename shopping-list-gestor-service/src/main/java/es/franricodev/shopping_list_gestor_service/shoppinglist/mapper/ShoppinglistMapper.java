package es.franricodev.shopping_list_gestor_service.shoppinglist.mapper;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.ShoppinglistItemMapper;
import es.franricodev.shopping_list_gestor_service.utils.DateUtils;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ShoppinglistItemMapper.class})
public interface ShoppinglistMapper {

    // ShoppinglistMapper INSTANCE = Mappers.getMapper(ShoppinglistMapper.class);

    ShoppinglistDTO toDTO(Shoppinglist shoppinglist);

    Shoppinglist toEntity(ShoppinglistDTO shoppinglistDTO);

    List<ShoppinglistDTO> toDTOList(List<Shoppinglist> shoppinglistList);

    List<Shoppinglist> toEntityList(List<ShoppinglistDTO> shoppinglistDTOS);

    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    @Mapping(target = "code", qualifiedByName = "generateShoppinglistCode")
    @Mapping(target = "infoBlock", defaultValue = "false")
    Shoppinglist createShoppinglist(RequestCreateShoppinglistDTO request);

    void updateShoppinglist(@MappingTarget Shoppinglist shoppinglist, RequestUpdateShoppinglistDTO request);

    ShoppinglistDetailsDTO shoppinglistToShoppinglistDetailsDTO(Shoppinglist shoppinglist);

    @Named("generateShoppinglistCode")
    static String generateShoppinglistCode(String code) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SL-").append(code).append(DateUtils.formatDate(new Date())).toString();
    }

}
