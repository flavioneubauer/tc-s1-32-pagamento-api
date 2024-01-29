package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.clients.PedidoClient;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.fiap.soat1.t32.utils.mappers.PedidoMapper.map;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoClient pedidoClient;

    public Long cadastrar(final CheckoutRequest checkout) {
        try {
            final var response = this.pedidoClient.cadastrar(map(checkout));

            if (isNull(response) || isNull(response.getId())) {
                throw new IntegrationException("Não foi possível incluir pedido.");
            }

            return response.getId();
        } catch(FeignException fe) {
            throw new IntegrationException("Houve um erro na inclusão do pedido.");
        }
    }

    public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido){
        try {
            pedidoClient.alterarStatusPreparacaoPedido(id, statusPreparacaoPedido);
        } catch(FeignException fe) {
            throw new IntegrationException("Houve um erro na alteração de status de preparação do pedido.");
        }
    }

}
