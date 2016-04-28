package br.com.principal;

import java.util.List;

import br.com.conexao.Conexao;
import br.com.entidade.Produto;
import br.com.model.ProdutoDaoBean;

public class Principal {

	public static void main(String[] args) {
		Conexao conexao = new Conexao();
		ProdutoDaoBean produtoDaoBean = new ProdutoDaoBean();

		System.out.println("-- Criando a base de dados --");
		conexao.criarBaseDeDados();
		System.out.println("-- base de dados criada --");
		System.out.println("---------------------------");

		System.out.println("-- inserindo o produto Tv --");
		Produto produto = new Produto();
		produto.setCodigo(1);
		produto.setDescricao("Televisão 32 polegadas");
		produto.setQuantidade(2);
		produtoDaoBean.inserirProduto(produto);
		System.out.println("-- Produto inserido --");

		System.out.println("------------------------------");
		System.out.println("-- Buscar todos os produtos --");
		List<Produto> produtos = produtoDaoBean.buscarTodosProduto();
		for (Produto i : produtos) {
			System.out.println(i.getDescricao());
		}
	}

}
