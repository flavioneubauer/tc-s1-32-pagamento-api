package br.com.fiap.soat1.t32.services;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.models.parameters.CheckoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final PedidoService pedidoService;

    public Long realizarCheckout(CheckoutRequest checkout) {
        validaCheckout(checkout);

//        final var pedidoId = this.pedidoService.cadastrar(PedidoMapper.map(checkout));



        return 0L;
    }

    private void validaCheckout(CheckoutRequest checkout) {

        if(checkout == null) {
            throw new ValidationException("Informar dados para checkout do pedido.");
        } else if(checkout.getProdutos() == null || checkout.getProdutos().isEmpty()) {
            throw new ValidationException("Produtos selecionados para checkout do pedido não foram informados.");
        }

    }

}