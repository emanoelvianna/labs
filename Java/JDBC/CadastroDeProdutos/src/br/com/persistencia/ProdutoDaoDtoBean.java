package br.com.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.model.Conexao;

public class ProdutoDaoDtoBean implements ProdutoDao {

	@Override
	public List<ProdutoDto> buscarProdutosPorCodigo(int codigo) {
		List<ProdutoDto> produtos = new ArrayList<>();
		String sql = "Select * from Produto";
		try (Connection conexo = Conexao.getConnection()) {
			try (Statement statement = conexo.createStatement()) {
				try (ResultSet resultado = statement.executeQuery(sql)) {
					while (resultado.next()) {
						ProdutoDto produto = new ProdutoDto();
						produto.setCodigo(resultado.getInt("codigo"));
						produto.setDescricao(resultado.getString("descricao"));
						produto.setQuantidade(resultado.getInt("quantidade"));
						produtos.add(produto);
					}
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {
		}

		return null;
	}

	@Override
	public List<ProdutoDto> buscarProdutosPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserirProduto(ProdutoDto produto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletarProduto(ProdutoDto produto) {
		// TODO Auto-generated method stub
		return false;
	}

}
