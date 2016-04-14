package persistencia;

import java.util.List;

import negocio.DAOException;
import negocio.Desconto;

public interface DescontoDao {
	Desconto buscarPorCodigo(char codigo) throws DAOException;

	List<Desconto> buscarTodos() throws DAOException;

	void inserir(Desconto novo) throws DAOException;
}
