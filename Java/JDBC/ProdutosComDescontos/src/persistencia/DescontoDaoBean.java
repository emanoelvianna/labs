package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import negocio.DAOAutorException;
import negocio.DAOException;
import negocio.Desconto;

public class DescontoDaoBean implements DescontoDao {

	@Override
	public Desconto buscaObjetoPorChavePrimaria(char codigo) throws DAOException {
		String sql = "SELECT * FROM DISCOUNT_CODE WHERE discount_code = ?";
		Desconto desconto = null;
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (PreparedStatement comando = conexao.prepareStatement(sql)) {
				comando.setInt(1, codigo);
				try (ResultSet resultado = comando.executeQuery()) {
					if (resultado.next()) {
						desconto = new Desconto(
								resultado.getString("discount_code").charAt(1),
								resultado.getDouble("rate"));
					}
					return desconto;
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca", e);
		}
	}

	@Override
	public List<Desconto> consultarTodaAColecaoDeObjetos() {
		return null;
	}

	@Override
	public void InserirUmNovoObjeto(Desconto novo) {
		String sql = "INSERT INTO DISCOUNT_CODE(codigo,primeironome) values(?,?)";
		int resultado = 0;
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (PreparedStatement comando = conexao.prepareStatement(sql)) {
				comando.setInt(1, novo.getDiscount_code());
				comando.setDouble(2, novo.getRate());
				resultado = comando.executeUpdate();
			}
		} catch (Exception e) {
			throw new DAOException("Falha na inserção", e);
		}
		if (resultado == 0) {
			throw new DAOException("Falha na inserção");
		}
	}

}
