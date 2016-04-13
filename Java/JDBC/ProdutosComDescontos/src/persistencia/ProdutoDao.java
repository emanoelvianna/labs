package persistencia;

import java.util.List;

import negocio.DAOException;
import negocio.Produto;

public interface ProdutoDao {
	Produto buscaObjetoPorChavePrimaria(char codigo) throws DAOException;

	List<Produto> consultarTodaAColecaoDeObjetos() throws DAOException;

	void InserirUmNovoObjeto(Produto novo) throws DAOException;
}
