package br.com.uniacademia.enade.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.uniacademia.enade.dao.UsuarioDao;
import br.com.uniacademia.enade.model.Usuario;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.UsuarioTO;
import br.com.uniacademia.enade.util.Util;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	public Usuario cadastrarUsuario(Usuario usuario) throws CustomExceptionTO, Exception {
		if(usuario.getId() != null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para o cadastro de novos usuarios o ID deve ser nulo"));
			throw ce;
		}
		
		try {
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
		
		return usuarioDao.persistirUsuario(usuario);
	}
	
	public boolean excluirUsuario(Long id) throws CustomExceptionTO {
		if(id == null) {
			CustomExceptionTO ce = new CustomExceptionTO(2,400,"Parâmetros informados incorretamente!");
			ce.setErrors(Arrays.asList("Para a exclusão de usuarios o ID deve ser informado"));
			throw ce;
		}
		
		Usuario usuario = this.buscarUsuarioId(id);
		
		return usuarioDao.excluirUsuario(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario) throws CustomExceptionTO{
		
		try {
			usuario = Util.mesclarClasse(this.buscarUsuarioId(usuario.getId()),usuario);
			return usuarioDao.atualizarUsuario(usuario);
		} catch (Exception e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há usuarios com o id " + usuario.getId() + " informado."));
			throw ce;
		}
	}
	
	public Usuario buscarUsuarioId(Long id) throws CustomExceptionTO{
		if(id == null)
			return null;
		
		try {
			return usuarioDao.buscarUsuarioId(id);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,404,"Conteúdo não encontrado");
			ce.setErrors(Arrays.asList("Não há usuario com o id " + id + " informado."));
			throw ce;
		}
		
	}
	
	public List<Usuario> buscarUsuario(UsuarioTO usuarioTO) throws CustomExceptionTO{
		try {
			return usuarioDao.buscarUsuario(usuarioTO);
		} catch (NoResultException e) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}
	
	public Usuario autenticarUsuario(UsuarioTO usuarioTO) throws CustomExceptionTO, NoSuchAlgorithmException{
		try {
			return usuarioDao.buscarUsuarioAutenticacao(usuarioTO.getEmail(),criptografarSenha(usuarioTO.getSenha()));
		} catch (NoResultException | EmptyResultDataAccessException  ex) {
			CustomExceptionTO ce = new CustomExceptionTO(2,204);
			throw ce;
		}
		
	}
	
	private String criptografarSenha(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes());
		byte[] digest = md.digest();
		    
		return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}

}
