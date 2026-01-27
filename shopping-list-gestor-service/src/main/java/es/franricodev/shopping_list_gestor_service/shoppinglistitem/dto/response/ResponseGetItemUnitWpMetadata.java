package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

public record ResponseGetItemUnitWpMetadata(
        Double priceKg,
        Double weight,
        Double calculatedPrice
) { }
