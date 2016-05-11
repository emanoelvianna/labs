package br.com.ui;

import br.com.negocio.RelatorioFabricaCarro;

public class Principal {
	public static void main(String[] args) {
		
		RelatorioFabricaCarro fabricaCarro = RelatorioFabricaCarro.getInstancia();
		fabricaCarro.fabricarFiat();
		fabricaCarro.fabricarFord();
		fabricaCarro.fabricarVolks();
		fabricaCarro.fabricarVolks();
		
		System.out.println(fabricaCarro.relatorioDeQuantidade());

	}
}
