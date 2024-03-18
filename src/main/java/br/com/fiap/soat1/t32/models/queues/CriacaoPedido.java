package br.com.fiap.soat1.t32.models.queues;

import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriacaoPedido {
	private Long idPagamento;
	private CheckoutRequest pedido;
}
