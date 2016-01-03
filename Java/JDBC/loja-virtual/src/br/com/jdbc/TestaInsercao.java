package br.com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		Connection connection = Database.getConnection();
		Statement statement = connection.createStatement();
		
		boolean resultado = statement.execute("insert into Produto (nome, descricao) values ('Notebook', 'Notebook i5')", statement.RETURN_GENERATED_KEYS);
		System.out.println(resultado);
		// devolve a lista de ids
		ResultSet resultSet = statement.getGeneratedKeys();
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println(id);
		}
		
		statement.close();
		connection.close();
	}
}
