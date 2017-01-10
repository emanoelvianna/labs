package br.com.threads.banheiro2;

public class TarefaNumero1 implements Runnable {

	private Banheiro banheiro = new Banheiro();

	public TarefaNumero1(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.tarefaNumero1();
	}

}
