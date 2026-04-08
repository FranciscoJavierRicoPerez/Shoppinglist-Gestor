package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import java.util.List;

public record RequestUpdateShoppinglistItemItemUnitsUp(
        List<RequestUpItemUnitUpdateMetadata> requestUpItemUnitUpdateMetadataList
) {
}
