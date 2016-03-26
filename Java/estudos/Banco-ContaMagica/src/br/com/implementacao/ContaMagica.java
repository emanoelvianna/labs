package br.com.implementacao;

import java.math.BigDecimal;

import br.com.enumeracao.Categorias;
import br.com.interfaces.Conta;

public class ContaMagica implements Conta {
	private Cliente cliente;
	private Categorias categoria;
	private BigDecimal saldo;

	public ContaMagica(Cliente cliente) {
		this.cliente = cliente;
		this.categoria = Categorias.Silver;
		this.saldo = new BigDecimal("0");
	}

	@Override
	public String getNomeCliente() {
		return cliente.getNome();
	}

	@Override
	public BigDecimal getSaldo() {
		return this.saldo;
	}

	@Override
	public Categorias getStatus() {
		return this.categoria;
	}

	@Override
	public void deposito(BigDecimal valor) {
		upgrade();
		if (this.categoria.equals(Categorias.Platinum)) {
			BigDecimal porcentagem = valor.multiply(new BigDecimal("0.025"));
			valor = valor.add(porcentagem);
			this.saldo = this.saldo.add(valor);

		} else if (this.categoria.equals(Categorias.Gold)) {
			BigDecimal porcentagem = valor.multiply(new BigDecimal("0.01"));
			valor = valor.add(porcentagem);
			this.saldo = this.saldo.add(valor);
			upgrade();

		} else {
			this.saldo = this.saldo.add(valor);
			upgrade();

		}
	}

	private void upgrade() {
		BigDecimal platinum = new BigDecimal("200.000");
		BigDecimal gold = new BigDecimal("50.000");

		if (this.categoria.equals(Categorias.Silver)) {
			if (this.saldo.compareTo(platinum) == 1 || this.saldo.compareTo(platinum) == 0) { // >=
				this.categoria = Categorias.Platinum;
				
			} else {
				this.categoria = Categorias.Gold;
				
			}
		} else if (this.categoria.equals(Categorias.Gold)) {
			if (this.saldo.compareTo(platinum) == 1 || this.saldo.compareTo(platinum) == 0) { // >=
				this.categoria = Categorias.Platinum;
			}
		}
	}

	@Override
	public void retirada(BigDecimal valor) {
		if (this.saldo.compareTo(valor) == 1 || this.saldo.compareTo(valor) == 0) {
			this.saldo = this.saldo.subtract(valor);
			retrocederUpgrade();
		}
	}

	private void retrocederUpgrade() {
		BigDecimal cairParaGold = new BigDecimal("100.000");
		BigDecimal cairParaSilver = new BigDecimal("25.000");

		if (this.categoria.equals(Categorias.Platinum)) {
			if (this.saldo.compareTo(cairParaGold) == -1) {
				this.categoria = Categorias.Gold;
			}
		} else if (this.categoria.equals(Categorias.Gold)) {
			if (this.saldo.compareTo(cairParaSilver) == -1) {
				this.categoria = Categorias.Silver;
			}
		}
	}

}