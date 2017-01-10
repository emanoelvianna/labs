package br.com.threads.lista;

public class TarefaAdicionar implements Runnable {

	private Lista lista;
	private int numeroThread;

	public TarefaAdicionar(Lista lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.adicionar("Thread " + numeroThread + "-" + i);
		}
	}

}
