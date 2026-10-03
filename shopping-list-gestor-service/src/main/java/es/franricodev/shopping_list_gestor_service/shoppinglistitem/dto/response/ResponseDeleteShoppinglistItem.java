package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDeleteShoppinglistItem {
    private String message;
    private boolean delete;
}
