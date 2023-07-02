package br.com.uniacademia.enade.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.ProvaAlunoDao;
import br.com.uniacademia.enade.model.ProvaAluno;
import br.com.uniacademia.enade.model.Resultado;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ProvaResultadoAlunoTO;
import br.com.uniacademia.enade.to.QuestaoResultadoTO;
import br.com.uniacademia.enade.to.ResultadoTO;

@Service
public class ProvaAlunoService {
	
	@Autowired
	ProvaAlunoDao provaAlunoDao;
	
	@Autowired
	ResultadoService resultadoService;
	
	@Lazy
	@Autowired
	ProvaQuestaoService provaQuestaoService;

	public List<ProvaAluno> inserirRespostasAlunoProva(ProvaResultadoAlunoTO respostas) throws CustomExceptionTO {
		List<ProvaAluno> respostasRegistradas = new ArrayList();
		
		for(QuestaoResultadoTO questao: respostas.getQuestoes()) {
			ProvaAluno q = new ProvaAluno(respostas,questao);
			
			try {
				if(provaQuestaoService.buscarQuestoesProva(q.getId().getIdProva(), q.getId().getIdQuestao()) == null) {
					throw new RuntimeException();
				};
				
				respostasRegistradas.add(provaAlunoDao.persistirProvaAluno(q));
			} catch (Exception e) {
				CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
				ce.setErrors(Arrays.asList("Verifique os parâmetros enviados."));
				throw ce;
			}
			
		}
		
		return respostasRegistradas;
	}
	
	public List<ProvaAluno> buscarRespostasProvaAluno(Long idProva, Long idUsuario) throws CustomExceptionTO, Exception {
		return provaAlunoDao.buscarRespostasProvaAluno(idProva,idUsuario);
	}
	
	public Resultado buscarPontuacaoProvaAluno(Long idProva, Long idUsuario) throws CustomExceptionTO, Exception {
		ResultadoTO resultadoTO = new ResultadoTO();
		resultadoTO.setIdProva(idProva);
		resultadoTO.setIdUsuario(idUsuario);
		
		List<Resultado> resultadosExistentes = resultadoService.buscarResultado(resultadoTO);
		
		if(resultadosExistentes != null && resultadosExistentes.size() > 0) {
			return resultadosExistentes.get(0);
		}
		
		Resultado resultado = resultadoService.validarPontuacao(resultadoTO);
		
		if(resultado == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Verifique os parâmetros enviados."));
			throw ce;
		}
		
		resultado = resultadoService.cadastrarResultado(resultado);
		
		return resultado;
	}

}
