package br.com.uniacademia.enade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.enade.model.ProvaAluno;

@Repository
public class ProvaAlunoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public ProvaAluno persistirProvaAluno(ProvaAluno provaAluno){
		manager.persist(provaAluno);
		return provaAluno;
	}
	
	@Transactional
	public boolean excluirProvaAluno(ProvaAluno provaAluno){
		manager.remove(provaAluno);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProvaAluno> buscarRespostasProvaAluno(Long idProva, Long idUsuario) throws NoResultException{
		String sql = "SELECT pa.* FROM prova_aluno pa WHERE pa.id_usuario = :idUsuario AND pa.id_prova = :idProva";
		return manager.createNativeQuery(sql, ProvaAluno.class)
									.setParameter("idProva", new TypedParameterValue(StandardBasicTypes.LONG, idProva))
									.setParameter("idUsuario", new TypedParameterValue(StandardBasicTypes.LONG, idUsuario))
									.getResultList();
	}
	
}
