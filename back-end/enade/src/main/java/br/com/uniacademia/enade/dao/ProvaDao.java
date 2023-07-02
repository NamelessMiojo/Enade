package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.Prova;
import br.com.uniacademia.enade.to.ProvaTO;

@Repository
public class ProvaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Prova persistirProva(Prova prova){
		manager.persist(prova);
		return prova;
	}
	
	@Transactional
	public Prova atualizarProva(Prova prova){
		manager.merge(prova);
		return prova;
	}
	
	@Transactional
	public boolean excluirProva(Prova prova) {
		manager.remove(prova);
		return true;
	}
	
	public Prova buscarProvaId(Long id) throws NoResultException{
		String sql = "SELECT * FROM prova WHERE id_prova = :id";
		return (Prova) manager.createNativeQuery(sql, Prova.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Prova> buscarProva(ProvaTO prova){
		String sql = "SELECT * \r\n"
				+ "FROM prova \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_prova = :id OR :id IS NULL) \r\n"
				+ "AND (data_prova = :dataProva OR :dataProva IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, Prova.class)
				.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, prova.getId()))
				.setParameter("dataProva", new TypedParameterValue(StandardBasicTypes.DATE, prova.getDataProva()))
				.getResultList();
	}

	
}
