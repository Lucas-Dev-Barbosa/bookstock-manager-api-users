package br.com.bookstock.domain.dto;

import java.util.List;

import br.com.bookstock.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioPageDto {
	
	private List<Usuario> usuario;
	
	private Integer numPagina;
	
	private Integer totalPaginas;
	
}
