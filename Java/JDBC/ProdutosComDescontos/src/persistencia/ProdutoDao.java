package persistencia;

import java.util.List;

import negocio.Produto;

public interface ProdutoDao {
	char buscaObjetoPorChavePrimaria(char codigo);

	List<Produto> consultarTodaAColecaoDeObjetos();

	void InserirUmNovoObjeto(Produto novo);
}
