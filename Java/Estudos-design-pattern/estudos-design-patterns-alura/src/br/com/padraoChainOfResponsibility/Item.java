package br.com.padraoChainOfResponsibility;

public class Item {

	private double valor;
	private String nome;

	public Item(String nome, double valor) {
		this.setNome(nome);
		this.setValor(valor);
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
