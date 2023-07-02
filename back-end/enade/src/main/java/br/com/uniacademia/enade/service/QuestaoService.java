package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.QuestaoDao;
import br.com.uniacademia.enade.model.Questao;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.QuestaoTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class QuestaoService {
	
	@Autowired
	QuestaoDao questaoDao;
	
	public Questao cadastrarQuestao(Questao questao) throws CustomExceptionTO, Exception {
		if(questao.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novas questões o ID deve ser nulo"));
			throw ce;
		}
		
		try{
			return questaoDao.persistirQuestao(questao);
		}catch (DataIntegrityViolationException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Tipo da questão não encontrado"));
			throw ce;
		}
		
	}
	
	public boolean excluirQuestao(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de questões o ID deve ser informado"));
			throw ce;
		}
		
		Questao questao = this.buscarQuestaoId(id);
		
		return questaoDao.excluirQuestao(questao);
	}
	
	public Questao atualizarQuestao(Questao questao) throws CustomExceptionTO{
		
		try {
			questao = Util.mesclarClasse(this.buscarQuestaoId(questao.getId()),questao);
			return questaoDao.atualizarQuestao(questao);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há questões com o id " + questao.getId() + " informado."));
			throw ce;
		}
	}
	
	public Questao buscarQuestaoId(Long id) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			return questaoDao.buscarQuestaoId(id);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há questão com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public List<Questao> buscarQuestao(QuestaoTO questaoTO) throws CustomExceptionTO{
		try {
			return questaoDao.buscarQuestao(questaoTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}
}
