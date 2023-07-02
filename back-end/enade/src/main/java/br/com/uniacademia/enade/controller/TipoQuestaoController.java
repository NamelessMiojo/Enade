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

import br.com.uniacademia.enade.model.TipoQuestao;
import br.com.uniacademia.enade.service.TipoQuestaoService;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ResponseTO;
import br.com.uniacademia.enade.to.TipoQuestaoTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/tipoQuestao")
public class TipoQuestaoController {
	
	@Autowired
	TipoQuestaoService tipoQuestaoService;
	
	@ApiOperation("Busca detalhes do tipo da questão com o id fornecido")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarTipoQuestao(@PathVariable @ApiParam("Id do Tipo da questão") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,tipoQuestaoService.buscarTipoQuestaoId(id));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca tipos de usuários com base nos filtros informados")
	@RequestMapping(method = RequestMethod.GET, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarTipoQuestao(TipoQuestaoTO tipoQuestao){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,tipoQuestaoService.buscarTipoQuestao(tipoQuestao));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Cria um tipo de questão")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Tipo Usuário Cadastrado com sucesso", response = TipoQuestao.class),
		@ApiResponse(code = 400, message = "Parâmetros informados incorretamente", response = ResponseTO.class),
		@ApiResponse(code = 500, message = "Erro interno da aplicação", response = ResponseTO.class)
	})
	@RequestMapping(method = RequestMethod.POST, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarTipoQuestao(@RequestBody TipoQuestao tipoQuestao) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			tipoQuestao = tipoQuestaoService.cadastrarTipoQuestao(tipoQuestao);
			response = new ResponseTO(1,201,tipoQuestao);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Atualiza um tipo da questão existente")
	@RequestMapping(method = RequestMethod.PUT, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarTipoQuestao(@PathVariable @ApiParam("Id do tipo da questão") Long id, @RequestBody @ApiParam("Dados do TipoQuestao") TipoQuestao tipoQuestao){
		ResponseTO response = new ResponseTO();
		
		try {
			tipoQuestao.setId(id);
			tipoQuestao = tipoQuestaoService.atualizarTipoQuestao(tipoQuestao);
			response = new ResponseTO(1,tipoQuestao);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Excluí um tipo da questão existente")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removerUsuario(@PathVariable @ApiParam("Id do tipo da questão") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			tipoQuestaoService.excluirTipoQuestao(id);
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
