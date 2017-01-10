package br.com.threads.banheiro;

public class Banheiro {

	public void tarefaNumero1() {

		System.out.println("batendo na porta");

		synchronized (this) {// somente um convidado utiliza o banheiro
			String convidado = Thread.currentThread().getName();
			System.out.println(convidado + " entrando no banheiro");
			System.out.println(convidado + " fazendo coisa rapida!");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
			System.out.println(convidado + " fazendo coisa demorada!");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(convidado + " saindo do banheiro");
		}
	}

}
