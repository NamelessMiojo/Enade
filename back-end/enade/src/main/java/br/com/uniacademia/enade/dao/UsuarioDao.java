package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.Usuario;
import br.com.uniacademia.enade.to.UsuarioTO;

@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Usuario persistirUsuario(Usuario usuario){
		manager.persist(usuario);
		return usuario;
	}
	
	@Transactional
	public Usuario atualizarUsuario(Usuario usuario){
		manager.merge(usuario);
		return usuario;
	}
	
	@Transactional
	public boolean excluirUsuario(Usuario usuario){
		manager.remove(usuario);
		return true;
	}
	
	public Usuario buscarUsuarioId(Long id) throws NoResultException{
		String sql = "SELECT * FROM usuario WHERE id_usuario = :id";
		return (Usuario) manager.createNativeQuery(sql, Usuario.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuario(UsuarioTO usuario){
		String sql = "SELECT * \r\n"
				+ "FROM usuario \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_usuario = :id OR :id IS NULL) \r\n"
				+ "AND (UPPER(nome) like CONCAT('%',UPPER(:nome),'%') OR :nome IS NULL) \r\n"
				+ "AND (UPPER(email) like CONCAT('%',UPPER(:email),'%') OR :email IS NULL) \r\n"
				+ "AND (id_tipo_usuario = :tipoUsuario OR :tipoUsuario IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, Usuario.class)
				.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, usuario.getId()))
				.setParameter("nome", new TypedParameterValue(StandardBasicTypes.STRING, usuario.getNome()))
				.setParameter("email", new TypedParameterValue(StandardBasicTypes.STRING, usuario.getEmail()))
				.setParameter("tipoUsuario", new TypedParameterValue(StandardBasicTypes.LONG, usuario.getTipoUsuario()))
				.getResultList();
	}
	
	public Usuario buscarUsuarioAutenticacao(String email, String senha){
		String sql = "SELECT * \r\n"
				+ "FROM usuario \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (email = :email) \r\n"
				+ "AND (senha = :senha) \r\n";
		
		return  (Usuario) manager.createNativeQuery(sql, Usuario.class)
				.setParameter("email", new TypedParameterValue(StandardBasicTypes.STRING, email))
				.setParameter("senha", new TypedParameterValue(StandardBasicTypes.STRING, senha))
				.getSingleResult();
	}
}
