package br.com.principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.connection.ConnectionPool;

public class Principal {
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionPool.getConnection();

		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from amigos");
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String telefone = resultSet.getString("telefone");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(telefone);
		}
	}

}
