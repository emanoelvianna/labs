package principal;

import persistencia.InicializadorBancoDadosDataSource;

public class Principal {
	public static void main(String[] args) throws Exception {
		// Utilize o seguinte código somente a primeira vez que executar
		System.out.println("Criando BD...");
		InicializadorBancoDadosDataSource.criarBd();
		System.out.println("BD criada com sucesso!");
		
		
		
	};
}
