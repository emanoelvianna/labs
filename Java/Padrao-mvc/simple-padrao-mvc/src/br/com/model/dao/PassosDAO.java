package br.com.model.dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import br.com.model.Passos;

public class PassosDAO {
	/**
	 * Metodo que recebe todos os passos (lista) e salva todos em um arquivo
	 * 
	 * @param passos
	 * @throws FileNotFoundException
	 */
	public void salvarPassos(List<Passos> passos) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("passos.txt");
		for (Passos p : passos) {
			pw.print(p);
		}
		pw.flush();
		pw.close();
	}
}
