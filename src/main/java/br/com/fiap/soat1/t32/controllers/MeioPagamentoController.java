package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.exceptions.NotFoundException;
import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MeioPagamentoController {

	private final PagamentoService pagamentoService;

	@GetMapping("/meiopagamento")
	public String getMeioPagamento(@RequestParam(name="pagamento", required=true) Long pagamentoId, Model model) {

		Optional<PagamentoDb> pagamentoDbOptional = pagamentoService.buscarPorId(pagamentoId);
		if(pagamentoDbOptional.isEmpty()){
			throw new NotFoundException("Pagamento não encontrado");
		}

		PagamentoDb pagamentoDb = pagamentoDbOptional.get();
		model.addAttribute("pedido", pagamentoDb.getIdPedido());
		model.addAttribute("pagamento", pagamentoDb.getId());
		return "meiopagamento";
	}
}
