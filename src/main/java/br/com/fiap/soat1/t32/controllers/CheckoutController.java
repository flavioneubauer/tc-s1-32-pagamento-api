package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import br.com.fiap.soat1.t32.models.presenters.CheckoutResponse;
import br.com.fiap.soat1.t32.models.presenters.RespostaErro;
import br.com.fiap.soat1.t32.services.CheckoutService;
import br.com.fiap.soat1.t32.utils.mappers.CheckoutMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.fiap.soat1.t32.utils.mappers.CheckoutMapper.map;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Checkout", description = "API de Checkout de Pedidos")
@RequestMapping(value = "/v1/checkout",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Operation(description = "Realiza checkout de pedido")
    @ApiResponse(responseCode = "201", description = "Checkout realizado")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
        content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CheckoutResponse> realizaCheckout(@RequestBody CheckoutRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(map(checkoutService.realizarCheckout(request)));
    }
}
