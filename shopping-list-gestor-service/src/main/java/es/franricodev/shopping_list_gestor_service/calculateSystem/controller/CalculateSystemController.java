package es.franricodev.shopping_list_gestor_service.calculateSystem.controller;

import es.franricodev.shopping_list_gestor_service.calculateSystem.constants.api.ApiCalculateSystemConstants;
import es.franricodev.shopping_list_gestor_service.calculateSystem.dto.response.ResponseGetAllCalculateSystems;
import es.franricodev.shopping_list_gestor_service.constants.GeneralConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(
        name = ApiCalculateSystemConstants.API_NAME,
        description = ApiCalculateSystemConstants.API_DESCRIPTION
)
public interface CalculateSystemController {

    @Operation(summary = ApiCalculateSystemConstants.GET_ALL_V1_OP_SUMMARY)
    @ApiResponses({
            @ApiResponse(
                    responseCode = GeneralConstants.HTTP_200,
                    content = {
                        @Content(
                                schema = @Schema(implementation = ResponseGetAllCalculateSystems.class),
                                mediaType = GeneralConstants.APPLICATION_JSON
                        )
                    })
    })
    @GetMapping(ApiCalculateSystemConstants.GET_ALL_V1)
    ResponseEntity<ResponseGetAllCalculateSystems> getAllCalculateSystems();
}
