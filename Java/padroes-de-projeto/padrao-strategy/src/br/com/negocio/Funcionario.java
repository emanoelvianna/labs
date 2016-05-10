package br.com.negocio;

public class Funcionario {

	private double salario;
	private Cargo cargo;
	private CalculaImposto imposto;

	public Funcionario(Cargo cargo, double salario) {
		
		this.salario = salario;
		
		switch (cargo) {
		case Desenvolvedor:
			imposto = new CalculaImpostoQuinzeOuDez();
			this.setCargo(cargo);
			break;
		case Gerente:
			imposto = new CalculaImpostoVinteOuQuinze();
			this.setCargo(cargo);
			break;
		case DBA:
			imposto = new CalculaImpostoQuinzeOuDez();
			this.setCargo(cargo);
			break;
		default:
			throw new RuntimeException("Cargo não existe!");
		}
	}

	public double calculaImposto() {
		return imposto.calculaSalarioComImposto(this);
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
