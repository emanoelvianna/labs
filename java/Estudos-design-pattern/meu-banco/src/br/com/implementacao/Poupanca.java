package br.com.implementacao;

import br.com.inteface.Conta;

public class Poupanca implements Conta {

	private double saldo;

	public Poupanca(double valor) {
		this.saldo += valor;
	}

	@Override
	public void depositar(double valor) {
		this.saldo = valor;
	}

	@Override
	public void sacar(double valor) {
		this.saldo = this.saldo - valor;
	}

	@Override
	public void transferir(Conta conta, double valor) {
		this.saldo = this.saldo - valor;
		conta.depositar(valor);
	}

	@Override
	public double getSaldo() {
		return this.saldo;
	}

}
