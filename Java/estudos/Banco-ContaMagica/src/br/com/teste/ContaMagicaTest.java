package br.com.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.*;

import br.com.enumeracao.Categorias;
import br.com.implementacao.Cliente;
import br.com.implementacao.ContaMagica;

public class ContaMagicaTest {
	private Cliente cliente;
	private BigDecimal saldoZero;

	@Before
	public void setUp() {
		cliente = new Cliente("Test1");
		saldoZero = new BigDecimal("0");
	}

	@Test
	public void test_cliente_deve_ser_setado_corretamente_e_seu_saldo_deve_ser_zero() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		assertEquals(cliente.getNome(), contaMagica.getNomeCliente());
		assertTrue(contaMagica.getSaldo().equals(saldoZero));
	}

	@Test
	public void test_o_primeiro_status_a_ser_setado_deve_ser_Silver() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		assertEquals(Categorias.Silver, contaMagica.getStatus());
	}

	@Test
	public void test_clientes_Silver_nao_tem_seus_depositos_valorizados() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		BigDecimal valor = new BigDecimal("100");
		contaMagica.deposito(valor);
		assertEquals(valor, contaMagica.getSaldo());
	}

	@Test
	public void test_cliente_com_status_de_Silver_deve_fazer_upgrade_para_Gold() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		BigDecimal valor = new BigDecimal("50000");
		contaMagica.deposito(valor);
		assertEquals(Categorias.Gold, contaMagica.getStatus());
	}

	@Test
	public void test_cliente_com_status_de_Gold_deve_fazer_upgrade_para_Platinum() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		BigDecimal valor = new BigDecimal("200000");
		contaMagica.deposito(valor);
		assertEquals(Categorias.Platinum, contaMagica.getStatus());
	}

	@Test
	public void test_cliente_com_status_de_Platinum_nao_deve_fazer_mais_upgrade() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		BigDecimal deposito1 = new BigDecimal("200000");
		contaMagica.deposito(deposito1);
		assertEquals(Categorias.Platinum, contaMagica.getStatus());

		BigDecimal deposito2 = new BigDecimal("10000");
		contaMagica.deposito(deposito2);
		assertEquals(Categorias.Platinum, contaMagica.getStatus());
	}

	@Test
	public void test_deve_retroceder_upgrade_para_Silver() {
		ContaMagica contaMagica = new ContaMagica(cliente);
		
		BigDecimal deposito1 = new BigDecimal("250000");
		contaMagica.deposito(deposito1);
		
		assertEquals(Categorias.Platinum, contaMagica.getStatus());
		
		BigDecimal retirada = new BigDecimal("250000");
		contaMagica.retirada(retirada);
		
		assertEquals(Categorias.Gold, contaMagica.getStatus());
		
		BigDecimal retirada2 = new BigDecimal("6250.000");
		contaMagica.retirada(retirada2);
		System.out.println(contaMagica.getStatus());
		
		assertEquals(Categorias.Silver, contaMagica.getStatus());
	}

}
