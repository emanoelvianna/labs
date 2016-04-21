package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import negocio.DAOException;
import negocio.Desconto;
import negocio.Produto;

public class DescontoDaoBean implements DescontoDao {

	private ProdutoDaoBean produtoDao;
	private DescontoDaoBean descontoDao;

	@Override
	public List<Desconto> buscarTodos() throws DAOException {
		List<Desconto> descontos = new ArrayList<>();
		List<Produto> produtos;
		String sqlDescontos = "SELECT * FROM DISCOUNT_CODE";
		String sqlProdutosDescontos = "SELECT * FROM PRODUTODESCONTO WHERE DISCOUNT_CODE = ?";
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (Statement comandoLivros = conexao.createStatement()) {
				try (ResultSet resultado = comandoLivros.executeQuery(sqlDescontos)) {

					// Para cada linha da tabela
					while (resultado.next()) {
						// Recupera todos os ProdutosDescontos
						try (PreparedStatement comandoLivrosAutores = conexao.prepareStatement(sqlProdutosDescontos)) {
							comandoLivrosAutores.setString(1, resultado.getString("DISCOUNT_CODE"));
							try (ResultSet resultadoLivrosAutores = comandoLivrosAutores.executeQuery()) {
								produtos = new ArrayList<>();
								while (resultadoLivrosAutores.next()) {
									char codigoProduto = resultado.getString("prod_code").charAt(2);
									produtos.add(produtoDao.buscarPorCodigo(codigoProduto));
								}
							}
						}
						Desconto desconto = new Desconto(resultado.getString("discount_code").charAt(2),
								resultado.getDouble("rate"), produtos.get(0));
						// Acrescenta os demais produtos
						for (int i = 1; i < produtos.size(); i++) {
							desconto.addProduto(produtos.get(i));
						}
						descontos.add(desconto);
					}
					return descontos;
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca: " + e.getMessage(), e);
		}
	}

	@Override
	public Desconto buscarPorCodigo(char codigo) throws DAOException {
		Desconto desconto = null;
		List<Produto> produtos;
		String sqlDesconto = "SELECT * FROM DESCONTO WHERE codigo=?";
		String sqlProdutoDesconto = "SELECT * FROM PRODUTODESCONTO WHERE DISCOUNT_CODE = ?";
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			// Recupera todos os produtos
			try (PreparedStatement comandoLivrosAutores = conexao.prepareStatement(sqlProdutoDesconto)) {
				comandoLivrosAutores.setInt(1, codigo);
				try (ResultSet resultado = comandoLivrosAutores.executeQuery()) {
					produtos = new ArrayList<>();
					while (resultado.next()) {
						char codigoProduto = resultado.getString("prod_code").charAt(2);
						produtos.add(produtoDao.buscarPorCodigo(codigoProduto));
					}
				}
			}
			// Recupera o livro
			try (PreparedStatement comandoLivros = conexao.prepareStatement(sqlDesconto)) {
				comandoLivros.setInt(1, codigo);
				try (ResultSet resultado = comandoLivros.executeQuery()) {
					resultado.next();
					desconto = new Desconto(resultado.getString("discount_code").charAt(2), resultado.getDouble("rate"),
							produtos.get(0));

					// Acrescenta os demais autores se houver
					for (int i = 1; i < produtos.size(); i++) {
						desconto.addProduto(produtos.get(i));
					}
				}
			}
			return desconto;
		} catch (Exception e) {
			throw new DAOException("Falha na busca: " + e.getMessage(), e);
		}
	}

	@Override
	public void inserir(Desconto desconto) throws DAOException {
		String sqlDesconto = "INSERT INTO DISCOUNT_CODE(DISCOUNT_CODE,RATE) values(?,?)";
		String sqlProdutoDesconto = "INSERT INTO PRODUTODESCONTO(PROD_CODE,DISCOUNT_CODE) values(?,?)";
		int resultado = 0;
		try {

			// Recupera os produtos
			for (Produto p : desconto.getProdutos()) {
				Produto umProduto = produtoDao.buscarPorCodigo(p.getProd_code());
			}

			// Executa a inserção do livro propriamente dita
			try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
				// Insere o Livro na tabela Livros
				try (PreparedStatement comando = conexao.prepareStatement(sqlDesconto)) {
					comando.setString(1, String.valueOf(desconto.getDiscount_code()));
					comando.setDouble(2, desconto.getRate());
					resultado = comando.executeUpdate();
				}
				// Atualiza a tabela LivrosAutores
				try (PreparedStatement comandoProdutoDesconto = conexao.prepareStatement(sqlProdutoDesconto)) {
					for (Produto p : desconto.getProdutos()) {
						comandoProdutoDesconto.setInt(1, desconto.getDiscount_code());
						comandoProdutoDesconto.setInt(2, p.getProd_code());
						resultado += comandoProdutoDesconto.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na inserção", e);
		}
		// Verifica se foi feita a inserção
		if (resultado < desconto.getProdutos().size() + 1) {
			throw new DAOException("Falha na inserção");
		}
	}

}
