package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static br.com.fiap.soat1.t32.test_utils.CheckoutTestUtils.getCheckoutRequest;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService(pedidoService, pagamentoService);
    }

    @Test
    void deveRealizarCheckout() {
//        when(pedidoService.cadastrar(any(CheckoutRequest.class)))
//                .thenReturn(1L);

        final var pedido = checkoutService.realizarCheckout(getCheckoutRequest());

        assertEquals(1L, pedido);
//        verify(pedidoService).cadastrar(any(CheckoutRequest.class));
        verify(pagamentoService).cadastrar(anyLong());
    }

    @Test
    void deveRealizarCheckout_ComCheckoutNulo_DeveLancarValidationException() {
        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(null));
    }

    @Test
    void deveRealizarCheckout_ComProdutosNulos_DeveLancarValidationException() {
        CheckoutRequest checkout = new CheckoutRequest();

        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(checkout));
    }

    @Test
    void deveRealizarCheckout_ComProdutosVazios_DeveLancarValidationException() {
        CheckoutRequest checkout = new CheckoutRequest();
        checkout.setProdutos(Collections.emptyList());

        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(checkout));
    }
}
