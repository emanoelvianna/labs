package br.com.threads.lista;

public class Lista {
	private String[] elementos = new String[1000];
	private int indice = 0;

	public void adicionar(String elemento) {
		synchronized (this) { // somente um thread executa essa tarefa por vez
			elementos[indice] = elemento;
			indice++;
		}
	}

	public int tamanho() {
		return this.elementos.length;
	}

	public String getElemento(int indice) {
		return this.elementos[indice];
	}

}
