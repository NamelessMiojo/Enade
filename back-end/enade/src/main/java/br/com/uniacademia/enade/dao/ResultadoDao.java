package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.Resultado;
import br.com.uniacademia.enade.to.ResultadoTO;

@Repository
public class ResultadoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Resultado persistirResultado(Resultado resultado){
		manager.persist(resultado);
		return resultado;
	}
	
	@Transactional
	public Resultado atualizarResultado(Resultado resultado){
		manager.merge(resultado);
		return resultado;
	}
	
	@Transactional
	public boolean excluirResultado(Resultado resultado) {
		manager.remove(resultado);
		return true;
	}
	
	public Resultado buscarResultadoId(Long id) throws NoResultException{
		String sql = "SELECT * FROM resultado WHERE id_resultado = :id";
		return (Resultado) manager.createNativeQuery(sql, Resultado.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resultado> buscarResultado(ResultadoTO resultado){
		String sql = "SELECT * \r\n"
				+ "FROM resultado \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_resultado = :idResultado OR :idResultado IS NULL) \r\n"
				+ "AND (id_prova = :idProva OR :idProva IS NULL) \r\n"
				+ "AND (id_usuario = :idUsuario OR :idUsuario IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, Resultado.class)
				.setParameter("idResultado", new TypedParameterValue(StandardBasicTypes.LONG, resultado.getId()))
				.setParameter("idProva", new TypedParameterValue(StandardBasicTypes.LONG, resultado.getIdProva()))
				.setParameter("idUsuario", new TypedParameterValue(StandardBasicTypes.LONG, resultado.getIdUsuario()))
				.getResultList();
	}

	
}
