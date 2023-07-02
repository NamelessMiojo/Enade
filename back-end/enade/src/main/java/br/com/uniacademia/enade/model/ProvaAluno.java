package br.com.uniacademia.enade.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.com.uniacademia.enade.to.ProvaResultadoAlunoTO;
import br.com.uniacademia.enade.to.QuestaoResultadoTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ProvaAluno {
	
	@EmbeddedId
	ProvaAlunoId id;
	
	@Column(name = "ALTERNATIVA")
	String alternativa;
	
	
	public ProvaAluno(ProvaResultadoAlunoTO prova, QuestaoResultadoTO questao) {
		this.id = new ProvaAlunoId();
		id.setIdProva(prova.getIdProva());
		id.setIdUsuario(prova.getIdUsuario());
		id.setIdQuestao(questao.getIdQuestao());
		this.setAlternativa(questao.getAlternativa());
	}
}
