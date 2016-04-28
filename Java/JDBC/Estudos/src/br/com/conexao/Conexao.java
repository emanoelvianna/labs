package br.com.conexao;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class Conexao {

	public static String DB_NAME = "cadastro de produtos";
	public static String USER_NAME = "usuario";
	public static String PASSWORD = "senha";
	private static DataSource dataSource;

	private static DataSource criarDataSource() throws Exception {
		if (dataSource == null) {
			EmbeddedDataSource ds = new EmbeddedDataSource();
			ds.setDatabaseName(DB_NAME);
			ds.setCreateDatabase("create");
			ds.setUser(USER_NAME);
			ds.setPassword(PASSWORD);
			dataSource = ds;
		}
		return dataSource;
	}

	public static void criarBaseDeDados() {
		try (Connection con = criarDataSource().getConnection(); 
			Statement sta = con.createStatement()) {
			String sqlProduto = "CREATE TABLE PRODUTO(" 
									+ "CODIGO int PRIMARY KEY NOT NULL,"
									+ "DESCRICAO varchar(100) NOT NULL," 
									+ "QUANTIDADE INT NOT NULL)";
			sta.executeUpdate(sqlProduto);
		} catch (Exception e) {

		}
	}

	public static Connection getConnection() throws Exception {
		return criarDataSource().getConnection();
	}

}
