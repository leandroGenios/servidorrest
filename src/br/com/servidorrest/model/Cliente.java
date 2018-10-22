package br.com.servidorrest.model;

public class Cliente {
	private int id;
	private String cpf;
	private String nome;
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
