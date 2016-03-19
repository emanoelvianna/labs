package br.com.excecoes;

public class NaoAutorizado extends Exception {

	private static final long serialVersionUID = 1L;

	public NaoAutorizado(String msg) {
		super(msg);
	}
	
}
