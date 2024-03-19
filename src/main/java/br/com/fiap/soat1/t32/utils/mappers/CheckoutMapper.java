package br.com.fiap.soat1.t32.utils.mappers;

import br.com.fiap.soat1.t32.models.presenters.CheckoutResponse;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckoutMapper {

    public static CheckoutResponse map(Long idPagamento) {
        return CheckoutResponse.builder()
                .idPagamento(idPagamento)
                .build();
    }

}
