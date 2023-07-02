package br.com.uniacademia.enade.to;

import java.util.List;

import lombok.Data;

@Data
public class ProvaResultadoAlunoTO {

	private Long idProva;
	private Long idUsuario;
	
	private List<QuestaoResultadoTO> questoes;
}
