package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response;

import lombok.Data;

@Data
public class ResponseDeleteShoppinglistItem {
    private String message;
    private boolean delete;
}
