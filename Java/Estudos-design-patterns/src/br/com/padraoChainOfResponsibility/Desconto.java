package br.com.padraoChainOfResponsibility;

public interface Desconto {
	double desconta(Orcamento orcamento);
	void setProximo(Desconto desconto); // guarda o proximo desconto
}
