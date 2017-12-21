package org.com.arvoreBinaria;

public interface Arvore {

	public boolean incluir(int elemento);

	public boolean remover(int elemento);

	public boolean pesquisar(int elemento);

	public boolean vazia();

	public String toSTring();

}
