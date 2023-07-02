package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.ProvaQuestao;
import br.com.uniacademia.enade.model.Questao;

@Repository
public class ProvaQuestaoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public ProvaQuestao persistirProvaQuestao(ProvaQuestao provaQuestao){
		manager.persist(provaQuestao);
		return provaQuestao;
	}
	
	@Transactional
	public boolean excluirProvaQuestao(ProvaQuestao questao){
		manager.remove(questao);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Questao> buscarQuestoesIdProva(Long id) throws NoResultException{
		String sql = "SELECT q.* FROM prova_questao pq JOIN questao q ON q.id_questao = pq.id_questao WHERE pq.id_prova = :id";
		return manager.createNativeQuery(sql, Questao.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getResultList();
	}
	
	public ProvaQuestao buscarQuestoesProva(Long idProva, Long idQuestao) throws NoResultException{
		String sql = "SELECT * FROM prova_questao pq WHERE id_prova = :idProva AND id_questao = :idQuestao";
		return (ProvaQuestao) manager.createNativeQuery(sql, ProvaQuestao.class)
									.setParameter("idProva", new TypedParameterValue(StandardBasicTypes.LONG, idProva))
									.setParameter("idQuestao", new TypedParameterValue(StandardBasicTypes.LONG, idQuestao))
									.getSingleResult();
	}
}
