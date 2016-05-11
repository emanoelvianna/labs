package Negocio;

public class CalculoIrpfNaturalizadoAdapter extends CalculoIrpfNaturalizado implements CalculoIrpf {

	public CalculoIrpfNaturalizadoAdapter(Contribuinte umContribuinte) {
		super(umContribuinte);
	}

	@Override
	public double calculaImposto(Contribuinte c) {
		return imposto_A_Pagar(baseDeCalculo(), fatorEspecifico());
	}

}
