package br.com.uniacademia.enade.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ProvaQuestao {
	
	@EmbeddedId
	private ProvaQuestaoID id;
	
	

	public ProvaQuestao(Prova prova, Questao questao) {
		this.id = new ProvaQuestaoID();
		this.id.setIdProva(prova.getId());
		this.id.setIdQuestao(questao.getId());
	}
	
	public ProvaQuestao(Long prova, Long questao) {
		this.id = new ProvaQuestaoID();
		this.id.setIdProva(prova);
		this.id.setIdQuestao(questao);
	}

	public ProvaQuestao(ProvaQuestaoID prova) {
		this.id = new ProvaQuestaoID();
		this.id.setIdProva(prova.getIdProva());
		this.id.setIdQuestao(prova.getIdQuestao());
	}

	public ProvaQuestao() {
	}
}