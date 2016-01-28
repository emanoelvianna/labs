package br.com.padraoTemplateMethod;

import java.util.ArrayList;
import java.util.List;

import br.com.padraoChainOfResponsibility.Item;

public class IHIT extends TemplateDeImpostoCondicional {

	@Override
	protected double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.1 + orcamento.getItens().size();
	}

	@Override
	protected double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 1.3 + 100;
	}

	@Override
	protected boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		List<String> noOrcamento = new ArrayList<String>();

		for (Item item : orcamento.getItens()) {
			if (noOrcamento.contains(item.getNome()))
				return true;
			else
				noOrcamento.add(item.getNome());
		}
		return false;
	}

}
