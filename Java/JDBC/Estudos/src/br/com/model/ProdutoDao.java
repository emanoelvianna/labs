package br.com.model;

import java.util.List;

import br.com.entidade.Produto;

public interface ProdutoDao {

	void inserirProduto(Produto produto);

	void deletarProduto(Produto produtos);

	Produto buscarProdutoPeloCodigo(int codigo);
	
	List<Produto> buscarTodosProduto();

}
