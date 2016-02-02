package br.com.entidade;

public class Telefone {
	private String telefoneResidencial;
	private String telefoneMovel;

	public Telefone(String telefoneResidencial, String telefoneMovel) {
		super();
		this.setTelefoneResidencial(telefoneResidencial);
		this.setTelefoneMovel(telefoneMovel);
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}
	
	@Override
	public String toString() {
		return "Telefone{" +
				", telefoneResidencial='" + telefoneResidencial + '\'' +
		        ", telefoneMovel='" + telefoneMovel + '\'' +
		        '}';
	}

}
