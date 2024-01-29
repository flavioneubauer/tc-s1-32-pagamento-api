package br.com.fiap.soat1.t32.services;


import br.com.fiap.soat1.t32.clients.PedidoClient;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.models.parameters.clients.PedidoClientRequest;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.soat1.t32.test_utils.CheckoutTestUtils.getCheckoutRequest;
import static br.com.fiap.soat1.t32.test_utils.PedidoTestUtils.getPedidoClientResponse_IdNulo;
import static br.com.fiap.soat1.t32.test_utils.PedidoTestUtils.getPedidoClientResponse_IdOk;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoClient pedidoClient;

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService(pedidoClient);
    }

    @Test
    void deveCadastrar() {

        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
                .thenReturn(getPedidoClientResponse_IdOk());

        final var idPedido = pedidoService.cadastrar(getCheckoutRequest());

        assertEquals(10L, idPedido);

    }

    @Test
    void deveCadastrar_FeignException() {

        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
                .thenThrow(FeignException.class);

        assertThrows(IntegrationException.class,
                () -> pedidoService.cadastrar(getCheckoutRequest()));
    }

    @Test
    void deveCadastrar_PedidoNulo() {

        when(pedidoClient.cadastrar(any(PedidoClientRequest.class)))
                .thenReturn(getPedidoClientResponse_IdNulo());

        assertThrows(IntegrationException.class,
                () -> pedidoService.cadastrar(getCheckoutRequest()));
    }

    @Test
    void deveAlterarStatusPreparacaoPedido() {
        pedidoService.alterarStatusPreparacaoPedido(10L, StatusPreparacaoPedido.RECEBIDO);

        verify(pedidoClient).alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class));
    }

    @Test
    void deveAlterarStatusPreparacaoPedido_FeignException() {

        when(pedidoClient.alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class)))
                .thenThrow(FeignException.class);

        assertThrows(IntegrationException.class,
                () -> pedidoService.alterarStatusPreparacaoPedido(10L, StatusPreparacaoPedido.RECEBIDO));
    }

}
