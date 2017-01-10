package br.com.threads.lista2;

public class TarefaImprimir implements Runnable {

	private Lista lista;

	public TarefaImprimir(Lista lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		synchronized (lista) {
			// esperando para todos os elementos ser adicionados!
			try {
				lista.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < lista.tamanho(); i++) {
				System.out.println(lista.getElemento(i));
			}
		}
	}

}
