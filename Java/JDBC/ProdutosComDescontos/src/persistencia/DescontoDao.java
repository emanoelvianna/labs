package persistencia;

import java.util.List;

import negocio.DAOException;
import negocio.Desconto;

public interface DescontoDao {
	Desconto buscaObjetoPorChavePrimaria(char codigo) throws DAOException;

	List<Desconto> consultarTodaAColecaoDeObjetos() throws DAOException;

	void InserirUmNovoObjeto(Desconto novo) throws DAOException;
}
