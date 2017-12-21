package br.com.threads.lista2;

public class Principal {

	public static void main(String[] args) throws InterruptedException {

		Lista lista = new Lista();

		for (int i = 0; i < 10; i++) {
			new Thread(new TarefaAdicionar(lista, i)).start();
		}

		new Thread(new TarefaImprimir(lista)).start();
	}

}
