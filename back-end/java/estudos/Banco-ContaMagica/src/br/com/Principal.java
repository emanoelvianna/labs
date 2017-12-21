package br.com;

import java.math.BigDecimal;

import br.com.implementacao.Cliente;
import br.com.implementacao.ContaMagica;

public class Principal {
	public static void main(String[] args) {

		Cliente cliente = new Cliente("Pedro");
		ContaMagica conta = new ContaMagica(cliente);
		
		conta.deposito(new BigDecimal("200000"));
		System.out.println(conta.getStatus());
		System.out.println(conta.getSaldo());

	}
}
