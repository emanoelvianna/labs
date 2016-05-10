package br.com.negocio;

public class CalculaImpostoQuinzeOuDez implements CalculaImposto{
	@Override
	public double calculaSalarioComImposto(Funcionario funcionario) {
		if (funcionario.getSalario() > 2000)
			return funcionario.getSalario() * 0.85;
		return funcionario.getSalario() * 0.9;
	}
}
