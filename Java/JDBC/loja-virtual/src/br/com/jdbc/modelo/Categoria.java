package br.com.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int id;
	private String nome;
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void adiciona(Produto produto) {
		getProdutos().add(produto);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
