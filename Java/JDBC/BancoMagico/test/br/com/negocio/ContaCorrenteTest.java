package br.com.negocio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContaCorrenteTest {

	Conta conta;

	@Before
	public void initialize() {
		conta = new ContaCorrente();
	}

	@Test
	public void deve_depositar_valor() {
		conta.depositar(100);
		assertEquals(100, conta.getSaldo(), 0.001);
	}

	@Test
	public void deve_somar_o_saldo_atual_com_o_valor() {
		conta.depositar(100);
		conta.depositar(100);
		assertEquals(200, conta.getSaldo(), 0.001);
	}

	@Test(expected = NumberFormatException.class)
	public void deve_gerar_excecao_quando_passado_valor_invalido() {
		conta.depositar(-1);
	}

	@Test
	public void deve_sacar_valor() throws ContaCorrenteException {
		conta.depositar(10);
		conta.sacar(5);
		assertEquals(5, conta.getSaldo(), 0.001);
	}

	@Test(expected = ContaCorrenteException.class)
	public void deve_gerar_excecao_quando_saldo_insuficiente() throws ContaCorrenteException {
		conta.depositar(100);
		conta.sacar(110);

	}

	@Test
	public void deve_retornar_saldo() {
		conta.depositar(100);
		assertEquals(100, conta.getSaldo(), 0.001);
	}
}
