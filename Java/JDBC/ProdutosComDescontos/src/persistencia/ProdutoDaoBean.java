package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import negocio.DAOException;
import negocio.Produto;

public class ProdutoDaoBean implements ProdutoDao {

	@Override
	public Produto buscaObjetoPorChavePrimaria(char codigo) throws DAOException {
		String sql = "SELECT * FROM PRODUTO_CODE WHERE prod_code = ?";
		Produto produto = null;
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (PreparedStatement comando = conexao.prepareStatement(sql)) {
				comando.setInt(1, codigo);
				try (ResultSet resultado = comando.executeQuery()) {
					if (resultado.next()) {
						//desconto = new Desconto(resultado.getString("discount_code").charAt(1),
						//		resultado.getDouble("rate"));
					}
					return produto;
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca", e);
		}
	}

	@Override
	public List<Produto> consultarTodaAColecaoDeObjetos() {
		return null;
	}

	@Override
	public void InserirUmNovoObjeto(Produto novo) {

	}

}
