package br.com.interfaces;

import br.com.enums.Status;
import br.com.excecoes.NaoAutorizado;

public interface Conta {

	void deposito(double valor) throws NaoAutorizado;

	void retirada(double valor) throws NaoAutorizado;

	double getSaldo() throws NaoAutorizado;

	void analisar(Status status);
	
	void emprestimo(double valor, int parcelas) throws NaoAutorizado;
}
