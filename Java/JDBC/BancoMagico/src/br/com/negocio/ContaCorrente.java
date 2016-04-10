package br.com.negocio;

public class ContaCorrente implements Conta {

	private double saldo;

	@Override
	public void sacar(double valor) throws ContaCorrenteException {
		if (valor < 0)
			throw new NumberFormatException();
		if (valor > this.saldo)
			throw new ContaCorrenteException("Saldo insuficiente!");
		this.saldo = this.saldo - valor;
	}

	@Override
	public void depositar(double valor) {
		if (valor < 0)
			throw new NumberFormatException();
		this.saldo = this.saldo + valor;
	}

	public double getSaldo() {
		return saldo;
	}

}
