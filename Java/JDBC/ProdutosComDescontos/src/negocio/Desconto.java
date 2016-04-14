package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Desconto {
	private char discount_code;
	private double rate;
	private List<Produto> produtos;

	public Desconto(char discount_code, double rate, Produto produto) {
		this.setDiscount_code(discount_code);
		this.rate = rate;
		produtos = new ArrayList<>();
		produtos.add(produto);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public char getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(char discount_code) {
		this.discount_code = discount_code;
	}

	public List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}
	
	public boolean addProduto(Produto produto) {
		for (Produto p : produtos) {
			if (p.getProd_code() == produto.getProd_code()) {
				return true;
			}
		}
		return produtos.add(produto);
	}
}
