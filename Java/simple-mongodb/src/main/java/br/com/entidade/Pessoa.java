package br.com.entidade;

public class Pessoa {
	private String id;
	private String primeiroNome;
	private String segundoNome;
	private Sexo sexo;
	private Telefone telefone;
	private Endereco endereco;

	public Pessoa(String primeiroNome, String segundoNome, Sexo sexo, Telefone telefone, Endereco endereco) {
		this.setPrimeiroNome(primeiroNome);
		this.setSegundoNome(segundoNome);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getSegundoNome() {
		return segundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		 return "Person{" +
	                "id='" + id + '\'' +
	                ", primeiroNome='" + primeiroNome + '\'' +
	                ", segundoNome='" + segundoNome + '\'' +
	                ", sexo='" + sexo + '\'' +
	                ", telefone=" + telefone +
	                ", endereco=" + endereco +
	                '}';
	}
}
