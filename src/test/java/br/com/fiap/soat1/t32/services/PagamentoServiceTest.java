package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.repositories.PagamentoCrudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.soat1.t32.test_utils.PagamentoTestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PagamentoCrudRepository pagamentoRepository;

    @Mock
    private PedidoService pedidoService;

    private PagamentoService pagamentoService;

//    @BeforeEach
//    void setUp() {
//        pagamentoService = new PagamentoService(pagamentoRepository, pedidoService);
//    }

    @Test
    void deveCadastrar() {
        pagamentoService.cadastrar(10L);

        verify(pagamentoRepository).save(any(PagamentoDb.class));
    }

    @Test
    void deveReceberAtualizacaoPagamento_RequestPagamentoAprovado() {
        when(pagamentoRepository.findByIdPedido(anyLong())).thenReturn(getPagamentoDb_Pendente());

//        pagamentoService.recebeAtualizacaoPagamento(getPagamentoPedidoRequest_Aprovado());

        verify(pagamentoRepository).save(any(PagamentoDb.class));
        //verify(pedidoService).notificarPagamentoPedidoAutorizado(anyLong(), any(StatusPreparacaoPedido.class));
    }

    @Test
    void deveReceberAtualizacaoPagamento_RequestPagamentoRecusado() {
        when(pagamentoRepository.findByIdPedido(anyLong())).thenReturn(getPagamentoDb_Pendente());

//        pagamentoService.recebeAtualizacaoPagamento(getPagamentoPedidoRequest_Recusado());

        verify(pagamentoRepository).save(any(PagamentoDb.class));
//        verify(pedidoService, never()).notificarPagamentoPedidoAutorizado(anyLong(), any(StatusPreparacaoPedido.class));
    }

    @Test
    void deveReceberAtualizacaoPagamento_PagamentoNulo() {
        when(pagamentoRepository.findByIdPedido(anyLong())).thenReturn(null);

//        Assertions.assertThrows(ValidationException.class,
//                () -> pagamentoService.recebeAtualizacaoPagamento(getPagamentoPedidoRequest_Aprovado()));

        verify(pagamentoRepository, never()).save(any(PagamentoDb.class));
//        verify(pedidoService, never()).notificarPagamentoPedidoAutorizado(anyLong(), any(StatusPreparacaoPedido.class));
    }

    @Test
    void deveReceberAtualizacaoPagamento_PagamentoAprovado() {
        when(pagamentoRepository.findByIdPedido(anyLong())).thenReturn(getPagamentoDb_Aprovado());

//        Assertions.assertThrows(ValidationException.class,
//                () -> pagamentoService.recebeAtualizacaoPagamento(getPagamentoPedidoRequest_Aprovado()));

        verify(pagamentoRepository, never()).save(any(PagamentoDb.class));
//        verify(pedidoService, never()).notificarPagamentoPedidoAutorizado(anyLong(), any(StatusPreparacaoPedido.class));
    }

}
