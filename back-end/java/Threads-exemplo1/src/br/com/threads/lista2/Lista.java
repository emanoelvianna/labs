package br.com.threads.lista2;

public class Lista {
	private String[] elementos = new String[1000];
	private int indice = 0;

	public void adicionar(String elemento) {
		synchronized (this) { // somente um thread executa essa tarefa por vez
			elementos[indice] = elemento;
			indice++;

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// verifica se foi adicionado todos os elementos p/ então imprimir!
			if (this.indice == this.elementos.length) {
				this.notify();
			}
		}
	}

	public int tamanho() {
		return this.elementos.length;
	}

	public String getElemento(int indice) {
		return this.elementos[indice];
	}

}
