package br.com.negocio;

public class CalculaImpostoVinteOuQuinze implements CalculaImposto {

	@Override
	public double calculaSalarioComImposto(Funcionario funcionario) {
		if (funcionario.getSalario() > 3500)
			return funcionario.getSalario() * 0.8;
		return funcionario.getSalario() * 0.85;
	}

}
