package br.com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jdbc.modelo.Categoria;
import br.com.jdbc.modelo.Produto;

public class CategoriasDao {

	private Connection connection;

	public CategoriasDao(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> lista() throws SQLException {
		List<Categoria> lista = new ArrayList<>();
		String sql = "Select * from Categoria";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.execute();
			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					Categoria categoria = new Categoria(id, nome);
					lista.add(categoria);
				}
			}
		}
		return lista;
	}

	public List<Categoria> listaComProdutos() throws SQLException {
		List<Categoria> lista = new ArrayList<>();
		Categoria ultima = null;
		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from Categoria as c join Produto as p on p.categoria_id = c.id";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.execute();
			try (ResultSet resultSet = preparedStatement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("c_id");
					String nome = resultSet.getString("c_nome");
					if (ultima == null || !ultima.getNome().equals(nome)) {
						Categoria categoria = new Categoria(id, nome);
						lista.add(categoria);
						ultima = categoria;
					}
					int idProduto = resultSet.getInt("p_id");
					String nomeProduto = resultSet.getString("p_nome");
					String descricao = resultSet.getString("p_descricao");
					Produto produto = new Produto(id, nomeProduto, descricao);
					ultima.adiciona(produto);
				}
			}
		}
		return lista;
	}

}
