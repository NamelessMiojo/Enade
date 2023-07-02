package br.com.uniacademia.enade.to;

import lombok.Data;

@Data
public class UsuarioTO {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Long tipoUsuario;
}
