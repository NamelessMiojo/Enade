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

import br.com.uniacademia.enade.model.Prova;
import br.com.uniacademia.enade.model.ProvaQuestao;
import br.com.uniacademia.enade.service.ProvaService;
import br.com.uniacademia.enade.to.CustomExceptionTO;
import br.com.uniacademia.enade.to.ProvaResultadoAlunoTO;
import br.com.uniacademia.enade.to.ProvaTO;
import br.com.uniacademia.enade.to.ResponseTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/prova")
public class ProvaController {
	
	@Autowired
	ProvaService provaService;
	
	@ApiOperation("Busca detalhes da prova com o id fornecido")
	@RequestMapping(method = RequestMethod.GET, value = "aplicacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarProva(@PathVariable @ApiParam("Id da prova") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,provaService.buscarProvaAplicacao(id));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca provas com base nos filtros informados")
	@RequestMapping(method = RequestMethod.GET, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarProva(ProvaTO prova){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,provaService.buscarProva(prova));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Envia Resultado da prova")
	@RequestMapping(method = RequestMethod.POST, value = "/resultado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inserirResultadoProva(@RequestBody ProvaResultadoAlunoTO respostas){
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,provaService.inserirRespostasAlunoProva(respostas));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Busca Resultado da prova")
	@RequestMapping(method = RequestMethod.POST, value = "/resultado/{idProva}/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscaResultadoProva(@PathVariable @ApiParam("Id da prova") Long idProva, @PathVariable @ApiParam("Id do usuário") Long idUsuario) throws Exception{
		ResponseTO response = new ResponseTO();
		
		try {
			response = new ResponseTO(1,provaService.buscarPontuacaoProvaAluno(idProva,idUsuario));
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Cria uma prova")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Prova Cadastrada com sucesso", response = Prova.class),
		@ApiResponse(code = 400, message = "Parâmetros informados incorretamente", response = ResponseTO.class),
		@ApiResponse(code = 500, message = "Erro interno da aplicação", response = ResponseTO.class)
	})
	@RequestMapping(method = RequestMethod.POST, path =  "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarProva(@RequestBody Prova prova) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			prova = provaService.cadastrarProva(prova);
			response = new ResponseTO(1,201,prova);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Adiciona uma questão à prova")
	@RequestMapping(method = RequestMethod.POST, path =  "/{idProva}/{idQuestao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrarQuestaoProva(@PathVariable @ApiParam("Id da prova") Long idProva, @PathVariable @ApiParam("Id da questão") Long idQuestao) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			Prova prova = provaService.persistirProvaQuestao(new ProvaQuestao(idProva,idQuestao));
			response = new ResponseTO(1,201,prova);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Remove uma questão da prova")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{idProva}/{idQuestao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> excluirQuestaoProva(@PathVariable @ApiParam("Id da prova") Long idProva, @PathVariable @ApiParam("Id da questão") Long idQuestao) throws Exception{
		ResponseTO response = new ResponseTO();
		try {
			provaService.removerProvaQuestao(new ProvaQuestao(idProva,idQuestao));
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Atualiza uma prova existente")
	@RequestMapping(method = RequestMethod.PUT, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarProva(@PathVariable @ApiParam("Id da prova") Long id, @RequestBody @ApiParam("Dados do Prova") Prova prova){
		ResponseTO response = new ResponseTO();
		
		try {
			prova.setId(id);
			prova = provaService.atualizarProva(prova);
			response = new ResponseTO(1,prova);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@ApiOperation("Excluí uma prova existente")
	@RequestMapping(method = RequestMethod.DELETE, path =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removerUsuario(@PathVariable @ApiParam("Id da prova") Long id){
		ResponseTO response = new ResponseTO();
		
		try {
			provaService.excluirProva(id);
			response = new ResponseTO(1);
			
		} catch (CustomExceptionTO e) {
			response = new ResponseTO(e);
		}
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
}
