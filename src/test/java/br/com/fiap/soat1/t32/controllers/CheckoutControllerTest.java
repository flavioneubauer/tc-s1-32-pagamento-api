package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.controllers.CheckoutController;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import br.com.fiap.soat1.t32.services.CheckoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.fiap.soat1.t32.test_utils.CheckoutTestUtils.getCheckoutRequest;
import static br.com.fiap.soat1.t32.test_utils.JsonHelper.asJsonString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CheckoutController.class)
@ExtendWith(MockitoExtension.class)
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckoutService checkoutService;

    @Test
    void deveRealizarCheckout() throws Exception {
        when(checkoutService.realizarCheckout(ArgumentMatchers.any(CheckoutRequest.class)))
                .thenReturn(10L);

        mockMvc.perform(post("/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(getCheckoutRequest())))
                .andExpect(status().isCreated())
                .andReturn();

        verify(checkoutService).realizarCheckout(ArgumentMatchers.any(CheckoutRequest.class));
    }

}
