package br.com.threads.arrayList;

import java.util.List;

public class TarefaAdicionar implements Runnable {

	private List<String> lista;
	private int numeroThread;

	public TarefaAdicionar(List<String> lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.add("Thread " + numeroThread + "-" + i);
		}
	}
	
}
