package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
						desconto = new Desconto(resultado.getString("discount_code").charAt(1),
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
	public List<Desconto> consultarTodaAColecaoDeObjetos() throws DAOException {
		List<Desconto> autores = new ArrayList<>();
		String sql = "SELECT * FROM DISCOUNT_CODE";
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (Statement comando = conexao.createStatement()) {
				try (ResultSet resultado = comando.executeQuery(sql)) {
					while (resultado.next()) {
						Desconto autor = new Desconto(resultado.getString("discount_code").charAt(1),
								resultado.getDouble("rate"));
						autores.add(autor);
					}
					return autores;
				}
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca", e);
		}
	}

	@Override
	public void InserirUmNovoObjeto(Desconto novo) throws DAOException {
		String sql = "INSERT INTO DISCOUNT_CODE(discount_code,rate) values(?,?)";
		int resultado = 0;
		try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
			try (PreparedStatement comando = conexao.prepareStatement(sql)) {
				comando.setInt(1, novo.getDiscount_code());
				comando.setDouble(2, novo.getRate());
				resultado = comando.executeUpdate();
			}
		} catch (Exception e) {
			throw new DAOException("Falha na insersão", e);
		}
		if (resultado == 0) {
			throw new DAOException("Falha na insersão");
		}
	}

}
