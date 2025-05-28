package es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RequestCreateShoppinglistItem {

    private Long productId;
    private String productName;
    private Long calculateSystemId;

}
