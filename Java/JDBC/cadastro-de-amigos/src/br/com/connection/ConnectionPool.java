package br.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPool {

	private static final String STR_DRIVER = "org.postgresql.Driver";
	private static final String STR_CONNECTION = "jdbc:postgresql://localhost:5432/cadastro_de_amigos";
	private static final String STR_DATABASE_USER = "usuario";
	private static final String STR_DATABASE_PWD = "senha";

	private static ConnectionPool instance = new ConnectionPool();

	static {
		try {
			Class.forName(STR_DRIVER);
		} catch (Exception e) {
			System.out.println("Erro ao carregar driver JDBC: " + STR_DRIVER);
			e.printStackTrace();
		}
	}

	public ConnectionPool() {};

	public static ConnectionPool getInstance() {
		return instance;
	}

	public static Connection getConnection() throws Exception {
		return getConnectionManual();
	}

	private static Connection getConnectionManual() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(STR_CONNECTION, STR_DATABASE_USER, STR_DATABASE_PWD);
		} catch (Exception e) {
			throw new Exception("Erro ao obter conexão via DriverManager: " + STR_CONNECTION);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		// Fecha o resultset aberto
		if (rs != null) {
			rs.close();
		}
		// Fecha o preparedstatement aberto
		if (pstmt != null) {
			pstmt.close();
		}
		// Fecha a conexão aberta
		if (conn != null) {
			if (!conn.isClosed()) {
				conn.close();
			}
		}
	}

}
