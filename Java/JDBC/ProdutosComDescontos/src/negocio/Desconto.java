package negocio;

import java.util.List;

public class Desconto {
	private char discount_code;
	private double rate;
	private List<Produto> produtos;

	public Desconto(char discount_code, double rate) {
		super();
		this.setDiscount_code(discount_code);
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public char getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(char discount_code) {
		this.discount_code = discount_code;
	}
}
