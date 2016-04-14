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

public class ProdutoDaoBean implements ProdutoDao {

	private ProdutoDaoBean produtoDao;
	private DescontoDaoBean descontoDao;

	public ProdutoDaoBean() {
		produtoDao = new ProdutoDaoBean();
		descontoDao = new DescontoDaoBean();
	}

	@Override
	public List<Produto> buscarTodos() throws DAOException {
		List<Produto> produtos = new ArrayList<>();
		String sqlProdutos = "SELECT * FROM PRODUTO_CODE";
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (Statement comando = conexao.createStatement()) {
				try (ResultSet resultado = comando.executeQuery(sqlProdutos)) {
					while (resultado.next()) {
						Produto produto = new Produto(
								resultado.getString("prod_code").charAt(2),
								descontoDao.buscarPorCodigo(resultado.getString("discount_code").charAt(2)),
								resultado.getString("description"));

						produtos.add(produto);
					}
					return produtos;
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca", e);
		}
	}

	@Override
	public Produto buscarPorCodigo(char codigo) throws DAOException {
		 String sql = "SELECT * FROM PRODUTO_CODE WHERE PROD_CODE = ?";
	        Produto produto = null;
	        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
	            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
	                comando.setInt(1, codigo);
	                try (ResultSet resultado = comando.executeQuery()) {
	                    if (resultado.next()) {
	                        produto = new Produto(
	                        		resultado.getString("prod_code").charAt(2),
									descontoDao.buscarPorCodigo(resultado.getString("discount_code").charAt(2)),
									resultado.getString("description")
	                        );
	                    }
	                    return produto;
	                }
	            }
	        } catch (Exception e) {
	            throw new DAOException("Falha na busca", e);
	        }
	}

	@Override
	public void inserir(Produto novo) throws DAOException {
		String sqlProduto = "INSERT INTO PRODUTO_CODE(PROD_CODE, DISCOUNT_CODE, DESCRITION) values(?,?,?)";
        String sqlProdutoDesconto = "INSERT INTO PRODUTODESCONTO(PROD_CODE,DISCOUNT_CODE) VALUES(?,?)";
        int resultado = 0;
        try {
            // Recupera o desconto
        	
        	char codDesconto = novo.getDiscount_code().getDiscount_code();
        	Desconto desconto = descontoDao.buscarPorCodigo(codDesconto);
        	
            // Executa a inserção do livro propriamente dita
            try(Connection conexao = InicializadorBancoDadosDataSource.conectarBd()){
                // Insere o Livro na tabela Livros
                try(PreparedStatement comando = conexao.prepareStatement(sqlProduto)){
                	
                	comando.setString(1, String.valueOf(novo.getProd_code()));
                	comando.setString(2, String.valueOf(desconto.getDiscount_code()));
                	comando.setString(3, String.valueOf(novo.getDescription()));
                    resultado = comando.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new DAOException("Falha na inserção", e);
        }

	}

}
