package br.com.model.dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import br.com.model.Ordenacao;

public class OrdenacaoDAO {
	/**
	 * Metodo que salva em um arquivo de texto os dados do objeto de ordenacao
	 * 
	 * @param Ordenacao
	 *            ordenacao
	 * @throws FileNotFoundException
	 */
	public void salvar(Ordenacao ordenacao) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("ordenacao.txt");
		pw.print(ordenacao);
		pw.flush();
		pw.close();
	}
}
