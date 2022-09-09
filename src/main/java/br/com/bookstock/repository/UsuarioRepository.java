package br.com.bookstock.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.bookstock.domain.Usuario;
import br.com.bookstock.domain.dto.UsuarioPageDto;
import br.com.bookstock.domain.dto.UsuarioUpdateDto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;

@Repository
public class UsuarioRepository implements PanacheRepository<Usuario> {
	public List<Usuario> getListaUsuarios() {
		return listAll();
	}

	public UsuarioPageDto getUsuariosPorPaginacao(int paginaAtual, String ordem, String busca) {
		Map<String, Object> params = Parameters.with("nome", "%" + busca + "%").and("sobrenome", "%" + busca + "%")
				.and("email", "%" + busca + "%").and("username", "%" + busca + "%").map();

		PanacheQuery<Usuario> usuarios = find(
				"nome like :nome OR sobrenome like :sobrenome OR email like :email OR username like :username",
				Sort.by("nome", Direction.valueOf(ordem)), params);
		
		UsuarioPageDto usuarioDto = getUsuarioPageDto(usuarios, paginaAtual);
		return usuarioDto;
	}

	public UsuarioPageDto getUsuariosPorPaginacaoSemBusca(int paginaAtual, String ordem) {
		PanacheQuery<Usuario> usuarios = findAll(Sort.by("nome", Direction.valueOf(ordem)));
		
		UsuarioPageDto usuarioDto = getUsuarioPageDto(usuarios, paginaAtual);
		return usuarioDto;
	}
	
	public Usuario getUsuarioByUserName(String username) {
		Map<String, Object> params = Parameters.with("username", username).map();
		return find("username = :username", params).firstResult();
	}

	public void editarUsuario(UsuarioUpdateDto usuario) {
		Map<String, Object> params = Parameters.with("nome", usuario.getNome()).and("sobrenome", usuario.getSobrenome())
				.and("id", usuario.getId()).map();
		update("nome = :nome, sobrenome = :sobrenome where id = :id", params);
	}
	
	private UsuarioPageDto getUsuarioPageDto(PanacheQuery<Usuario> panacheQuery, int paginaAtual) {
		PanacheQuery<Usuario> page = panacheQuery.page(paginaAtual - 1, 12);
		List<Usuario> usuarios = page.list();
		
		UsuarioPageDto usuarioDto = new UsuarioPageDto(usuarios, paginaAtual, page.pageCount());
		return usuarioDto;
	}

}
