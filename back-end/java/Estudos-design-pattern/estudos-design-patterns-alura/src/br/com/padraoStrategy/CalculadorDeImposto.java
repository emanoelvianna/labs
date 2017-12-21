package br.com.padraoStrategy;

public class CalculadorDeImposto {

	public double realizaCalculo(Orcamento orcamento, Imposto imposto) {
		double icms = imposto.calcula(orcamento);
		return icms;
	}
}
