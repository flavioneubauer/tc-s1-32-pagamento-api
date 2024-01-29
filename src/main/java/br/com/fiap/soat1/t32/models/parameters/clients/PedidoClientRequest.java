package br.com.fiap.soat1.t32.models.parameters.clients;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoClientRequest {

    private StatusPreparacaoPedido statusPreparacao;
    private ClienteClientRequest cliente;
    private List<PedidoProdutoClientRequest> produtos;
}