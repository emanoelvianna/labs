package br.com.padraoChainOfResponsibility;

public class Principal {
	public static void main(String[] args) {
		CalculaDesconto desconto = new CalculaDesconto();
		Orcamento orcamento = new Orcamento(600);
		orcamento.adicionaItem(new Item("Caneta", 250.0));
		orcamento.adicionaItem(new Item("Geladeira", 250.0));
	
		double descontFinal = desconto.calcula(orcamento);
		System.out.println(descontFinal);
	}
}
