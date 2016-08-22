package br.com.compostosEDecorador;

public class ImpostoMuitoAlto extends Imposto{

	public ImpostoMuitoAlto(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public ImpostoMuitoAlto() {
	}
	
	@Override
	public double calcula(Orcamento orcamento) {
		return orcamento.getValor() * 2.0;
	}

	
}
