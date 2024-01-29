package br.com.fiap.soat1.t32.utils.mappers;

import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import br.com.fiap.soat1.t32.models.parameters.clients.ClienteClientRequest;
import br.com.fiap.soat1.t32.models.parameters.clients.PedidoClientRequest;
import br.com.fiap.soat1.t32.models.parameters.clients.PedidoProdutoClientRequest;
import br.com.fiap.soat1.t32.models.parameters.clients.ProdutoClientRequest;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoMapper {

    public static PedidoClientRequest map(final CheckoutRequest checkout) {
        return PedidoClientRequest.builder()
                .cliente(isNull(checkout.getCliente()) ?
                        null : ClienteClientRequest.builder().id(checkout.getCliente().getId()).build())
                .produtos(checkout.getProdutos().stream()
                        .map(produto -> PedidoProdutoClientRequest.builder()
                                .produto(ProdutoClientRequest.builder().id(produto.getId()).build())
                                .quantidade(produto.getQuantidade())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

}
