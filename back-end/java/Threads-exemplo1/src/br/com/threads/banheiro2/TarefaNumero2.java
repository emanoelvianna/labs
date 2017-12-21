package br.com.threads.banheiro2;

public class TarefaNumero2 implements Runnable {

	private Banheiro banheiro = new Banheiro();

	public TarefaNumero2(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.tarefaNumero2();
	}
}
