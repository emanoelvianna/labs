package br.com.ui;

import br.com.negocio.Cargo;
import br.com.negocio.Funcionario;

public class Principal {
	public static void main(String[] args) {
		Funcionario funcionario1 = new Funcionario(Cargo.Desenvolvedor, 2000);
		System.out.println(funcionario1.calculaImposto());

		Funcionario funcionario2 = new Funcionario(Cargo.DBA, 3000);
		System.out.println(funcionario2.calculaImposto());

	}
}
