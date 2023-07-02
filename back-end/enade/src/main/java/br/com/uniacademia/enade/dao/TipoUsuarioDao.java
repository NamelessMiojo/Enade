package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.TipoUsuario;
import br.com.uniacademia.enade.to.TipoUsuarioTO;

@Repository
public class TipoUsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public TipoUsuario persistirTipoUsuario(TipoUsuario tipoUsuario){
		manager.persist(tipoUsuario);
		return tipoUsuario;
	}
	
	@Transactional
	public TipoUsuario atualizarTipoUsuario(TipoUsuario tipoUsuario){
		manager.merge(tipoUsuario);
		return tipoUsuario;
	}
	
	@Transactional
	public boolean excluirTipoUsuario(TipoUsuario tipoUsuario) {
		manager.remove(tipoUsuario);
		return true;
	}
	
	public TipoUsuario buscarTipoUsuarioId(Long id) throws NoResultException{
		String sql = "SELECT * FROM tipo_usuario WHERE id_tipo_usuario = :id";
		return (TipoUsuario) manager.createNativeQuery(sql, TipoUsuario.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoUsuario> buscarTipoUsuario(TipoUsuarioTO tipoUsuario){
		String sql = "SELECT * \r\n"
				+ "FROM tipo_usuario \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_tipo_usuario = :id OR :id IS NULL) \r\n"
				+ "AND (UPPER(nome_tipo_usuario) like CONCAT('%',UPPER(:nome),'%') OR :nome IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, TipoUsuario.class)
				.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, tipoUsuario.getId()))
				.setParameter("nome", new TypedParameterValue(StandardBasicTypes.STRING, tipoUsuario.getNome()))
				.getResultList();
	}

	
}
