package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jdbc.modelo.Produto;

public class TestaDaoDeProduto {

	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa azul", "Possui 4 pés");

		try (Connection connection = new ConnectionPool().getConnection()) {

			ProdutosDao dao = new ProdutosDao(connection);
			dao.salva(mesa);

			List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println(produto);
			}
		}
	}

}
