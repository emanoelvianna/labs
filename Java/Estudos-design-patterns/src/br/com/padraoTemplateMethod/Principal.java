package br.com.padraoTemplateMethod;

import java.util.ArrayList;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		Orcamento orcamento1 = new Orcamento(1000);
		Orcamento orcamento2 = new Orcamento(1000);
		Imposto imposto1 = new ICPP();
		System.out.println(imposto1.calcula(orcamento1));
		
		Imposto imposto2 = new IKCV();
		System.out.println(imposto2.calcula(orcamento2));
		
		System.out.println("------------- Relatorio --------------");
		
		List<Conta> listaDeContas = new ArrayList<>();
		Conta conta1 = new Conta("titular1", 1540);
		Conta conta2 = new Conta("titular2", 40);
		listaDeContas.add(conta1);
		listaDeContas.add(conta2);
		
		
		RelatorioSimples relatorioSimples = new RelatorioSimples();
		relatorioSimples.cabecalho();
		relatorioSimples.corpo(listaDeContas);
	}

}
