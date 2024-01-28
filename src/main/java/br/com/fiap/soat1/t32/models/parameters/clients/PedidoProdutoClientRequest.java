package br.com.fiap.soat1.t32.models.parameters.clients;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProdutoClientRequest {

    private ProdutoClientRequest produto;
    private Long quantidade;

}
