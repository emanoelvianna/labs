/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

public class CalculoIrpfSingleton {

	private static CalculoIrpfSingleton instancia;

	private CalculoIrpfSingleton() {
	}

	public static CalculoIrpfSingleton getCalculoIrpfSingleton() {
		if (instancia == null)
			return new CalculoIrpfSingleton();
		else
			return instancia;
	}

	public CalculoIrpf createInstance(TipoCalculo t) {
		switch (t) {
		case SIMPLIFICADO:
			return new CalculoIrpfSimplificado();
		case COMPLETO:
			return new CalculoIrpfCompleto();
		case ESTRANGEIRO:
			return new CalculoIrpfNaturalizadoAdapter(null);
		}
		return null;
	}
}
