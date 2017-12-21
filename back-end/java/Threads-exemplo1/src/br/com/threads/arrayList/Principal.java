package br.com.threads.arrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		//ArrayList não é sincronizada por padrão!
		List<String> lista = Collections.synchronizedList(new ArrayList<String>());

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new TarefaAdicionar(lista, i));
			thread.start();
		}

		Thread.sleep(2000);

		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}

}
