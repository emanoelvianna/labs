package br.com.principal;

import java.sql.Connection;

import br.com.connection.ConnectionPool;

public class Principal {
	public static void main(String[] args) throws Exception {
		Connection connection =  ConnectionPool.getConnection();
		
	}
}
