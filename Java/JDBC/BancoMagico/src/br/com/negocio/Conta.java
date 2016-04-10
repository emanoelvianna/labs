package br.com.negocio;

public interface Conta {
	void sacar(double valor) throws ContaCorrenteException;

	void depositar(double valor);

	double getSaldo();
}
