package br.com.negocio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProdutoTeste {

	private int codigo = 2020;
	String nome = "Test";
	private double valor = 100;
	private Produto produto;

	@Before
	public void inicializar() {
		produto = new Produto(codigo, nome, valor);
	}

	@Test
	public void deve_setar_corretamente_atributos_no_contrutor() {
		assertEquals(codigo, produto.getCodigo());
		assertEquals(nome, produto.getNome());
		assertEquals(valor, produto.getValor(), 0.001);
	}

}
