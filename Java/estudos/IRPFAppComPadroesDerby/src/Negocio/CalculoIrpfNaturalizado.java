package Negocio;

public class CalculoIrpfNaturalizado {
	private Contribuinte contribuinte;

	public CalculoIrpfNaturalizado(Contribuinte umContribuinte) {
		contribuinte = umContribuinte;
	}

	public double baseDeCalculo() {
		double baseDeCalculo = contribuinte.getTotRend() - contribuinte.getContrPrev();
		double txDescDep = 0.0;
		if (contribuinte.getIdade() < 70) {
			if (contribuinte.getNroDep() > 0 && contribuinte.getNroDep() <= 2) {
				txDescDep = 0.015;
			} else if (contribuinte.getNroDep() > 2 && contribuinte.getNroDep() <= 5) {
				txDescDep = 0.025;
			} else if (contribuinte.getNroDep() > 5) {
				txDescDep = 0.030;
			}
		} else {
			if (contribuinte.getNroDep() > 0 && contribuinte.getNroDep() <= 2) {
				txDescDep = 0.020;
			} else if (contribuinte.getNroDep() > 2 && contribuinte.getNroDep() <= 5) {
				txDescDep = 0.025;
			} else if (contribuinte.getNroDep() > 5) {
				txDescDep = 0.035;
			}
		}
		double descDep = baseDeCalculo * txDescDep;
		baseDeCalculo = baseDeCalculo - descDep;
		return baseDeCalculo;
	}

	public double fatorEspecifico() {
		double contrPrev = contribuinte.getContrPrev();
		if (contrPrev < 5000) {
			return 1.0;
		} else if (contrPrev >= 5000 && contrPrev < 10000) {
			return 1.1;
		} else {
			return 1.2;
		}
	}

	public double imposto_A_Pagar(double baseDeCalculo, double fatorEspecifico) {
		double impPagar;
		baseDeCalculo *= fatorEspecifico;
		if (baseDeCalculo <= 15000.0) {
			impPagar = 0;
		} else if ((baseDeCalculo >= 15000.0) && (baseDeCalculo < 25000.0)) {
			impPagar = (baseDeCalculo - 12000.0) * 0.17;
		} else {
			double p1 = (24999.0 - 15000.0) * 0.17;
			double p2 = (baseDeCalculo - 24999.0) * 0.30;
			impPagar = p1 + p2;
		}
		return impPagar;
	}
}