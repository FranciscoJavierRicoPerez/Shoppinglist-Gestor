package es.franricodev.shopping_list_gestor_service.shoppinglist.controller;

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
        name = "Shoppinglist View Controller API Documentation",
        description = "Management of all the visible data related with the shoppinglist and his details in the client"
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
    @GetMapping("v1/all")
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
    @GetMapping("v1/{id}/details")
    ResponseEntity<ResponseGetShoppinglistDetailsMetadata> getShoppinglistDetailsMetadata(@PathVariable("id") Long idShoppinglist);
}
