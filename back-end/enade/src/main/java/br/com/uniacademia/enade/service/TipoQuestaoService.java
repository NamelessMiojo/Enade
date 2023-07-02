package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.TipoQuestaoDao;
import br.com.uniacademia.enade.model.TipoQuestao;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.TipoQuestaoTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class TipoQuestaoService {
	
	@Autowired
	TipoQuestaoDao tipoQuestaoDao;
	
	public TipoQuestao cadastrarTipoQuestao(TipoQuestao tipoQuestao) throws CustomExceptionTO, Exception {
		if(tipoQuestao.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novos tipos de questões o ID deve ser nulo"));
			throw ce;
		}
		
		return tipoQuestaoDao.persistirTipoQuestao(tipoQuestao);
	}
	
	public TipoQuestao atualizarTipoQuestao(TipoQuestao tipoQuestao) throws CustomExceptionTO{
		
		try {
			tipoQuestao = Util.mesclarClasse(this.buscarTipoQuestaoId(tipoQuestao.getId()),tipoQuestao);
			return tipoQuestaoDao.atualizarTipoQuestao(tipoQuestao);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há tipo de questão com o id " + tipoQuestao.getId() + " informado."));
			throw ce;
		}
	}
	
	public TipoQuestao buscarTipoQuestaoId(Long id) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			return tipoQuestaoDao.buscarTipoQuestaoId(id);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há tipo de questão com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public List<TipoQuestao> buscarTipoQuestao(TipoQuestaoTO tipoQuestaoTO) throws CustomExceptionTO{
		try {
			return tipoQuestaoDao.buscarTipoQuestao(tipoQuestaoTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}

	public boolean excluirTipoQuestao(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de tipo de questão o ID deve ser informado"));
			throw ce;
		}
		
		TipoQuestao usuario = this.buscarTipoQuestaoId(id);
		
		return tipoQuestaoDao.excluirTipoQuestao(usuario);
	}
}
