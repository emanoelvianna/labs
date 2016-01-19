package br.com.padraoChainOfResponsibility;

public class DescontoPorCincoItens implements Desconto{

	private Desconto proximo;

	public double desconta(Orcamento orcamento) {
		// mais de 5 itens deve haver desconto
		if (orcamento.getItens().size() > 5) {
			return orcamento.getValor() * 0.1;
		}
		return 0;
	}

	@Override
	public void setProximo(Desconto desconto) {
		this.proximo = desconto;
	}

}
