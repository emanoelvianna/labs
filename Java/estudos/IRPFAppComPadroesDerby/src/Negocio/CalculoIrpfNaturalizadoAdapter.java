package Negocio;

public class CalculoIrpfNaturalizadoAdapter extends CalculoIrpfNaturalizado implements CalculoIrpf {

	private CalculoIrpfNaturalizado calculoIrpfNaturalizado;

	public CalculoIrpfNaturalizadoAdapter(Contribuinte umContribuinte) {
		super(umContribuinte);
	}

	@Override
	public double calculaImposto(Contribuinte c) {
		return 10;
	}

}
