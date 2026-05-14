package es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.lombock;

import es.franricodev.shopping_list_gestor_service.shoppinglistitem.ShoppinglistItemMetadataDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.utils.DateUtils;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ShoppinglistItemMapper {

    public static List<ShoppinglistItemMetadataDTO> toShoppinglistItemMetadataDTOList(List<ShoppinglistItem> list) {
        return list.stream().map(ShoppinglistItemMapper::toShoppinglistItemMetadataDTO).toList();
    }

    public static ShoppinglistItemMetadataDTO toShoppinglistItemMetadataDTO(ShoppinglistItem shoppinglistItem) {
        return ShoppinglistItemMetadataDTO.builder()
                .id(shoppinglistItem.getId())
                .name(shoppinglistItem.getName())
                .assignationToListDate(DateUtils.formatDate(shoppinglistItem.getAssignationToListDate()))
                .calculatedPrice(shoppinglistItem.getCalculatedPrice())
                .calculateSystemCode(shoppinglistItem.getCalculateSystem().getCode())
                .build();
    }

}
