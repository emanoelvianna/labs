package negocio;

public class Produto {
	private char prod_code;
	private Desconto discount_code;
	private String description;

	public Produto(char prod_code, Desconto discount_code, String description) {
		super();
		this.prod_code = prod_code;
		this.discount_code = discount_code;
		this.description = description;
	}

	public char getProd_code() {
		return prod_code;
	}

	public void setProd_code(char prod_code) {
		this.prod_code = prod_code;
	}

	public Desconto getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(Desconto discount_code) {
		this.discount_code = discount_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
