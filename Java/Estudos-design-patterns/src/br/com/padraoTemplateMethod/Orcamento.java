package br.com.padraoTemplateMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.padraoChainOfResponsibility.Item;

public class Orcamento {

	private final List<Item> itens; // final : não ira ser modificado
	private double valor;

	public Orcamento(double valor) {
		this.valor = valor;
		itens = new ArrayList<>();
	}

	public double getValor() {
		return valor;
	}

	public void adicionaItem(Item item) {
		itens.add(item);
	}
	
	public List<Item> getItens() {
		return Collections.unmodifiableList(itens); // retorna lista imutavel, ou seja ela não ira ser alterada
	}
}
