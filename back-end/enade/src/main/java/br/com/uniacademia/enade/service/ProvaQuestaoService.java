package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.ProvaQuestaoDao;
import br.com.uniacademia.enade.model.Prova;
import br.com.uniacademia.enade.model.ProvaQuestao;
import br.com.uniacademia.enade.model.Questao;
import br.com.uniacademia.enade.to.CustomExceptionTO;

@Service
public class ProvaQuestaoService {
	
	@Lazy
	@Autowired
	ProvaService provaService;
	
	@Lazy
	@Autowired
	QuestaoService questaoService;
	
	@Autowired
	ProvaQuestaoDao provaQuestaoDao;

	public void validarQuestoesProva(Prova prova) throws CustomExceptionTO {
		
		if(prova.getQuestoes().size() < 36) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a aplicação da prova é necessário que a prova tenha 36 questões. " + prova.getQuestoes().size() + " informadas."));
			throw ce;
		}
		
		Set<Questao> setQuestoes = new HashSet<>(prova.getQuestoes());
		
		if(setQuestoes.size() != prova.getQuestoes().size()) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Questões enviadas em duplicidade."));
			throw ce;
		}
	}
	
	public Prova cadastrarQuestoesProva(Prova prova) throws CustomExceptionTO {
		
		for(Questao questao: prova.getQuestoes()) {
			ProvaQuestao provaQuestao = new ProvaQuestao(prova,questao);
			
			this.persistirProvaQuestao(provaQuestao);
		}
		
		prova.setQuestoes(this.buscarQuestoesIdProva(prova.getId()));
		
		return prova;
		
	}
	
	public ProvaQuestao persistirProvaQuestao(ProvaQuestao provaQuestao) throws CustomExceptionTO {
		try {
			return provaQuestaoDao.persistirProvaQuestao(provaQuestao);
		} catch (DataIntegrityViolationException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Questão "+ provaQuestao.getId().getIdQuestao() + " Já adicionada na prova "+ provaQuestao.getId().getIdProva() + "."));
			throw ce;
		}
	}
	
	public boolean removerProvaQuestao(ProvaQuestao provaQuestao) throws CustomExceptionTO {
		try {
			return provaQuestaoDao.excluirProvaQuestao(provaQuestaoDao.buscarQuestoesProva(provaQuestao.getId().getIdProva(),provaQuestao.getId().getIdQuestao()));
		} catch (DataIntegrityViolationException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Questão "+ provaQuestao.getId().getIdQuestao() + " não encontrada na prova "+ provaQuestao.getId().getIdProva() + "."));
			throw ce;
		}
	}
	
	public ProvaQuestao buscarQuestoesProva(Long idProva, Long idQuestao){
		return provaQuestaoDao.buscarQuestoesProva(idProva,idQuestao);
	}
	
	public List<Questao> buscarQuestoesIdProva(Long id) throws CustomExceptionTO {
		try {
			return provaQuestaoDao.buscarQuestoesIdProva(id);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Questao> buscarQuestoesIdProvaSemResposta(Long id) throws CustomExceptionTO {
		try {
			List<Questao> questoes = provaQuestaoDao.buscarQuestoesIdProva(id);
			questoes.forEach(x -> x.setQuestaoCorreta(null));
			return questoes;
		} catch (NoResultException e) {
			return null;
		}
	}
}
