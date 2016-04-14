package persistencia;

import java.util.List;

import negocio.DAOException;
import negocio.Produto;

public interface ProdutoDao {
	Produto buscarPorCodigo(char codigo) throws DAOException;

	List<Produto> buscarTodos() throws DAOException;

	void inserir(Produto novo) throws DAOException;
}
