package br.com.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.implementacao.Cliente;
import br.com.implementacao.ContaMagica;

@RunWith(Parameterized.class)
public class ParameterizedContaMagicaTest {
	private BigDecimal param;
	private BigDecimal result;
	private Cliente cliente;

	public ParameterizedContaMagicaTest(BigDecimal param, BigDecimal result) {
		this.param = param;
		this.result = result;
	}

	@Before
	public void setUp() {
		cliente = new Cliente("Test1");
	}

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] { 
			{ new BigDecimal("50000"), new BigDecimal("50500.00") },
			{ new BigDecimal("100000"), new BigDecimal("101000.00") }
			
		});
	}

	@Test
	public void test_o_calculo_para_a_valorizacao_de_deposito_deve_estar_correto() {
		ContaMagica contaMagica = new ContaMagica(cliente);

		contaMagica.deposito(param);
		assertEquals(result, contaMagica.getSaldo());
	}
}
