package es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper;

import es.franricodev.shopping_list_gestor_service.calculateSystem.mapper.CalculateSystemMapper;
import es.franricodev.shopping_list_gestor_service.itemUnit.mapper.ItemUnitMapper;
import es.franricodev.shopping_list_gestor_service.product.mapper.ProductMapper;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.ShoppinglistItemMetadataDTO;
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

    @Mapping(target = "product", expression = "java(shoppinglistItem.getProducts().size() > 0 ? productMapper.productToProductDto(shoppinglistItem.getProducts().stream().findFirst().get()) : null)")
    // @Mapping(target = "calculateSystem", expression = "java(shoppinglistItem.getCalculateSystems().size() > 0 ? calculateSystemMapper.calculateSystemToCalculateSystemDTO(shoppinglistItem.getCalculateSystems().stream().findFirst().get()) : null)")
    ShoppinglistItemDTO shoppinglistItemToShoppinglistItemDTO(ShoppinglistItem shoppinglistItem);

    ShoppinglistItem shoppinglistItemDtoToShoppinglistItem(ShoppinglistItemDTO shoppinglistItemDTO);

    List<ShoppinglistItemDTO> shoppinglistItemListToShoppinglistItemDtoList(List<ShoppinglistItem> shoppinglistItemList);

    List<ShoppinglistItem> shoppinglistItemDtoListToShoppinglistItemList(List<ShoppinglistItemDTO> shoppinglistItemDTOS);

    @Mapping(target = "calculateSystemCode", expression = "java(shoppinglistItem.getCalculateSystem() != null ? shoppinglistItem.getCalculateSystem().getCode() : null)")
    ShoppinglistItemMetadataDTO shoppinglistItemToShoppinglistItemMetadataDTO(ShoppinglistItem shoppinglistItem);

}
