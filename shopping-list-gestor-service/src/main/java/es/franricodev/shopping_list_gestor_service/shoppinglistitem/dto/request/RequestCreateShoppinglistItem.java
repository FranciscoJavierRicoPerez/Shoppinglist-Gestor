package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import lombok.Getter;

@Getter
public class RequestCreateShoppinglistItem {

    private Long productId;
    private String productName;
    private String calculateSystemCode;

}
