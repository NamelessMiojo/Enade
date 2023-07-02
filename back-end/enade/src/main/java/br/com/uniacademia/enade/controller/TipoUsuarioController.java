package br.com.uniacademia.enade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniacademia.enade.model.TipoUsuario;
import br.com.uniacademia.enade.service.TipoUsuarioService;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ResponseTO;
import br.com.uniacademia.enade.to.TipoUsuarioTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/tipoUsuario")
public class TipoUsuarioController {
	
	@Autowired
	TipoUsuarioService tipoUsuarioService;
	
	@ApiOperation("Busca detalhes do tipo do usuário com o id fornecido")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarTipoUsuario(@PathVariable @ApiParam("Id do Tipo do usuario") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,tipoUsuarioService.buscarTipoUsuarioId(id));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca tipos de usuários com base nos filtros informados")
	@RequestMapping(method = RequestMethod.GET, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarTipoUsuario(TipoUsuarioTO tipoUsuario){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,tipoUsuarioService.buscarTipoUsuario(tipoUsuario));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Cria um tipo de usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Tipo Usuário Cadastrado com sucesso", response = TipoUsuario.class),
		@ApiResponse(code = 400, message = "Parâmetros informados incorretamente", response = ResponseTO.class),
		@ApiResponse(code = 500, message = "Erro interno da aplicação", response = ResponseTO.class)
	})
	@RequestMapping(method = RequestMethod.POST, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			tipoUsuario = tipoUsuarioService.cadastrarTipoUsuario(tipoUsuario);
			response = new ResponseTO(1,201,tipoUsuario);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Atualiza um tipo de usuário existente")
	@RequestMapping(method = RequestMethod.PUT, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarTipoUsuario(@PathVariable @ApiParam("Id do tipo do usuario") Long id, @RequestBody @ApiParam("Dados do TipoUsuario") TipoUsuario tipoUsuario){
		ResponseTO response = new ResponseTO();
		
		try {
			tipoUsuario.setId(id);
			tipoUsuario = tipoUsuarioService.atualizarTipoUsuario(tipoUsuario);
			response = new ResponseTO(1,tipoUsuario);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Excluí um tipo de usuário existente")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removerUsuario(@PathVariable @ApiParam("Id do tipo do usuario") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			tipoUsuarioService.excluirTipoUsuario(id);
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
