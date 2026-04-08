package es.franricodev.shopping_list_gestor_service.wpItemUnit.controller;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.constants.api.ApiItemUnitWpConstants;
import es.franricodev.shopping_list_gestor_service.wpItemUnit.dto.request.RequestUpdateItemUnitWp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = ApiItemUnitWpConstants.API_NAME,
        description = ApiItemUnitWpConstants.API_DESCRIPTION
)
public interface WpItemUnitController {
    @Operation(summary = ApiItemUnitWpConstants.UPDATE_ITEM_UNIT_WP_V1_OP_SUMMARY)
    @ApiResponses({
      @ApiResponse(responseCode = GeneralConstants.HTTP_204, description = GeneralConstants.DESC_204)
    })
    @PutMapping(ApiItemUnitWpConstants.UPDATE_ITEM_UNIT_WP_V1)
    ResponseEntity<Void> updateItemUnitWp(@PathVariable(ApiItemUnitWpConstants.ID_ITEM_UNIT_WP) Long idItemUnitWp, @RequestBody RequestUpdateItemUnitWp request);
}
