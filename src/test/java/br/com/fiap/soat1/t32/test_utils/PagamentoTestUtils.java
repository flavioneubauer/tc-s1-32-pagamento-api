package br.com.fiap.soat1.t32.test_utils;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.models.parameters.PagamentoPedidoRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PagamentoTestUtils {

    public static PagamentoPedidoRequest getPagamentoPedidoRequest_Aprovado() {
        return PagamentoPedidoRequest.builder()
                .pagamentoAprovado(Boolean.TRUE)
                .build();
    }

    public static PagamentoPedidoRequest getPagamentoPedidoRequest_Recusado() {
        return PagamentoPedidoRequest.builder()
                .pagamentoAprovado(Boolean.FALSE)
                .build();
    }

    public static PagamentoDb getPagamentoDb_Pendente() {
        return PagamentoDb.builder()
                .id(50L)
                .horaCriacao(LocalDateTime.now())
                .horaAlteracao(LocalDateTime.now())
                .statusPagamento(StatusPagamentoPedido.PENDENTE)
                .build();
    }

    public static PagamentoDb getPagamentoDb_Aprovado() {
        return PagamentoDb.builder()
                .id(50L)
                .horaCriacao(LocalDateTime.now())
                .horaAlteracao(LocalDateTime.now())
                .statusPagamento(StatusPagamentoPedido.APROVADO)
                .build();
    }

}
