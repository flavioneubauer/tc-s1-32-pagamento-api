package br.com.fiap.soat1.t32.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MeioPagamentoService {

	@Value("${url.externa}")
	private String urlExterna;

	public String montarUrlProcessamentoPagamento(Long pagamentoId, Long pedidoId){
		return urlExterna + "/meiopagamento?pagamento="+pagamentoId + "&pedido=" + pedidoId;
	}
}
