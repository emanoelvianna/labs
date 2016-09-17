package br.com.principal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class GramaticaGp1 {
	
	/*
	 Gramatica modificada:
	 
	 G -> GRAPH nome '{' NodeList ContList '}'
	 
	 NodeList -> Nome NomeList RestoNodeList
	 RestoNodeList -> 'Auxiliar' NodeList | // producao vazia
	 
	 NomeList -> Nome ';' RestoNomeList
	 RestoNomeList -> 'Auxiliar' NomeList | // producao vazia
	 
	 Nome -> IDENT | STRING 
	 
	 ConList -> Conexao RestoConexao
	 RestoConexao -> 'Auxiliar' ConList | // producao vazia
	 
	 Conexao -> Nome '--' Nome ';'
	
	 */

	private static final int BASE_TOKEN_NUM = 301;
	public static final int STRING = 302;
	public static final int IDENT = 303;
	public static final int TRACO = 304;
	public static final int GRAPH = 305;
	private static final int AUXILIAR = 306;

	public static final String tokenList[] = { "STRING", "IDENT", "TRACO", "GRAPH" };

	private Lexico lexico;
	public ParserVal yylval;
	private static int la;
	private boolean debug = true;

	public GramaticaGp1(Reader r) {
		lexico = new Lexico(r);
	}

	public void G() {
		check(GRAPH);
		Nome();
		if (la == '{') {
			check('{');
			NodeList();
			ConList();
		} else {
			yyerror("esperado '{'");
		}
		if (la == '}') {
			check('}');
		} else {
			yyerror("esperado '}'");
		}
	}

	private void NodeList() {
		Nome();
		NomeList();
		ContinuaNodeList();
	}

	// Auxiliar
	private void ContinuaNodeList() {
		if (la == AUXILIAR) {
			check(AUXILIAR);
			NodeList();
		} else {
			// produção vazia
		}
	}

	private void NomeList() {
		Nome();
		if (la == ';') {
			check(';');
			ContinuaNomeList();
		} else {
			yyerror("esperado ';'");
		}
	}

	// Auxiliar
	private void ContinuaNomeList() {
		if (la == AUXILIAR) {
			check(AUXILIAR);
			NomeList();
		} else {
			// produção vazia
		}
	}

	private void Nome() {
		if (la == STRING) {
			check(STRING);
		} else if (la == IDENT) {
			check(IDENT);
		}
	}

	private void ConList() {
		Conexao();
		ContinuaConList();
	}
	
	private void ContinuaConList() {
		if (la == AUXILIAR) {
			check(AUXILIAR);
			ConList();
		} else {
			// produção vazia
		}
	}

	private void Conexao() {
		Nome();
		if (la == '-') {
			check('-');
			if(la == '-'){
				check('-'); // dois traços
				Nome();
				if (la == ';') {
					check(';');
				} else {
					yyerror("esperado ';'");
				}
			} else {
				yyerror("esperado '--'");
			}
		} else {
			yyerror("esperado '--'");
		}
	}

	private void check(int expected) {
		if (la == expected)
			la = this.yylex();
		else {
			// erro: esperado token "expected" veio token "la"
			// System.out.printf("Erro: esperado %d, veio %d\n", expected, la)
			String expStr, laStr;

			expStr = ((expected < BASE_TOKEN_NUM) ? "" + (char) expected : tokenList[expected - BASE_TOKEN_NUM]);

			laStr = ((la < BASE_TOKEN_NUM) ? new Character((char) la).toString() : tokenList[la - BASE_TOKEN_NUM]);

			yyerror("esperado token : " + expStr + " na entrada: " + laStr);
		}
	}

	/* metodo de acesso ao Scanner gerado pelo JFLEX */
	private int yylex() {
		int retVal = -1;
		try {
			yylval = new ParserVal(0); // zera o valor do token
			retVal = lexico.yylex(); // le a entrada do arquivo e retorna um
										// token
		} catch (IOException e) {
			System.err.println("IO Error:" + e);
		}
		return retVal; // retorna o token para o Parser
	}

	/* metodo de manipulacao de erros de sintaxe */
	private void yyerror(String error) {
		System.err.println("Erro: " + error);
		System.err.println("Entrada rejeitada");
		System.out.println("\n\nFalhou!!!");
		System.exit(1);

	}

	public static void main(String[] args) {
		GramaticaGp1 parser = null;
		try {
			if (args.length == 0)
				parser = new GramaticaGp1(new InputStreamReader(System.in));
			else
				parser = new GramaticaGp1(new java.io.FileReader(args[0]));

			la = parser.yylex();

			parser.G();

			if (la == Lexico.YYEOF)
				System.out.println("\n\nSucesso!");
			else
				System.out.println("\n\nFalhou - esperado EOF.");

		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + args[0] + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + args[0] + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}

	}
}
