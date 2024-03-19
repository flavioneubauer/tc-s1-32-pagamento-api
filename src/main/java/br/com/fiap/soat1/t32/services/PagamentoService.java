package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.exceptions.NotFoundException;
import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.models.parameters.PagamentoPedidoRequest;
import br.com.fiap.soat1.t32.models.queues.FalhaCriacaoPedido;
import br.com.fiap.soat1.t32.models.queues.PedidoCriado;
import br.com.fiap.soat1.t32.repositories.PagamentoCrudRepository;
import br.com.fiap.soat1.t32.utils.mappers.PagamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.fiap.soat1.t32.utils.mappers.PagamentoMapper.map;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoCrudRepository pagamentoRepository;
    private final PedidoService pedidoService;
    private final MeioPagamentoService meioPagamentoService;

    @Transactional
    public PagamentoDb cadastrar(final Long idPedido) {
        return pagamentoRepository.save(PagamentoMapper.map(idPedido));
    }

    @Transactional
    public void remover(PagamentoDb pagamentoDb) {
        pagamentoRepository.delete(pagamentoDb);
    }

    @Transactional(readOnly = true)
    public Optional<PagamentoDb> buscarPorId(final Long idPagamento){
        return pagamentoRepository.findById(idPagamento);
    }

    @Transactional
    public void associarPedidoPagamento(PedidoCriado pedidoCriado){
        Optional<PagamentoDb> pagamentoDbOptional = pagamentoRepository.findById(pedidoCriado.getIdPagamento());
        if(pagamentoDbOptional.isPresent()){
            PagamentoDb pagamentoDb = pagamentoDbOptional.get();

            if(pagamentoDb.getIdPedido() == null){
                pagamentoDb.setIdPedido(pedidoCriado.getIdPedido());
                pagamentoDb.setCallbackMeioPagamento(meioPagamentoService.montarUrlProcessamentoPagamento(pagamentoDb.getId(),pagamentoDb.getIdPedido()));
                pagamentoRepository.save(pagamentoDb);
            }
        }
    }

    @Transactional
    public void registrarFalhaCriacaoPedido(FalhaCriacaoPedido falhaCriacaoPedido){
        Optional<PagamentoDb> pagamentoDbOptional = pagamentoRepository.findById(falhaCriacaoPedido.getIdPagamento());
        if(pagamentoDbOptional.isPresent()){
            PagamentoDb pagamentoDb = pagamentoDbOptional.get();
            pagamentoDb.setMensagemErro(falhaCriacaoPedido.getMensagem());
            pagamentoRepository.save(pagamentoDb);
        }
    }

    @Transactional
    public void recebeAtualizacaoPagamento(Long pagamentoId, PagamentoPedidoRequest pagamentoPedido) {
        final var pagamentoDbOptional = pagamentoRepository.findById(pagamentoId);

        if(pagamentoDbOptional.isEmpty())
            throw new NotFoundException("Pagamento não encontrado");

        PagamentoDb pagamento = pagamentoDbOptional.get();
        validaPagamento(pagamento);

        if(TRUE.equals(pagamentoPedido.getPagamentoAprovado())) {
            this.pagamentoRepository.save(map(pagamento, StatusPagamentoPedido.APROVADO));
            this.pedidoService.notificarPagamentoPedidoAutorizado(pagamento.getIdPedido());
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