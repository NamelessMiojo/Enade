package br.com.uniacademia.enade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class Questao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_QUESTAO")
	private Long id;
	
	@Column(name = "DESCRICAO_QUESTAO")
	private String descricao;
	
	@Column(name = "ALTERNATIVA_A")
	private String alternativaA;
	
	@Column(name = "ALTERNATIVA_B")
	private String alternativaB;
	
	@Column(name = "ALTERNATIVA_C")
	private String alternativaC;
	
	@Column(name = "ALTERNATIVA_D")
	private String alternativaD;
	
	@Column(name = "ALTERNATIVA_E")
	private String alternativaE;
	
	@Column(name = "QUESTAO_CORRETA")
	private String questaoCorreta;
	
	@Column(name = "ESTADO_QUESTAO")
	private Integer estadoQuestao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_QUESTAO")
	private TipoQuestao tipoQuestao;
}
