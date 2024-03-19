package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import br.com.fiap.soat1.t32.models.queues.CriacaoPedido;
import br.com.fiap.soat1.t32.models.queues.PagamentoPedidoAutorizado;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

	private final RabbitTemplate rabbitTemplate;
	@Value("${fila.pedidos.nome}")
	private String filaPedidos;

	@Value("${fila.pagamento-pedido-autorizado.nome}")
	private String filaPagamentoPedidoAutorizado;

	public boolean cadastrar(Long idPagamento, final CheckoutRequest checkout) {
		try {
			CriacaoPedido criacaoPedido = CriacaoPedido.builder()
				.pedido(checkout)
				.idPagamento(idPagamento)
				.build();
			rabbitTemplate.convertAndSend(filaPedidos, criacaoPedido);
			return true;
		} catch (AmqpException e) {
			log.error("Failed to push to queue criacao-pedido with paymentId" + idPagamento, e);
			return false;
		}
	}

	public void notificarPagamentoPedidoAutorizado(Long idPedido) {
		try {
			rabbitTemplate.convertAndSend(filaPagamentoPedidoAutorizado, PagamentoPedidoAutorizado.builder()
					.idPedido(idPedido)
					.build());
		} catch (AmqpException e) {
			log.error("Failed to push to queue pagamento-pedido-autorizado" + idPedido, e);
		}
	}

}
