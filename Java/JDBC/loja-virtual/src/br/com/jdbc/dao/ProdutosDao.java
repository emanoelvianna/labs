package br.com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbc.modelo.Categoria;
import br.com.jdbc.modelo.Produto;

public class ProdutosDao {

	private Connection connection;

	public ProdutosDao(Connection connection) {
		this.connection = connection;
	}

	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto (nome, descricao) values(?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getDescricao());
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				resultSet.next();
				int id = resultSet.getInt("id");
				produto.setId(id);
			}
		}
	}

	public List<Produto> lista() throws SQLException {
		String sql = "select * from Produto";
		List<Produto> lista = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					Produto produto = new Produto(id, nome, descricao);
					lista.add(produto);
				}
			}
		}
		return lista;
	}

	public List<Produto> busca(Categoria categoria) throws SQLException {//0 = Eletrodoméstico
		
		String sql = "select * from Produto where categoria_id = ?";
		List<Produto> lista = new ArrayList<>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, categoria.getId());
			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					Produto produto = new Produto(id, nome, descricao);
					lista.add(produto);
				}
			}
		}
		return lista;
	}

}
