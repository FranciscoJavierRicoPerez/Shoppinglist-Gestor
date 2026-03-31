package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

public record RequestUpItemUnitUpdateMetadata(
        Long idItemUnit,
        Long idItemUnitUp,
        Integer newQuantity,
        Boolean removeItemUnitUp
) {
}
