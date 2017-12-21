package br.com.threads.banheiro2;

public class Banheiro {

	private boolean estaLimpo = false;

	public void tarefaNumero1() {

		System.out.println("batendo na porta");

		synchronized (this) {// somente um convidado utiliza o banheiro
			String convidado = Thread.currentThread().getName();
			System.out.println(convidado + " entrando no banheiro");

			while (estaLimpo == false) {
				esperandoLimparBanheiro(convidado);
			}

			System.out.println(convidado + " fazendo coisa rapida!");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(convidado + " saindo do banheiro");
		}
	}

	public void tarefaNumero2() {

		System.out.println("batendo na porta");

		synchronized (this) {

			String convidado = Thread.currentThread().getName();
			System.out.println(convidado + " entrando no banheiro");

			while (estaLimpo == false) {
				esperandoLimparBanheiro(convidado);
			}

			System.out.println(convidado + " fazendo coisa demorada!");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(convidado + " saindo do banheiro");
		}
	}

	public void limparBanheiro() {
		synchronized (this) {
			if (estaLimpo == true) {
				System.out.println("banheiro limpo!");
				return;
			} else {
				System.out.println("Entrando no banheiro");
				System.out.println("limpando o banheiro!");
				try {
					estaLimpo = true; // limpando banheiro
					Thread.sleep(10000);
					this.notifyAll(); // notificado que o banheiro esta limpo!
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("saindo do banheiro");
			}
		}
	}

	private void esperandoLimparBanheiro(String convidado) {
		System.out.println(convidado + " eca, banheiro esta sujo!");
		try {
			System.out.println(convidado + " esperando aqui fora..");
			this.wait(); // colocando a thread em espera
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
