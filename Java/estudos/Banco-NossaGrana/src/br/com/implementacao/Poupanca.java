package br.com.implementacao;

import br.com.enums.Status;
import br.com.excecoes.NaoAutorizado;
import br.com.interfaces.Conta;

public class Poupanca implements Conta {

	private Titular titular;
	private double saldo;
	private Status status;
	private boolean emprestimo;

	public Poupanca(Titular titular) throws NaoAutorizado {
		if (titular.equals(null))
			throw new NaoAutorizado("Titular invalido!");
		this.titular = titular;
		this.saldo = 0;
		status = Status.CONTA_NOVA;
	}

	@Override
	public void deposito(double valor) throws NaoAutorizado {
		if (status.equals(Status.CONTA_NOVA))
			throw new NaoAutorizado("Conta nova, não autorizado!");
		this.saldo = this.saldo + valor;
	}

	@Override
	public void retirada(double valor) throws NaoAutorizado {
		if (status.equals(Status.CONTA_NOVA))
			throw new NaoAutorizado("Conta nova, não autorizado!");

		if (valor >= this.saldo) {
			throw new NaoAutorizado("Saldo não suficiente!");
		}
		this.saldo = this.saldo - valor;
	}

	@Override
	public void analisar(Status status) {
		this.status = status;
	}

	@Override
	public void emprestimo(double valor, int parcelas) throws NaoAutorizado {
		if (this.status != Status.CONTA_PREMIUM)
			throw new NaoAutorizado("Não autorizado, conta precisa ser tipo CONTA_PREMIUM!");
		else if(parcelas > 24 || isEmprestimo() == true)
			throw new NaoAutorizado("Número de parcelas acima do permitido ou já existe emprestimo ativo");
		this.emprestimo = true;
	}

	@Override
	public double getSaldo() throws NaoAutorizado {
		if (status.equals(Status.CONTA_NOVA))
			throw new NaoAutorizado("Conta nova, não autorizado!");
		return this.saldo;
	}

	public Status getStatus() {
		return this.status;
	}

	public Titular getTitular() {
		return new Titular(titular.getNome(), titular.getIdade(), titular.getCpf());
	}

	public boolean isEmprestimo() {
		return emprestimo;
	}

}
