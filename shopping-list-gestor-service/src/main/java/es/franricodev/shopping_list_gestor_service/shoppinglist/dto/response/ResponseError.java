package es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response;

public record ResponseError(
        String httpError,
        String httpDescription,
        String specificMessage
) {
}
