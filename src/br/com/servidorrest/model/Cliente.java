package br.com.servidorrest.model;

import javax.validation.constraints.NotNull;

public class Cliente {
	private int id;
	@NotNull(message = "O preenchimento do CPF é Obrigatório")
	private String cpf;
	@NotNull(message = "O preenchimento do nome é Obrigatório")
	private String nome;
	@NotNull(message = "O preenchimento do sobrenome é Obrigatório")
	private String sobrenome;
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String cpf, String nome, String sobrenome) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
}
