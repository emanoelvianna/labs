package br.com.principal;

import br.com.model.Conexao;
import br.com.persistencia.ProdutoDao;
import br.com.persistencia.ProdutoDaoDtoBean;
import br.com.persistencia.ProdutoDto;

public class Principal {
	
	public enum minhaEnum {
		
	}
	
	public static class myclass {
		public void x(minhaEnum e){
			
		}
	}
	
	
	
	public static void main(String[] args) {
		
		myclass my = new myclass();
		my.x(null);
		
		System.out.println("-- Criando a base de dados --");
		Conexao.criarBaseDeDados();

		System.out.println("-- Criando um novo produto --");
		ProdutoDao produtoDao = new ProdutoDaoDtoBean();
		ProdutoDto produto = new ProdutoDto();
		produto.setCodigo(1);
		produto.setDescricao("Sab�o em p�");
		produto.setQuantidade(10);
		produtoDao.inserirProduto(produto);

		System.out.println("-- Buscando por produto --");
		ProdutoDto p = produtoDao.buscarProdutosPorCodigo(1);
		
		if(p == null) {
			System.out.println("erro");
		}
		System.out.println("-- Produto retornado --");
		System.out.println("Codigo: " + p.getCodigo() + "Descri��o: " + p.getDescricao());
	}
}
