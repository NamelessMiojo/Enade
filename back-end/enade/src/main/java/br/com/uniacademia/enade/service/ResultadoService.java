package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.ResultadoDao;
import br.com.uniacademia.enade.model.Prova;
import br.com.uniacademia.enade.model.ProvaAluno;
import br.com.uniacademia.enade.model.Questao;
import br.com.uniacademia.enade.model.Resultado;
import br.com.uniacademia.enade.model.Usuario;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ResultadoTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class ResultadoService {
	
	@Autowired
	ResultadoDao resultadoDao;
	
	@Lazy
	@Autowired
	ProvaAlunoService provaAlunoService;
	
	@Lazy
	@Autowired
	QuestaoService questaoService;
	
	@Lazy
	@Autowired
	UsuarioService usuarioService;
	
	
	public Resultado cadastrarResultado(Resultado resultado) throws CustomExceptionTO, Exception {
		if(resultado.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novas resultados o ID deve ser nulo"));
			throw ce;
		}
		
		resultado = resultadoDao.persistirResultado(resultado);
		
		return resultado;
	}
	
	public Resultado atualizarResultado(Resultado resultado) throws CustomExceptionTO{
		
		try {
			resultado = Util.mesclarClasse(this.buscarResultadoId(resultado.getId(),2),resultado);
			return resultadoDao.atualizarResultado(resultado);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há resultado com o id " + resultado.getId() + " informado."));
			throw ce;
		}
	}
	
	public Resultado buscarResultadoId(Long id, Integer resposta) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			Resultado resultado = resultadoDao.buscarResultadoId(id);
			return resultado;
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há resultado com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public List<Resultado> buscarResultado(ResultadoTO resultadoTO) throws CustomExceptionTO{
		try {
			return resultadoDao.buscarResultado(resultadoTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}

	public boolean excluirResultado(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de resultados o ID deve ser informado"));
			throw ce;
		}
		
		Resultado usuario = this.buscarResultadoId(id,2);
		
		return resultadoDao.excluirResultado(usuario);
	}
	
	public Resultado validarPontuacao(ResultadoTO resultadoTO) throws CustomExceptionTO, Exception {
		
		List<ProvaAluno> respostas = provaAlunoService.buscarRespostasProvaAluno(resultadoTO.getIdProva(),resultadoTO.getIdUsuario());
		Integer acertos = 0;
		Resultado resultado = null;
		
		if(respostas.size() > 0) {
			resultado = new Resultado();
			for(ProvaAluno resposta	: respostas) {
				Questao questao = questaoService.buscarQuestaoId(resposta.getId().getIdQuestao());
				
				if(questao.getQuestaoCorreta().toUpperCase().equals(resposta.getAlternativa())) {
					acertos += 1;
				}
			}
			
			resultado.setAcertos(acertos);
			resultado.setProva(new Prova(resultadoTO.getIdProva()));
			resultado.setUsuario(usuarioService.buscarUsuarioId(resultadoTO.getIdUsuario()));
			resultado.setNota(this.calcularNota(acertos));
		}
		return resultado;
	}
	
	private Float calcularNota(Integer acertos) {
		Float nota = 0F;
		Float valorQuestao = 0.28F;
		
		if(acertos <= 19) {
			nota = 0F;
		}else {
			nota = acertos * valorQuestao;
		}
		
		return nota;
	}
}
