package br.com.interfaces;

import java.math.BigDecimal;

import br.com.enumeracao.Categorias;

public interface Conta {
	public String getNomeCliente();

	public BigDecimal getSaldo();

	public Categorias getStatus();

	public void deposito(BigDecimal valor);

	public void retirada(BigDecimal valor);

}
