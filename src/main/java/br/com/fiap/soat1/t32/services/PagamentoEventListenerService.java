package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.models.queues.FalhaCriacaoPedido;
import br.com.fiap.soat1.t32.models.queues.PedidoCriado;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoEventListenerService {

	private final PagamentoService pagamentoService;

	@RabbitListener(queues = {"${fila.falha-criacao-pedido.nome}"})
	public void registrarFalhaCriacaoPedido(FalhaCriacaoPedido falhaCriacaoPedido){
		pagamentoService.registrarFalhaCriacaoPedido(falhaCriacaoPedido);
	}

	@RabbitListener(queues = {"${fila.pedido-criado.nome}"})
	public void associarPedidoPagamento(PedidoCriado pedidoCriado){
		pagamentoService.associarPedidoPagamento(pedidoCriado);
	}

}