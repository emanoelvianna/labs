package br.com.negocio;

public class ContaCorrenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public ContaCorrenteException() {
		super();
	}

	public ContaCorrenteException(String mensagem) {
		super(mensagem);
	}

	public ContaCorrenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
