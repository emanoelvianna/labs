package negocio;

public class DAOException extends Exception {
	public DAOException() {
		super();
	}

	public DAOException(String mensagem) {
		super(mensagem);
	}

	public DAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
