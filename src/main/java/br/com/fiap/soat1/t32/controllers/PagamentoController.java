package br.com.fiap.soat1.t32.controllers;

import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import br.com.fiap.soat1.t32.models.presenters.RespostaErro;
import br.com.fiap.soat1.t32.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Checkout", description = "API de Pagamentos")
@RequestMapping(value = "/v1/pagamentos",
		consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
		produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class PagamentoController {

	private final PagamentoService pagamentoService;

	@Operation(description = "Consultar pagamento por Id")
	@GetMapping(path = "/{idPagamento}",
			consumes = {ALL_VALUE},
			produces = {APPLICATION_JSON_VALUE, ALL_VALUE})
	@ApiResponse(responseCode = "200", description = "Dados do pagamento")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	public ResponseEntity<PagamentoDb> consultarPagamentoPorId(@PathVariable Long idPagamento) {
		Optional<PagamentoDb> pagamentoDb = pagamentoService.buscarPorId(idPagamento);
		if(pagamentoDb.isEmpty())
			return ResponseEntity.notFound()
					.build();
		return ResponseEntity.ok(pagamentoDb.get());
	}

}
