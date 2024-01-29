package br.com.fiap.soat1.t32.test_utils;

import br.com.fiap.soat1.t32.models.presenters.clients.PedidoClientResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoTestUtils {

    public static PedidoClientResponse getPedidoClientResponse_IdOk() {
        return PedidoClientResponse.builder()
                .id(10L)
                .build();
    }

    public static PedidoClientResponse getPedidoClientResponse_IdNulo() {
        return PedidoClientResponse.builder()
                .build();
    }

}
