package br.com.padraoTemplateMethod;

public class IKCV extends TemplateDeImpostoCondicional {
	@Override
	protected double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.5;
	}

	@Override
	protected double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 1.0;
	}

	@Override
	protected boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() > 1000;
	}
}
