package br.com.padraoChainOfResponsibility3;

public class Conta {
	private String titular;
	private double saldo;

	public Conta(String titular, double saldo) {
		this.setTitular(titular);
		this.setSaldo(saldo);
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
