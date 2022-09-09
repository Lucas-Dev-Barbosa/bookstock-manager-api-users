package br.com.bookstock.resource;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;

import br.com.bookstock.domain.Usuario;
import br.com.bookstock.domain.dto.UsuarioPageDto;
import br.com.bookstock.domain.dto.UsuarioUpdateDto;
import br.com.bookstock.service.UsuarioService;

@Path("/")
@Tag(name = "Usuário Resource")
public class UsuarioResource {
	
	@Inject
	JsonWebToken jwt;

	@Inject
	UsuarioService usuarioService;
	
	@GET
	@Tag(name = "listarUsuarios", description = "Retorna uma lista com todos os usuarios da base keycloak")
	@RolesAllowed("ROLE_ADMIN")
	public List<Usuario> listarUsuarios() {
		return usuarioService.getListaUsuarios();
	}

	@GET
	@Path("/paginacao")
	@Tag(name = "listarUsuariosPorPaginacao", description = "Retorna uma lista com os usuarios da base keycloak a partir de filtros especificados")
	@RolesAllowed("ROLE_ADMIN")
	public UsuarioPageDto listarUsuariosPorPaginacao(
			@RestQuery(value = "pagina") Optional<Integer> pagina,
			@RestQuery(value = "direcao") Optional<String> direcao,
			@RestQuery(value = "filtro") Optional<String> filtro) {
		
		Integer p = pagina.filter((page) -> (page != null && page > 0)).orElse(1);
		String dir = direcao.orElse("Ascending");
		String fil = filtro.orElse(null);

		UsuarioPageDto usuarios = usuarioService.buscarUsuariosPorPaginacao(p, dir, fil);
		return usuarios;
	}
	
	@GET
	@Path("/user")
	@Tag(name = "obterUsuarioAutenticado", description = "Retorna as informações do usuário autenticado do token da base keycloak")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public Usuario obterUsuarioAutenticado() {
		String username = jwt.getName();
		Usuario usuario = usuarioService.getUsuarioByUserName(username);
		return usuario;
	}

	@PUT
	@ResponseStatus(value = StatusCode.OK)
	@Tag(name = "editarUsuario", description = "Possibilidade de alterar o nome e o sobrenome do usuario na base do keycloak.")
	@APIResponse(responseCode = "200")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public void editarUsuario(@Valid UsuarioUpdateDto usuario) {
		usuarioService.editarUsuario(usuario);
	}
	
}
