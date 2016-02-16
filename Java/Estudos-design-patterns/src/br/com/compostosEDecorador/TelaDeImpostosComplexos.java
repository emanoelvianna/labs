package br.com.compostosEDecorador;

public class TelaDeImpostosComplexos {
	public static void main(String[] args) {
		Imposto imposto = new ISS();
		Orcamento orcamento = new Orcamento(800);
		double valor = imposto.calcula(orcamento);
		System.out.println(valor);
	}
}
