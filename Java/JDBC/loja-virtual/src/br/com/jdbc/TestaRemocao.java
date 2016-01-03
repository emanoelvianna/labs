package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
	
	public static void main(String[] args) throws SQLException {
		Connection connection = Database.getConnection();
		Statement statement = connection.createStatement();
		
		statement.execute("delete from Produto where id>3");
		int count = statement.getUpdateCount();
		System.out.println(count + " N�mero de linhas atualizadas");
		connection.close();
	}

}
