package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v2;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v2.ApiShoppinglistV2Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseCreateShoppinglist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = ApiShoppinglistV2Constants.API_NAME,
        description = ApiShoppinglistV2Constants.API_DESCRIPTION
)
public interface ShoppinglistV2Controller {
    @Operation(summary = ApiShoppinglistV2Constants.CREATE_V2_OP_SUMMARY)
    @PostMapping(ApiShoppinglistV2Constants.CREATE_V2)
    ResponseEntity<ResponseCreateShoppinglist> createShoppinglistV2(@NotNull @RequestBody RequestCreateShoppinglistDTO request);
}
