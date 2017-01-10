package br.com.threads.banheiro;

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

		convidado1.start();
		convidado2.start();
	}
}
