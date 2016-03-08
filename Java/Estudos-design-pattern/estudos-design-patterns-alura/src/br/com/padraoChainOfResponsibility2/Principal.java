package br.com.padraoChainOfResponsibility2;

public class Principal {
	public static void main(String[] args) {
		Banco bancos = new BancoA();
		bancos.setProximo(new BancoB());
		bancos.setProximo(new BancoC());
		bancos.setProximo(new BancoD());

		try {
			bancos.efetuarPagamento(IDBancos.bancoC);
			bancos.efetuarPagamento(IDBancos.bancoD);
			bancos.efetuarPagamento(IDBancos.bancoA);
			bancos.efetuarPagamento(IDBancos.bancoB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
