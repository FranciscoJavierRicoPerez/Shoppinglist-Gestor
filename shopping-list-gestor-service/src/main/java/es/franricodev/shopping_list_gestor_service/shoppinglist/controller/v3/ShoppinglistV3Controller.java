package es.franricodev.shopping_list_gestor_service.shoppinglist.controller.v3;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.v3.ApiShoppinglistV3Constants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseError;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.response.ResponseDeleteShoppinglistItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = ApiShoppinglistV3Constants.API_NAME,
        description = ApiShoppinglistV3Constants.API_DESCRIPTION
)
public interface ShoppinglistV3Controller {
    @Operation(summary = ApiShoppinglistV3Constants.DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    description = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseDeleteShoppinglistItem.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON)
                    }),
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_409,
                    description = GeneralConstants.DESC_409,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseError.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            ),
            @ApiResponse (
                    responseCode = GeneralConstants.HTTP_404,
                    description = GeneralConstants.DESC_404,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseError.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            )
    })
    @DeleteMapping(ApiShoppinglistV3Constants.DELETE_LOGIC_SHOPPINGLIST_ITEM_FROM_SHOPPINGLIST_V3)
    ResponseEntity<?> deleteShoppinglistItem(
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST) Long idShoppinglist,
            @PathVariable(ApiShoppinglistV3Constants.ID_SHOPPINGLIST_ITEM) Long idShoppinglistItem
    );
}
