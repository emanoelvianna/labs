package br.com.threads.banheiro2;

public class Principal {
	public static void main(String[] args) {
		/**
		 * Representando uma casa com somente um banheiro, ou seja, o objetivo é
		 * mostrar a concorrencia para sua utilização.
		 **/
		System.out.println("-- inicio --");
		Banheiro banheiro = new Banheiro();

		/**
		 * Uma boa analogia é pensar que o banheiro é a memoria e as tarefas
		 * precisam acessar a memoria para realizar alguma operação com os dados
		 **/
		Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "Pedro");
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Maria");
		Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpaza");

		convidado1.start();
		convidado2.start();
		// informando que a limpeza é um provedor de serviço para outras threads
		limpeza.setDaemon(true);
		// informando a prioriade da limpeza referente aos outros convidados
		limpeza.setPriority(Thread.MAX_PRIORITY); // ou limpeza.setPriority(10);
		limpeza.start();
	}
}
