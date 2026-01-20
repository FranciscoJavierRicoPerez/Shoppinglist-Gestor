package es.franricodev.shopping_list_gestor_service.product.controller;

import es.franricodev.shopping_list_gestor_service.product.dto.ProductDTO;
import es.franricodev.shopping_list_gestor_service.product.dto.response.ResponseProductsNames;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(
        name = "Product Controller API Documentation",
        description = "Management of the operations related with the products"
)
public interface ProductController {
    @Operation(summary = "Returns all the products in the database")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProductDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/v1/all")
    ResponseEntity<List<ProductDTO>> getAllProducts();

    @Operation(summary = "Return the name of all products in the database")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseProductsNames.class),
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/v1/names")
    ResponseEntity<ResponseProductsNames> getAllProductsNames();
}
