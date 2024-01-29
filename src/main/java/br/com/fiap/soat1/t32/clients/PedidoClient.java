package br.com.fiap.soat1.t32.clients;

import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.models.parameters.clients.PedidoClientRequest;
import br.com.fiap.soat1.t32.models.presenters.clients.PedidoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "pedidoClient", url = "${pedido.api.base-path}")
public interface PedidoClient {

    @PostMapping(value = "/v1/pedidos", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    PedidoClientResponse cadastrar(@RequestBody PedidoClientRequest checkoutRequest);

    @PutMapping(value = "/v1/pedidos/{id}/{status}", consumes = APPLICATION_JSON_VALUE)
    Void alterarStatusPreparacaoPedido(@PathVariable Long id, @PathVariable StatusPreparacaoPedido status);




}
