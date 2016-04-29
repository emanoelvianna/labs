package br.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.conexao.Conexao;
import br.com.entidade.Produto;

public class ProdutoDaoBean implements ProdutoDao {

	@Override
	public void inserirProduto(Produto produto) {
		String sql = "insert into Produto (codigo, descricao, quantidade) values(?, ?, ?)";
		try (Connection conexao = Conexao.getConnection()) {
			try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
				preparedStatement.setInt(1, produto.getCodigo());
				preparedStatement.setString(2, produto.getDescricao());
				preparedStatement.setInt(3, produto.getQuantidade());
				preparedStatement.executeUpdate();
				conexao.close();
				preparedStatement.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar inserir produto");
		}

	}

	@Override
	public void deletarProduto(Produto produto) {
		String sql = "delete from Produto where codigo = ?";
		try (Connection conexao = Conexao.getConnection()) {
			try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
				preparedStatement.setInt(1, produto.getCodigo());
				preparedStatement.execute();
			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar deletar produto");
		}
	}

	@Override
	public List<Produto> buscarTodosProduto() {
		List<Produto> listaDeProdutos = new ArrayList<>();
		String sql = "select * from Produto";
		try (Connection conexao = Conexao.getConnection()) {
			try (Statement statement = conexao.createStatement()) {
				boolean resultado = statement.execute(sql);
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Produto produto = new Produto();
					produto.setCodigo(resultSet.getInt("codigo"));
					produto.setDescricao(resultSet.getString("descricao"));
					produto.setQuantidade(resultSet.getInt("quantidade"));
					listaDeProdutos.add(produto);
				}
				resultSet.close();
				conexao.close();
				return listaDeProdutos;
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os produtos");
		}

		return listaDeProdutos;
	}

	@Override
	public Produto buscarProdutoPeloCodigo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
