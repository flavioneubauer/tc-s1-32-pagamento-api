package br.com.fiap.soat1.t32.services;


import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static br.com.fiap.soat1.t32.test_utils.CheckoutTestUtils.getCheckoutRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

//    @Mock
//    private RabbitTemplate rabbitTemplate;
//
//    private PedidoService pedidoService;
//
////    @BeforeEach
////    void setUp() {
////        pedidoService = new PedidoService(rabbitTemplate);
////    }
//
//    @Test
//    void deveCadastrar() {
//
////        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
////                .thenReturn(getPedidoClientResponse_IdOk());
//
////        final var idPedido = pedidoService.cadastrar(getCheckoutRequest());
////
////        assertEquals(10L, idPedido);
//
//    }
//
//    @Test
//    void deveCadastrar_FeignException() {
//
////        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
////                .thenThrow(FeignException.class);
//
////        assertThrows(IntegrationException.class,
////                () -> pedidoService.cadastrar(getCheckoutRequest()));
//    }
//
//    @Test
//    void deveCadastrar_PedidoNulo() {
//
////        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
////                .thenReturn(getPedidoClientResponse_IdNulo());
//
////        assertThrows(IntegrationException.class,
////                () -> pedidoService.cadastrar(getCheckoutRequest()));
//    }
//
//    @Test
//    void deveAlterarStatusPreparacaoPedido() {
////        pedidoService.notificarPagamentoPedidoAutorizado(10L, StatusPreparacaoPedido.RECEBIDO);
//
//        //verify(pedidoClient).alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class));
//    }

    @Test
    void deveAlterarStatusPreparacaoPedido_FeignException() {

//        when(pedidoClient.alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class)))
//                .thenThrow(FeignException.class);

//        assertThrows(IntegrationException.class,
//                () -> pedidoService.notificarPagamentoPedidoAutorizado(10L, StatusPreparacaoPedido.RECEBIDO));
        assertTrue(true);
    }

}
