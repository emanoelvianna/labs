package br.com.negocio;

/**
 * Exemplo do uso do padrão Singleton
 * 
 * @author Emanoel
 */

public class RelatorioFabricaCarro {
	private int quantidadeFiat;
	private int quantidadeFord;
	private int quantidadeVolks;

	public static RelatorioFabricaCarro instancia;

	// construtor privado para criar o padrão Singleton
	private RelatorioFabricaCarro() {
	};

	public static RelatorioFabricaCarro getInstancia() {
		if (instancia == null)
			return new RelatorioFabricaCarro();
		else
			return instancia;
	}

	public void fabricarFiat() {
		quantidadeFiat++;
	}

	public void fabricarFord() {
		quantidadeFord++;
	}

	public void fabricarVolks() {
		quantidadeVolks++;
	}

	public String relatorioDeQuantidade() {
		return  " ******************** " + 
				"\n quantidade de carros Fiat: " + quantidadeFiat + 
				"\n quantidade de carros Ford: " + quantidadeFord + 
				"\n quantidade de carros Volks: " + quantidadeVolks + 
			    "\n *********************";
	}
}
