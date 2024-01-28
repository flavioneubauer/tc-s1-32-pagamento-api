package br.com.fiap.soat1.t32.models.parameters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoPedidoRequest {

    private Long idPedido;
    private Boolean pagamentoAprovado;

}