package br.com.fiap.soat1.t32.models.parameters.clients;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteClientRequest {

	private UUID id;
	private String nome;
	private String cpf;
	private String email;
	
}
