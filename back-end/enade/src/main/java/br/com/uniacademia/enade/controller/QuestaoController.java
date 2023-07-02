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

import br.com.uniacademia.enade.model.Questao;
import br.com.uniacademia.enade.service.QuestaoService;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ResponseTO;
import br.com.uniacademia.enade.to.QuestaoTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/questao")
public class QuestaoController {
	
	@Autowired
	QuestaoService questaoService;
	
	@ApiOperation("Busca detalhes da questão com o id fornecido")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarQuestao(@PathVariable @ApiParam("Id da questao") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,questaoService.buscarQuestaoId(id));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca questões com base nos filtros informados")
	@RequestMapping(method = RequestMethod.GET, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarQuestao(QuestaoTO questao){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,questaoService.buscarQuestao(questao));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Cria uma questão")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Questão Cadastrada com sucesso", response = Questao.class),
		@ApiResponse(code = 400, message = "Parâmetros informados incorretamente", response = ResponseTO.class),
		@ApiResponse(code = 500, message = "Erro interno da aplicação", response = ResponseTO.class)
	})
	@RequestMapping(method = RequestMethod.POST, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarQuestao(@RequestBody Questao questao) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			questao = questaoService.cadastrarQuestao(questao);
			response = new ResponseTO(1,201,questao);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Atualiza uma questão existente")
	@RequestMapping(method = RequestMethod.PUT, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarQuestao(@PathVariable @ApiParam("Id da questao") Long id, 
											  @RequestBody @ApiParam("Dados da questao") Questao questao){
		ResponseTO response = new ResponseTO();
		
		try {
			questao.setId(id);
			questao = questaoService.atualizarQuestao(questao);
			response = new ResponseTO(1,questao);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Excluí uma questão existente")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removerQuestao(@PathVariable @ApiParam("Id da questao") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			questaoService.excluirQuestao(id);
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
