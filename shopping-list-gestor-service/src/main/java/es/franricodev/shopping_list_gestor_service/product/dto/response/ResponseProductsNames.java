package es.franricodev.shopping_list_gestor_service.product.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@Builder
public class ResponseProductsNames {
    private List<String> productsNames;
}
