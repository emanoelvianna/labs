package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import negocio.Autor;
import negocio.DAOAutorException;
import negocio.DAOException;
import negocio.Desconto;
import negocio.Produto;

public class ProdutoDaoBean implements ProdutoDao {

	@Override
	public Produto buscaObjetoPorChavePrimaria(char codigo) throws DAOException {
		String sql = "select * from autores where codigo = ?";
        Autor autor = null;
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, codigo);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        autor = new Autor(
                                resultado.getInt("codigo"),
                                resultado.getString("primeironome"),
                                resultado.getString("ultimonome")
                        );
                    }
                    return autor;
                }
            }
        } catch (Exception e) {
            throw new DAOAutorException("Falha na busca", e);
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
