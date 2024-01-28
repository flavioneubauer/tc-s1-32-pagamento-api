package br.com.fiap.soat1.t32.models.parameters.clients;

import br.com.fiap.soat1.t32.enums.CategoriaProduto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoClientRequest {

    private Long id;
    private BigDecimal preco;
    private CategoriaProduto categoria;
    private String descricao;
}