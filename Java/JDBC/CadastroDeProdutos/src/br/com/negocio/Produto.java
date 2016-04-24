package br.com.negocio;

public class Produto {

	private int codigo;
	private String nome;
	private int quantidade;
	private double valor;
	private String descricao;

	public Produto(int codigo, String nome, double valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

}
