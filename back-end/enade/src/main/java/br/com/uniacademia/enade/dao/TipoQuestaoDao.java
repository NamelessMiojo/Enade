package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.TipoQuestao;
import br.com.uniacademia.enade.to.TipoQuestaoTO;

@Repository
public class TipoQuestaoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public TipoQuestao persistirTipoQuestao(TipoQuestao tipoQuestao){
		manager.persist(tipoQuestao);
		return tipoQuestao;
	}
	
	@Transactional
	public TipoQuestao atualizarTipoQuestao(TipoQuestao tipoQuestao){
		manager.merge(tipoQuestao);
		return tipoQuestao;
	}
	
	@Transactional
	public boolean excluirTipoQuestao(TipoQuestao tipoQuestao) {
		manager.remove(tipoQuestao);
		return true;
	}
	
	public TipoQuestao buscarTipoQuestaoId(Long id) throws NoResultException{
		String sql = "SELECT * FROM tipo_questao WHERE id_tipo_questao = :id";
		return (TipoQuestao) manager.createNativeQuery(sql, TipoQuestao.class)
									.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, id))
									.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoQuestao> buscarTipoQuestao(TipoQuestaoTO tipoQuestao){
		String sql = "SELECT * \r\n"
				+ "FROM tipo_questao \r\n"
				+ "WHERE 1 = 1 \r\n" 
				+ "AND (id_tipo_questao = :id OR :id IS NULL) \r\n"
				+ "AND (UPPER(nome_tipo_questao) like CONCAT('%',UPPER(:nome),'%') OR :nome IS NULL) \r\n";
		
		return  manager.createNativeQuery(sql, TipoQuestao.class)
				.setParameter("id", new TypedParameterValue(StandardBasicTypes.LONG, tipoQuestao.getId()))
				.setParameter("nome", new TypedParameterValue(StandardBasicTypes.STRING, tipoQuestao.getNome()))
				.getResultList();
	}

	
}
