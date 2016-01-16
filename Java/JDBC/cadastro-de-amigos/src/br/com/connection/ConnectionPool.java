package br.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	private static final String STR_DRIVER = "org.postgresql.Driver";
	private static final String STR_CONNECTION = "jdbc:postgresql://localhost:5432/amigos";
	private static final String STR_DATABASE_USER = "postgres";
	private static final String STR_DATABASE_PWD = "admin";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(STR_CONNECTION, STR_DATABASE_USER, STR_DATABASE_PWD);
			System.out.println("conexão com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
