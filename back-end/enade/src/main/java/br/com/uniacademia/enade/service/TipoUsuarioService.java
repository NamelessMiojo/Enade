package br.com.uniacademia.enade.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.TipoUsuarioDao;
import br.com.uniacademia.enade.model.TipoUsuario;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.TipoUsuarioTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class TipoUsuarioService {
	
	@Autowired
	TipoUsuarioDao tipoUsuarioDao;
	
	public TipoUsuario cadastrarTipoUsuario(TipoUsuario tipoUsuario) throws CustomExceptionTO, Exception {
		if(tipoUsuario.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novos tipos de usuarios o ID deve ser nulo"));
			throw ce;
		}
		
		return tipoUsuarioDao.persistirTipoUsuario(tipoUsuario);
	}
	
	public TipoUsuario atualizarTipoUsuario(TipoUsuario tipoUsuario) throws CustomExceptionTO{
		
		try {
			tipoUsuario = Util.mesclarClasse(this.buscarTipoUsuarioId(tipoUsuario.getId()),tipoUsuario);
			return tipoUsuarioDao.atualizarTipoUsuario(tipoUsuario);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há tipo de usuario com o id " + tipoUsuario.getId() + " informado."));
			throw ce;
		}
	}
	
	public TipoUsuario buscarTipoUsuarioId(Long id) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			return tipoUsuarioDao.buscarTipoUsuarioId(id);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há tipo de usuario com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public List<TipoUsuario> buscarTipoUsuario(TipoUsuarioTO tipoUsuarioTO) throws CustomExceptionTO{
		try {
			return tipoUsuarioDao.buscarTipoUsuario(tipoUsuarioTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}

	public boolean excluirTipoUsuario(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de usuarios o ID deve ser informado"));
			throw ce;
		}
		
		TipoUsuario usuario = this.buscarTipoUsuarioId(id);
		
		return tipoUsuarioDao.excluirTipoUsuario(usuario);
	}
}
