package org.com.arvoreBinaria;

public class ArvoreBinaria implements Arvore {
	private class Nodo {
		private int elemento;
		private Nodo esq;
		private Nodo dir;

		public Nodo(int elemento) {
			this.elemento = elemento;
		}

		public Nodo getEsq() {
			return esq;
		}

		public void setEsq(Nodo esq) {
			this.esq = esq;
		}

		public Nodo getDir() {
			return dir;
		}

		public void setDir(Nodo dir) {
			this.dir = dir;
		}

		public int getElemento() {
			return elemento;
		}

		public void setElemento(int elemento) {
			this.elemento = elemento;
		}
	}

	private Nodo raiz;
	private int tamanho;

	@Override
	public boolean incluir(int elemento) {
		if (raiz == null) {
			Nodo novo = new Nodo(elemento);
			raiz = novo;
			tamanho++;
		} else {
			
		}
		return false;
	}

	@Override
	public boolean remover(int elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pesquisar(int elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vazia() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toSTring() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTamanho() {
		return tamanho;
	}

}
