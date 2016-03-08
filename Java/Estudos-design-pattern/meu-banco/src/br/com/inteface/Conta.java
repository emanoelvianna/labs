package br.com.inteface;

public interface Conta {
	void depositar(double valor);

	void sacar(double valor);

	void transferir(Conta conta, double valor);

	double getSaldo();
}
