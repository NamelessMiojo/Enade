package br.com.uniacademia.enade.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ProvaAlunoId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_PROVA")
	private Long idProva;
	
	@Column(name = "ID_QUESTAO")
	private Long idQuestao;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
}
