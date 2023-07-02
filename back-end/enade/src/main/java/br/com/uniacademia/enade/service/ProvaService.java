package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.ProvaDao;
import br.com.uniacademia.enade.model.Prova;
import br.com.uniacademia.enade.model.ProvaAluno;
import br.com.uniacademia.enade.model.ProvaQuestao;
import br.com.uniacademia.enade.model.Resultado;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ProvaResultadoAlunoTO;
import br.com.uniacademia.enade.to.ProvaTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class ProvaService {
	
	@Autowired
	ProvaDao provaDao;
	
	@Autowired
	ProvaQuestaoService provaQuestaoService;
	
	@Autowired
	ProvaAlunoService provaAlunoService;
	
	public Prova cadastrarProva(Prova prova) throws CustomExceptionTO, Exception {
		if(prova.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novas provas o ID deve ser nulo"));
			throw ce;
		}
		
		prova = provaDao.persistirProva(prova);
		prova = provaQuestaoService.cadastrarQuestoesProva(prova);
		
		return prova;
	}
	
	public Prova atualizarProva(Prova prova) throws CustomExceptionTO{
		
		try {
			prova = Util.mesclarClasse(this.buscarProvaId(prova.getId(),2),prova);
			prova = provaQuestaoService.cadastrarQuestoesProva(prova);
			return provaDao.atualizarProva(prova);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há prova com o id " + prova.getId() + " informado."));
			throw ce;
		}
	}
	
	public Prova buscarProvaId(Long id, Integer resposta) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			Prova prova = provaDao.buscarProvaId(id);
			
			if(resposta == 0) {
				prova.setQuestoes(provaQuestaoService.buscarQuestoesIdProvaSemResposta(id));
			}else if (resposta == 1){
				prova.setQuestoes(provaQuestaoService.buscarQuestoesIdProva(id));
			}
			
			return prova;
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há prova com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public Prova buscarProvaAplicacao(Long id) throws CustomExceptionTO{
		Prova prova = buscarProvaId(id,0);
		
		//provaQuestaoService.validarQuestoesProva(prova);
		
		return prova;
	}
	
	public List<Prova> buscarProva(ProvaTO provaTO) throws CustomExceptionTO{
		try {
			return provaDao.buscarProva(provaTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}

	public boolean excluirProva(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de provas o ID deve ser informado"));
			throw ce;
		}
		
		Prova usuario = this.buscarProvaId(id,2);
		
		return provaDao.excluirProva(usuario);
	}
	
	public Resultado buscarPontuacaoProvaAluno(Long idProva, Long idUsuario) throws CustomExceptionTO, Exception {
		return provaAlunoService.buscarPontuacaoProvaAluno(idProva, idUsuario);
	}
	
	public Prova persistirProvaQuestao(ProvaQuestao provaQuestao) throws CustomExceptionTO {
		provaQuestaoService.persistirProvaQuestao(provaQuestao);
		
		return this.buscarProvaId(provaQuestao.getId().getIdProva(),1);
	}
	
	public void removerProvaQuestao(ProvaQuestao provaQuestao) throws CustomExceptionTO {
		provaQuestaoService.removerProvaQuestao(provaQuestao);
	}

	public List<ProvaAluno> inserirRespostasAlunoProva(ProvaResultadoAlunoTO respostas) throws CustomExceptionTO {
		return provaAlunoService.inserirRespostasAlunoProva(respostas);
		
	}

	
}
