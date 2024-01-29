package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.models.parameters.PagamentoPedidoRequest;
import br.com.fiap.soat1.t32.models.presenters.RespostaErro;
import br.com.fiap.soat1.t32.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Tag(name = "Webhook", description = "Webhook de pagamento de pedido")
@RequestMapping(value = "/v1/webhook",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class PagamentoWebhookController {

    private final PagamentoService pagamentoService;

    @Operation(description = "Endpoint para receber notificação acerca de alteração do status de pagamento do pedido.")
    @ApiResponse(responseCode = "200", description = "Pedido atualizado.")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @ApiResponse(responseCode = "500", description = "Erro de sistema",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> alteraPagamentoPedido(@RequestBody PagamentoPedidoRequest request) {

        pagamentoService.recebeAtualizacaoPagamento(request);

        return ResponseEntity.ok().build();
    }
}