package br.com.uniacademia.enade.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class Prova {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROVA")
	private Long id;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "DATA_PROVA")
	private LocalDateTime dataProva;
	
	@Transient
	private List<Questao> questoes;
	
	public Prova(Long id) {
		this.id = id;
	}
}