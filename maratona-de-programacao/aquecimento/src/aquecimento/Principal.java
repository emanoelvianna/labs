package aquecimento;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		// lendo as entradas do teclado
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int numero = scanner.nextInt();
			if (numero == 42)
				break;
			System.out.println("-- saida: " + numero);
		}
	}
}
