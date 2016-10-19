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
	public static int STRING = 262;
	public static int DEFINE = 263;
	public static int AUTO = 264;
	public static int IF = 265;
	public static int ELSE = 266;
	public static int NOT = 267;
	public static int OR = 268;
	public static int AND = 269;
	public static int MENORIGUAL = 270;
	public static int MAIORIGUAL = 271;
	public static int IGUAL = 272;
	public static int DIFERENTE = 273;
	public static int WHILE = 274;
	public static int FOR = 275;
	public static int MENOSMENOS = 276;
	public static int MAISMAIS = 277;
	public static int RETORNO = 278;
	public static int PRINT = 279;


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
			Valor();
			AuxiliarImediato();
		} else {
			yyerror("esperado 'NUMERO'");
		}
	}

	private void AuxiliarImediato() {
		if (la == '+' || la == '-' || la == '*' || la == '/' || la == '^' || la == '%') {
			Operador();
			Imediato();
		} else {
			// produção vazia
		}
	}

	private void Atribuicao() {
		if (la == IDENTIFICADOR) {
			Valor();
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
		if (la == VARIAVEL || la == NUMERO) {
			Valor();
			AuxiliarExpressao();
		} else {
			yyerror("esperado 'VARIAVEL' ou 'NUMERO'");
		}
	}

	private void AuxiliarExpressao() {
		if (la == '+' || la == '-' || la == '*' || la == '/' || la == '^' || la == '%') {
			Operador();
			Expressao();
		} else {
			// produção vazia
		}
	}

	private void Funcao() {
		if (la == DEFINE) {
			check(DEFINE);
			if (la == IDENTIFICADOR) {
				Valor();
				if (la == '(') {
					check('(');
					Parametros();
					if (la == ')') {
						check(')');
						if (la == '{') {
							check('{');
							CorpoFuncao();
							if (la == '}') {
								check('}');
							} else {
								yyerror("esperado '}'");
							}
						} else {
							yyerror("esperado '{'");
						}
					} else {
						yyerror("esperado ')'");
					}
				} else {
					yyerror("esperado '('");
				}
			} else {
				yyerror("esperado 'IDENTIFICADOR'");
			}
		} else {
			yyerror("esperado 'DEFINE'");
		}
	}

	private void Parametros() {
		if (la == VARIAVEL || la == NUMERO || la == STRING) {
			Valor();
			AuxiliarParametros();
		} else {
			yyerror("esperado 'VARIAVEL'");
		}
	}

	private void AuxiliarParametros() {
		if (la == ',') {
			check(',');
			Parametros();
		} else {
			// produção vazia
		}
	}

	private void CorpoFuncao() {
		if (la == AUTO) {
			Declaracao();
		} else if (la == IF) {
			Condicional();
		} else if (la == WHILE || la == FOR) {
			Loop();
		} else if (la == RETORNO) {
			Retorno();
		} else if (la == PRINT) {
			Escrever();
		} else {
			ExpressaoCorpo();
		}
	}

	private void ExpressaoCorpo() {
		if (la == ';') {
			check(';');
		} else if (la == VARIAVEL || la == NUMERO) {
			Expressao();
		}
	}

	private void Escrever() {
		check(PRINT);
		AuxiliarEscrever();
	}

	private void AuxiliarEscrever() {
		if (la == VARIAVEL || la == NUMERO) {
			Valor();
		} else {
			Expressao();
		}
	}

	private void Retorno() {
		check(RETORNO);
		AuxiliarRetorno();
	}

	private void AuxiliarRetorno() {
		if (la == ';') {
			check(';');
		} else {
			Expressao();
		}
	}

	private void Loop() {
		if (la == WHILE) {
			check(WHILE);
			if (la == '(') {
				check('(');
				if (la == ')') {
					check(')');
					ExpressaoSimples();
					if (la == '{') {
						check('{');
						CorpoFuncao();
						if (la == '}') {
							check('}');
							// aqui rola deixar em branco?
						} else {
							yyerror("esperado '}'");
						}
					} else {
						yyerror("esperado '{'");
					}
				} else {
					yyerror("esperado ')'");
				}
			} else {
				yyerror("esperado '('");
			}
		} else if (la == FOR) {
			check(FOR);
			if (la == '(') {
				check('(');
				if (la == VARIAVEL) {
					Valor();
					if (la == '=') {
						check('=');
						if (la == NUMERO || la == VARIAVEL) {
							Valor();
							if (la == ';') {
								check(';');
								ExpressaoSimples();
								if (la == ';') {
									check(';');
									Mutavel();
									if (la == ')') {
										check(')');
										if (la == '{') {
											check('{');
											CorpoFuncao();
											if (la == '}') {
												check('}');
											} else {
												yyerror("esperado '}'");
											}
										} else {
											yyerror("esperado '{'");
										}
									} else {
										yyerror("esperado ')'");
									}
								} else {
									yyerror("esperado ';'");
								}
							} else {
								yyerror("esperado ';'");
							}
						} else {
							yyerror("esperado 'NUMERO' ou 'VARIAVEL'");
						}
					} else {
						yyerror("esperado '='");
					}
				} else {
					yyerror("esperado 'VARIAVEL'");
				}
			} else {
				yyerror("esperado '('");
			}
		}
	}

	private void Mutavel() {
		if (la == VARIAVEL || la == NUMERO) {
			Valor();
			AuxiliarMutavel();
		} else {
			yyerror("esperado 'VARIAVEL' ou 'NUMERO'");
		}
	}

	private void AuxiliarMutavel() {
		if (la == MENOSMENOS) {
			check(MENOSMENOS);
		} else {
			check(MAISMAIS);
		}
	}

	private void Condicional() {
		check(IF);
		if (la == '(') {
			ExpressaoSimples();
		} else {
			yyerror("esperado '('");
		}
	}

	private void ExpressaoSimples() {
		if (la == VARIAVEL || la == NUMERO) {
			Valor();
			AuxiliarExpressaoSimples();
		} else if (la == NOT) {
			check(NOT);
			if (la == VARIAVEL || la == NUMERO) {
				Valor();
				AuxiliarExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL' ou 'NUMERO'");
			}
		} else {
			yyerror("esperado 'VARIAVEL', 'NUMERO' ou 'NOT'");
		}
	}

	private void AuxiliarExpressaoSimples() {
		if (la == OR) {
			check(OR);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == AND) {
			check(AND);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == MENORIGUAL) {
			check(MENORIGUAL);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == '<') {
			check('<');
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == MAIORIGUAL) {
			check(MAIORIGUAL);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == '>') {
			check('>');
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == IGUAL) {
			check(IGUAL);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == DIFERENTE) {
			check(DIFERENTE);
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else if (la == '+' || la == '-' || la == '*' || la == '/' || la == '^' || la == '%') {
			Operador();
			if (la == VARIAVEL) {
				Valor();
				ExpressaoSimples();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else {
			yyerror("esperado operadores logicos ou matematicos!");
		}
	}

	private void Operador() {
		if (la == '+') {
			check('+');
		} else if (la == '-') {
			check('-');
		} else if (la == '*') {
			check('*');
		} else if (la == '/') {
			check('/');
		} else if (la == '^') {
			check('^');
		} else if (la == '%') {
			check('%');
		}
	}

	private void AuxiliarCondicional() {
		if (la == ELSE) {
			check(ELSE);
			CorpoFuncao();
		} else if (la == ';') {
			check(';');
			Condicional();
		} else {
			// produção vazia
		}
	}

	private void Declaracao() {
		check(AUTO);
		if (la == VARIAVEL) {
			Valor();
			AuxiliarDeclaracao();
		} else {
			yyerror("esperado 'VARIAVEL'");
		}
	}

	private void AuxiliarDeclaracao() {
		if (la == ',') {
			check(',');
			if (la == VARIAVEL) {
				Valor();
				Declaracao();
			} else {
				yyerror("esperado 'VARIAVEL'");
			}
		} else {
			// produção vazia
		}
	}

	private void Valor() {
		if (la == VARIAVEL) {
			check(VARIAVEL);
		} else if (la == IDENTIFICADOR) {
			check(IDENTIFICADOR);
		} else if (la == NUMERO) {
			check(NUMERO);
		} else if (la == STRING) {
			check(STRING);
		} else {
			yyerror("esperado 'NUMERO' ou 'IDENTIFICADOR' ou 'NUMERO' ou 'STRING'");
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
