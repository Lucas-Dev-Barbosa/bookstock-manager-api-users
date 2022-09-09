package br.com.bookstock.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.bookstock.domain.Usuario;
import br.com.bookstock.domain.dto.UsuarioPageDto;
import br.com.bookstock.domain.dto.UsuarioUpdateDto;
import br.com.bookstock.repository.UsuarioRepository;
import lombok.extern.java.Log;

@Log
@Service
public class UsuarioService {

	@Inject
	UsuarioRepository repository;

	public List<Usuario> getListaUsuarios() {
		return repository.getListaUsuarios();
	}

	public UsuarioPageDto buscarUsuariosPorPaginacao(int paginaAtual, String ordem, String busca) {
		if (busca == null || busca.isEmpty())
			return repository.getUsuariosPorPaginacaoSemBusca(paginaAtual, ordem);

		return repository.getUsuariosPorPaginacao(paginaAtual, ordem, busca);
	}

	@Transactional
	public void editarUsuario(UsuarioUpdateDto usuario) {
		log.info("Editando usuario [" + usuario.getNome() + "]");
		repository.editarUsuario(usuario);
	}

	public Usuario getUsuarioByUserName(String username) {
		return repository.getUsuarioByUserName(username);
	}

}
