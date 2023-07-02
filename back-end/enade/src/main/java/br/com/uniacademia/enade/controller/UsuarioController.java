package br.com.uniacademia.enade.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.enade.model.Usuario;
import br.com.uniacademia.enade.service.UsuarioService;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ResponseTO;
import br.com.uniacademia.enade.to.UsuarioTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@ApiOperation("Busca detalhes do usuário com o id fornecido")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarUsuario(@PathVariable @ApiParam("Id do Usuario") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,usuarioService.buscarUsuarioId(id));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca usuários com base nos filtros informados")
	@RequestMapping(method = RequestMethod.GET, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarUsuario(UsuarioTO usuario){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,usuarioService.buscarUsuario(usuario));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Autentica usuário")
	@RequestMapping(method = RequestMethod.GET, path =  "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autenticarUsuario(UsuarioTO usuario) throws NoSuchAlgorithmException{
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,usuarioService.autenticarUsuario(usuario));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Cria um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário Cadastrado com sucesso", response = Usuario.class),
		@ApiResponse(code = 400, message = "Parâmetros informados incorretamente", response = ResponseTO.class),
		@ApiResponse(code = 500, message = "Erro interno da aplicação", response = ResponseTO.class)
	})
	@RequestMapping(method = RequestMethod.POST, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			usuario = usuarioService.cadastrarUsuario(usuario);
			response = new ResponseTO(1,201,usuario);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Atualiza um usuário existente")
	@RequestMapping(method = RequestMethod.PUT, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarUsuario(@PathVariable @ApiParam("Id do Usuario") Long id, 
											  @RequestBody @ApiParam("Dados do Usuario") Usuario usuario){
		ResponseTO response = new ResponseTO();
		
		try {
			usuario.setId(id);
			usuario = usuarioService.atualizarUsuario(usuario);
			response = new ResponseTO(1,usuario);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Excluí um usuário existente")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removerUsuario(@PathVariable @ApiParam("Id do Usuario") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			usuarioService.excluirUsuario(id);
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
