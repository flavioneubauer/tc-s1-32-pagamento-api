package br.com.fiap.soat1.t32.models.queues;

import lombok.Data;

@Data
public class FalhaCriacaoPedido {
	private Long idPagamento;
	private String mensagem;
}
