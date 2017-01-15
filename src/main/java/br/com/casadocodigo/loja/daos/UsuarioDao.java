package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager
				.createQuery("select u from Usuario u where u.email = :email", 
						Usuario.class)
				.setParameter("email", email)
				.getResultList();
		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("usuário :" + email + "não encontrado");
		}
		return usuarios.get(0);
	}

	public void gravar(Usuario usuario) {
		
		System.out.println("usuarioDao método grava chamado!");
		
	    manager.persist(usuario);
	}
}
