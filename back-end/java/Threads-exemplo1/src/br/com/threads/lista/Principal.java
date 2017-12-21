package br.com.threads.lista;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		Lista lista = new Lista();

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new TarefaAdicionar(lista, i));
			thread.start();
		}

		Thread.sleep(2000);

		for (int i = 0; i < lista.tamanho(); i++) {
			System.out.println(lista.getElemento(i));
		}
	}

}
