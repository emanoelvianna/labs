package br.com.padraoTemplateMethod;

import java.util.List;

public class RelatorioSimples extends Relatorio {

	@Override
	protected void cabecalho() {
		System.out.println("cabecalho");
	}

	@Override
	protected void rodape() {
		System.out.println("rodape");
	}

	@Override
	protected void corpo(List<Conta> contas) {
		for (Conta conta : contas) {
			System.out.println(conta.getTitular());
			System.out.println(conta.getSaldo());
		}
	}

}
