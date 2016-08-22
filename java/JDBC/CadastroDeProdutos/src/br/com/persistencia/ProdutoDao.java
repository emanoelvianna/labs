package br.com.persistencia;

import java.util.List;

import br.com.negocio.Produto;

public interface ProdutoDao {
	List<ProdutoDto> buscarTodosProdutos(int codigo);
	
	ProdutoDto buscarProdutosPorCodigo(int codigo);

	List<ProdutoDto> buscarProdutosPorNome(String nome);

	boolean inserirProduto(ProdutoDto produto);

	boolean deletarProduto(ProdutoDto produto);
}
