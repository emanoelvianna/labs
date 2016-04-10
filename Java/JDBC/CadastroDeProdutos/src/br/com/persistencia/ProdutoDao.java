package br.com.persistencia;

import java.util.List;

import br.com.negocio.Produto;

public interface ProdutoDao {
	List<Produto> buscarProdutosPorCodigo(int codigo);

	List<Produto> buscarProdutosPorNome(String nome);
	
	void inserirProduto(ProdutoDto produto);
}
