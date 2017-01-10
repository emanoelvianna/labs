package br.com.threads.banheiro2;

public class TarefaLimpeza implements Runnable {

	private Banheiro banheiro = new Banheiro();

	public TarefaLimpeza(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		while (true) {
			this.banheiro.limparBanheiro();
			try {
				Thread.sleep(15000);// limpando cada 15s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
