package br.com.implementacao;

public class Cliente {

	private String nome;
	/*
	 * Outros atribuidos
	 */

	public Cliente(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
