package br.com.principal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class BC {

	private static final int BASE_TOKEN_NUM = 301;
	public static int VARIAVEL = 257;
	public static int IDENTIFICADOR = 258;
	public static int NUMERO = 259;
	public static int OPERADOR = 260;
	public static int ATRIBUICAO = 261;

	public static final String tokenList[] = { "STRING", "IDENT", "TRACO", "GRAPH" };

	private Lexico lexico;
	public ParserVal yylval;
	private static int la;
	private boolean debug = true;

	public BC(Reader r) {
		lexico = new Lexico(r, this);
	}

	/* Teste simples */
	public void Principal() {
		Imediato();
	}

	private void Imediato() {
		if (la == NUMERO) {
			check(NUMERO);
			AuxiliarImediato();
		} else {
			yyerror("esperado 'NUMERO'");
		}
	}

	private void AuxiliarImediato() {
		if (la == '+') {
			check('+');
			Imediato();
		} else if (la == '-') {
			check('-');
			Imediato();
		} else if (la == '*') {
			check('*');
			Imediato();
		} else if (la == '/') {
			check('/');
			Imediato();
		} else if (la == '^') {
			check('^');
			Imediato();
		} else {
			// produção vazia
		}
	}

	private void Atribuicao() {
		if (la == IDENTIFICADOR) {
			check(IDENTIFICADOR);
			if (la == '=') {
				check('=');
				Expressao();
			} else {
				yyerror("esperado '='");
			}
		} else {
			yyerror("esperado 'IDENTIFICADOR'");
		}
	}

	private void Expressao() {
		if (la == NUMERO) {
			check(NUMERO);
			AuxiliarExpressao();
		} else if (la == VARIAVEL) {
			check(VARIAVEL);
			AuxiliarExpressao();
		} else {
			yyerror("esperado 'NUMERO' ou 'VARIAVEL'");
		}
	}

	private void AuxiliarExpressao() {
		if (la == '+') {
			check('+');
			Expressao();
		} else if (la == '-') {
			check('-');
			Expressao();
		} else if (la == '*') {
			check('*');
			Expressao();
		} else if (la == '/') {
			check('/');
			Expressao();
		} else if (la == '^') {
			check('^');
			Expressao();
		} else {
			// produção vazia
		}
	}

	private void check(int expected) {
		if (la == expected)
			la = this.yylex();
		else {
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
		System.out.println("-- BC --");
		BC parser = null;
		try {
			if (args.length == 0)
				parser = new BC(new InputStreamReader(System.in));
			else
				parser = new BC(new java.io.FileReader(args[0]));

			la = parser.yylex();

			/* Rodando as produções! */
			parser.Principal();

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
