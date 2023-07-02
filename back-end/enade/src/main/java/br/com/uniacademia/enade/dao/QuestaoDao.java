package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.Questao;
import br.com.uniacademia.enade.to.QuestaoTO;

@Repository
public class QuestaoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Questao persistirQuestao(Questao questao){
		manager.persist(questao);
		return questao;
	}
	
	@Transactional
	public Questao atualizarQuestao(Questao questao){
		manager.merge(questao);
		return questao;
	}
	
	@Transactional
	public boolean excluirQuestao(Questao questao){
		manager.remove(questao);
		return true;
	}
	
	public Questao buscarQuestaoId(Long id) throws NoResultException{
		String sql = "SELECT * FROM questao WHERE id_questao = :id";
		return (Questao) manager.createNativeQuery(sql, Questao.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Questao> buscarQuestao(QuestaoTO questao){
		String sql = "SELECT * \r\n"
				+ "FROM questao \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_questao = :id OR :id IS NULL) \r\n"
				+ "AND (UPPER(descricao_questao) like CONCAT('%',UPPER(:descricao),'%') OR :descricao IS NULL) \r\n"
				+ "AND (UPPER(estado_questao) like CONCAT('%',UPPER(:estado),'%') OR :estado IS NULL) \r\n"
				+ "AND (id_tipo_questao = :tipoQuestao OR :tipoQuestao IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, Questao.class)
				.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, questao.getId()))
				.setParameter("descricao", new TypedParameterValue(StandardBasicTypes.STRING, questao.getDescricao()))
				.setParameter("estado", new TypedParameterValue(StandardBasicTypes.STRING, questao.getEstado()))
				.setParameter("tipoQuestao", new TypedParameterValue(StandardBasicTypes.LONG, questao.getTipoQuestao()))
				.getResultList();
	}
}
