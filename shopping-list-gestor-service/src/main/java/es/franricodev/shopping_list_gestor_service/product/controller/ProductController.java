package es.franricodev.shopping_list_gestor_service.product.controller;

import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import es.franricodev.shopping_list_gestor_service.product.constants.api.ApiProductConstants;
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
        name = ApiProductConstants.API_NAME,
        description = ApiProductConstants.API_DESCRIPTION
)
public interface ProductController {
    @Operation(summary = ApiProductConstants.GET_ALL_PRODUCTS_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    description = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProductDTO.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            )
    })
    @GetMapping(ApiProductConstants.GET_ALL_PRODUCTS_V1)
    ResponseEntity<List<ProductDTO>> getAllProducts();

    @Operation(summary = ApiProductConstants.GET_ALL_PRODUCTS_NAMES_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    description = GeneralConstants.DESC_200,
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ResponseProductsNames.class),
                                    mediaType = GeneralConstants.APPLICATION_JSON
                            )
                    }
            )
    })
    @GetMapping(ApiProductConstants.GET_ALL_PRODUCTS_NAMES_V1)
    ResponseEntity<ResponseProductsNames> getAllProductsNames();
}
