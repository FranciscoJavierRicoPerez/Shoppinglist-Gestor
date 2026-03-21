package es.franricodev.shopping_list_gestor_service.itemUnit.controller;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.constants.api.ApiItemUnitConstants;
import es.franricodev.shopping_list_gestor_service.itemUnit.dto.request.RequestUpdateItemUnitWpTotalPrice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = ApiItemUnitConstants.BASE_URL,
        description = ApiItemUnitConstants.API_DESCRIPTION
)
public interface ItemUnitController {
    @Operation(summary = ApiItemUnitConstants.UPDATE_ITEM_UNIT_TOTAL_PRICE_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(responseCode = GeneralConstants.HTTP_204, description = GeneralConstants.DESC_204)
    })
    @PutMapping(ApiItemUnitConstants.UPDATE_ITEM_UNIT_TOTAL_PRICE_V1)
    ResponseEntity<Void> updateItemUnit(@PathVariable(ApiItemUnitConstants.ID_ITEM_UNIT) Long idItemUnit, @RequestBody RequestUpdateItemUnitWpTotalPrice request);
}
