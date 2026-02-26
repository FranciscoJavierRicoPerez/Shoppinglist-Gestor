package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

import es.franricodev.shopping_list_gestor_service.shoppinglist.constants.api.ApiShoppinglistViewConstants;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistDetailsMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ResponseGetShoppinglistTableMetadata;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(
        name = ApiShoppinglistViewConstants.API_NAME,
        description = ApiShoppinglistViewConstants.API_DESCRIPTION
)
public interface ShoppinglistViewController {
    @Operation(summary = "Get the metainformation necesary for the build of the shoppinglist table view")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseGetShoppinglistTableMetadata.class),
                                            mediaType = "application/json"
                                    )
                            }
                    )
            }
    )
    @GetMapping(ApiShoppinglistViewConstants.GET_ALL_V1)
    ResponseEntity<ResponseGetShoppinglistTableMetadata> getShoppinglistTableMetadata();

    @Operation(summary = "Get the metainformation necessary for the build of the shoppinglist details view")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            schema = @Schema(implementation = ResponseGetShoppinglistDetailsMetadata.class),
                                            mediaType = "application/json"
                                    )
                            }
                    )
            }
    )
    @GetMapping(ApiShoppinglistViewConstants.GET_DETAILS_V1)
    ResponseEntity<ResponseGetShoppinglistDetailsMetadata> getShoppinglistDetailsMetadata(@PathVariable(name = ApiShoppinglistViewConstants.ID_SHOPPINGLIST) Long idShoppinglist);
}
