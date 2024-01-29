package br.com.fiap.soat1.t32.test_utils;

import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import br.com.fiap.soat1.t32.models.parameters.ClienteCheckoutRequest;
import br.com.fiap.soat1.t32.models.parameters.ProdutoCheckoutRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static org.assertj.core.util.Lists.newArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CheckoutTestUtils {

    public static CheckoutRequest getCheckoutRequest() {
        return CheckoutRequest.builder()
                .cliente(ClienteCheckoutRequest.builder()
                        .id(UUID.randomUUID())
                        .build())
                .produtos(newArrayList(ProdutoCheckoutRequest.builder()
                        .id(10L)
                        .quantidade(50L)
                        .build()))
                .build();
    }

}
