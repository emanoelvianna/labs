package Negocio;

public class CalculoIrpfNaturalizadoAdapter implements CalculoIrpf {

	private CalculoIrpfNaturalizado calculoIrpfNaturalizado;

	@Override
	public double calculaImposto(Contribuinte c) {
		calculoIrpfNaturalizado = new CalculoIrpfNaturalizado(c);
		
		return calculoIrpfNaturalizado.imposto_A_Pagar(calculoIrpfNaturalizado.baseDeCalculo(),
				calculoIrpfNaturalizado.fatorEspecifico());
	}

}
