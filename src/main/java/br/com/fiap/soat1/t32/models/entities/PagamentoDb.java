package br.com.fiap.soat1.t32.models.entities;

import br.com.fiap.soat1.t32.enums.StatusPagamentoPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PAGAMENTO_PEDIDO")
public class PagamentoDb {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long idPedido;

    @Column(nullable = false)
    @Enumerated(STRING)
    private StatusPagamentoPedido statusPagamento;

    @Column(nullable = false)
    private LocalDateTime horaCriacao;

    private LocalDateTime horaAlteracao;
}
