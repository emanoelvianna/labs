package br.com.model.vo;

public class User extends Pessoa {

	private String login;
	private String senha;

	public User(String primeiroNome, String segundoNome, int idade, Sexo sexo, String login, String senha) {
		super(primeiroNome, segundoNome, idade, sexo);
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
