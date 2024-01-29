package br.com.fiap.soat1.t32.utils.mappers;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static br.com.fiap.soat1.t32.enums.StatusPagamentoPedido.PENDENTE;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PagamentoMapper {

    public static PagamentoDb map(Long idPedido) {
        return PagamentoDb.builder()
                .idPedido(idPedido)
                .statusPagamento(PENDENTE)
                .horaCriacao(LocalDateTime.now())
                .build();
    }

    public static PagamentoDb map(PagamentoDb pagamentoDb, StatusPagamentoPedido statusPagamentoPedido) {
        return PagamentoDb.builder()
                .id(pagamentoDb.getId())
                .idPedido(pagamentoDb.getIdPedido())
                .statusPagamento(statusPagamentoPedido)
                .horaCriacao(pagamentoDb.getHoraCriacao())
                .horaAlteracao(LocalDateTime.now())
                .build();
    }

}
