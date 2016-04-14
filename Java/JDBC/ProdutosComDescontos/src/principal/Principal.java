package principal;

import negocio.Desconto;
import persistencia.DescontoDao;
import persistencia.DescontoDaoBean;
import persistencia.InicializadorBancoDadosDataSource;

public class Principal {
	public static void main(String[] args) throws Exception {
		// Utilize o seguinte código somente a primeira vez que executar
		System.out.println("Criando BD...");
		InicializadorBancoDadosDataSource.criarBd();
		System.out.println("BD criada com sucesso!");

		DescontoDao desconto = new DescontoDaoBean();

		System.out.println("Inserir desconto...");
		desconto.InserirUmNovoObjeto(new Desconto('1', 1.0));
		System.out.println("Sucesso!");

		
		System.out.println("Buscando produto: ");
		Desconto descontoBuscado = desconto.buscaObjetoPorChavePrimaria('1');
		System.out.println(descontoBuscado.getDiscount_code());
		
		
	};
}
