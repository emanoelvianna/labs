package br.com.principal;

import br.com.model.Conexao;
import br.com.persistencia.ProdutoDao;
import br.com.persistencia.ProdutoDaoDtoBean;
import br.com.persistencia.ProdutoDto;

public class Principal {
	public static void main(String[] args) {
		System.out.println("-- Criando a base de dados --");
		Conexao.criarBaseDeDados();

		System.out.println("-- Criando um novo produto --");
		ProdutoDao produtoDao = new ProdutoDaoDtoBean();
		ProdutoDto produto = new ProdutoDto();
		produto.setCodigo(1);
		produto.setDescricao("Sabão em pó");
		produto.setQuantidade(10);
		produtoDao.inserirProduto(produto);

		System.out.println("-- Buscando por produto --");
		ProdutoDto p = produtoDao.buscarProdutosPorCodigo(1);
		
		if(p == null) {
			System.out.println("erro");
		}
		System.out.println("-- Produto retornado --");
		System.out.println("Codigo: " + p.getCodigo() + "Descrição: " + p.getDescricao());
	}
}
