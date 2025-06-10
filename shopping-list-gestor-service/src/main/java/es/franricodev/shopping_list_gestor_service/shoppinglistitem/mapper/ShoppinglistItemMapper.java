package es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper;

import es.franricodev.shopping_list_gestor_service.calculateSystem.mapper.CalculateSystemMapper;
import es.franricodev.shopping_list_gestor_service.itemUnit.mapper.ItemUnitMapper;
import es.franricodev.shopping_list_gestor_service.product.mapper.ProductMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CalculateSystemMapper.class, ProductMapper.class, ItemUnitMapper.class })
public interface ShoppinglistItemMapper {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    CalculateSystemMapper calculateSystemMapper = Mappers.getMapper(CalculateSystemMapper.class);

    @Mapping(target = "product", expression = "java(productMapper.productToProductDto(shoppinglistItem.getProducts().stream().findFirst().get()))")
    @Mapping(target = "calculateSystem", expression = "java(calculateSystemMapper.calculateSystemToCalculateSystemDTO(shoppinglistItem.getCalculateSystems().stream().findFirst().get()))")
    ShoppinglistItemDTO shoppinglistItemToShoppinglistItemDTO(ShoppinglistItem shoppinglistItem);

    ShoppinglistItem shoppinglistItemDtoToShoppinglistItem(ShoppinglistItemDTO shoppinglistItemDTO);

    List<ShoppinglistItemDTO> shoppinglistItemListToShoppinglistItemDtoList(List<ShoppinglistItem> shoppinglistItemList);

    List<ShoppinglistItem> shoppinglistItemDtoListToShoppinglistItemList(List<ShoppinglistItemDTO> shoppinglistItemDTOS);

}
