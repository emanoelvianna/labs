package br.com.teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.imposto.Irpf;

public class IrpfTest {

	private Irpf irpf;

	@Before
	public void setUp() {
		irpf = new Irpf("Test", "12345-55");
		irpf.setIdade(50);
		irpf.setNroDep(2);
		irpf.setTotRendimentos(1000);
	}

	@Test
	public void deve_construir_a_classe_corretamente_com_os_parametros() {
		String nome = "Test";
		String cpf = "1234566";
		irpf = new Irpf(nome, cpf);
		Assert.assertEquals(nome, irpf.getNome());
		Assert.assertEquals(cpf, irpf.getCpf());
	}

	@Test
	public void para_contribuintes_com_menos_de_65_anos_e_2_dependentes() {
		if (irpf.getIdade() < 65 && irpf.getNroDep() <= 2) {
			
		}
	}
}
