
public class Principal {
	public static void main(String[] args) {
		Imposto icms = new ICMS();
		Imposto iss = new ISS();
		Orcamento orcamento = new Orcamento(500.0);
		CalculadorDeImposto calculador = new CalculadorDeImposto();
		System.out.println(calculador.realizaCalculo(orcamento, icms));
		System.out.println(calculador.realizaCalculo(orcamento, iss));

	}
}
