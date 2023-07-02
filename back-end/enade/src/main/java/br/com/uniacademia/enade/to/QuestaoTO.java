package br.com.uniacademia.enade.to;

import lombok.Data;

@Data
public class QuestaoTO {
	
	private Long id;
	private String descricao;
	private String estado;
	private Long tipoQuestao;
}
