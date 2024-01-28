package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = mock(PedidoService.class);
        checkoutService = new CheckoutService(pedidoService);
    }

    @Test
    void realizarCheckout_ComCheckoutNulo_DeveLancarValidationException() {
        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(null));
    }

    @Test
    void realizarCheckout_ComProdutosNulos_DeveLancarValidationException() {
        CheckoutRequest checkout = new CheckoutRequest();

        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(checkout));
    }

    @Test
    void realizarCheckout_ComProdutosVazios_DeveLancarValidationException() {
        CheckoutRequest checkout = new CheckoutRequest();
        checkout.setProdutos(Collections.emptyList());

        assertThrows(ValidationException.class, () -> checkoutService.realizarCheckout(checkout));
    }
}
