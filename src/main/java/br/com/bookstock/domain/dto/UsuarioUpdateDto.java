package br.com.bookstock.domain.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDto {
	
	@NotBlank(message = "É necessário inserir o ID do usuario.")
	private String id;
	
	@NotBlank(message = "Digite o primeiro nome.")
	private String nome;
	
	@NotBlank(message = "Digite o sobrenome.")
	private String sobrenome;

}
