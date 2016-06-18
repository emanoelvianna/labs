package br.com.model.vo;

public class Pessoa {
	private String primeiroNome;
	private String segundoNome;
	private int idade;
	private Sexo sexo;

	public Pessoa(String primeiroNome, String segundoNome, int idade, Sexo sexo) {
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.idade = idade;
		this.sexo = sexo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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

}
