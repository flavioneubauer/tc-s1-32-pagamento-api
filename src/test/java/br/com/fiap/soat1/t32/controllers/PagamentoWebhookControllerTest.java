package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.models.parameters.PagamentoPedidoRequest;
import br.com.fiap.soat1.t32.services.PagamentoService;
import br.com.fiap.soat1.t32.test_utils.PagamentoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.fiap.soat1.t32.test_utils.JsonHelper.asJsonString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoWebhookController.class)
@ExtendWith(MockitoExtension.class)
public class PagamentoWebhookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService pagamentoService;

    @Test
    void deveRealizarCheckout() throws Exception {
        mockMvc.perform(post("/v1/webhook")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(PagamentoTestUtils.getPagamentoPedidoRequest_Aprovado())))
                .andExpect(status().isOk())
                .andReturn();

        verify(pagamentoService).recebeAtualizacaoPagamento(ArgumentMatchers.any(PagamentoPedidoRequest.class));
    }

}
