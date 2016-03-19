package br.com;

import br.com.enums.Status;
import br.com.excecoes.NaoAutorizado;
import br.com.implementacao.Poupanca;
import br.com.implementacao.Titular;

public class Principal {

	public static void main(String[] args) throws NaoAutorizado {
		Titular pedro = new Titular("Pedro", 23, "5511");
		Poupanca poupanca = new Poupanca(pedro);

		System.out.println(poupanca.getStatus());

		// poupanca.emprestimo(200, 2);

		Titular titular = poupanca.getTitular();
		titular.setNome("Maria");
		System.out.println(poupanca.getTitular().getNome());

		// poupanca.deposito(100);
		poupanca.analisar(Status.CONTA_COMUM);
		poupanca.deposito(100);
		System.out.println(poupanca.getSaldo());

		poupanca.retirada(50);
		System.out.println(poupanca.getSaldo());

		System.out.println(poupanca.isEmprestimo());
		poupanca.analisar(Status.CONTA_PREMIUM);
		poupanca.emprestimo(10, 2);

	}
}
