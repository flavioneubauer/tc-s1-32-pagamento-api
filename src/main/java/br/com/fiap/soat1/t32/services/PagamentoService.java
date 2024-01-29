package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.models.parameters.PagamentoPedidoRequest;
import br.com.fiap.soat1.t32.repositories.PagamentoCrudRepository;
import br.com.fiap.soat1.t32.utils.mappers.PagamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.fiap.soat1.t32.utils.mappers.PagamentoMapper.map;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoCrudRepository pagamentoRepository;
    private final PedidoService pedidoService;

    public void cadastrar(final Long idPedido) {
        pagamentoRepository.save(PagamentoMapper.map(idPedido));
    }

    public void recebeAtualizacaoPagamento(PagamentoPedidoRequest pagamentoPedido) {
        final var pagamento = pagamentoRepository.findByIdPedido(pagamentoPedido.getIdPedido());

        validaPagamento(pagamento);

        if(TRUE.equals(pagamentoPedido.getPagamentoAprovado())) {
            this.pagamentoRepository.save(map(pagamento, StatusPagamentoPedido.APROVADO));
            this.pedidoService.alterarStatusPreparacaoPedido(pagamentoPedido.getIdPedido(), StatusPreparacaoPedido.RECEBIDO);
        } else {
            this.pagamentoRepository.save(map(pagamento, StatusPagamentoPedido.RECUSADO));
        }
    }

    private void validaPagamento(PagamentoDb pagamento) {
        if(isNull(pagamento)) {
            throw new ValidationException("Não existe pagamento pendente para o pedido informado.");
        } else if(StatusPagamentoPedido.APROVADO.equals(pagamento.getStatusPagamento())) {
    		throw new ValidationException("Já consta aprovação do pagamento para o pedido " + pagamento.getIdPedido());
    	}
    }
}