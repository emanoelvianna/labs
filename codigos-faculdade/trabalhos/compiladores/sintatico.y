/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Trabalho Compiladores - 2016			                           *
 * BC - Calculadora Unix			                           *
 * 						                           *
 * Alunos:					                           *
 * Emanoel A Vianna Fabiano			                           *
 * Jessica Arruda Ferreira de Santana		                           *
 * Matheus Cosme Britzke			                           *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


%{
  import java.io.*;
  import java.util.HashMap;
%}

%token NL          /* nova linha  */
%token <dval> NUMERO
%token <sval> IDENTIFICADOR
%token <sval> VARIAVEL

%type <obj> exp, line, bc

%nonassoc '<'
%left '-' '+'
%left '*', '/'
%left NEG          /* negation--unary minus */
%right '^'         /* expoente */

%%

bc:   	  /* empty string */ {$$=null;}
	| bc line { 
			if ($2 != null) {
	          		System.out.print("Resultado: " + ((INodo) $2).avalia() +"\n> "); 
				$$=$2;
		 	}
			else {
	                  	System.out.print("\n> "); 
				$$=null;
			}
		 }
       | error NL { System.out.println("entrada ignorada"); }
       ;

line:    NL     { if (interactive) System.out.print("\n> "); $$ = null; }
       | exp NL	{ 
			$$ = $1;
		  	System.out.println("\n expressão = " + $1); 
                   	if (interactive){ 
				System.out.print("\n "); 
			}
		}
       ;

exp:     NUMERO				{ $$ = new NodoTDouble($1); }
       | IDENTIFICADOR			{ $$ = new NodoID($1); }
       | exp '+' exp			{ $$ = new NodoNT(TipoOperacao.ADD,(INodo)$1,(INodo)$3); }
       | exp '-' exp			{ $$ = new NodoNT(TipoOperacao.SUB,(INodo)$1,(INodo)$3); }
       | exp '*' exp			{ $$ = new NodoNT(TipoOperacao.MULL,(INodo)$1,(INodo)$3); }
       | exp '/' exp			{ $$ = new NodoNT(TipoOperacao.DIV,(INodo)$1,(INodo)$3); }
       | exp '<' exp			{ $$ = new NodoNT(TipoOperacao.LESS,(INodo)$1,(INodo)$3); }
       | '-' exp  %prec NEG		{ $$ = new NodoNT(TipoOperacao.UMINUS,(INodo)$2,null); }
       | exp '^' exp			{ $$ = new NodoNT(TipoOperacao.POW,(INodo)$1,(INodo)$3); }
       | '(' exp ')'			{ $$ = $2; }
       ;

%%

  public static HashMap<String, ResultValue> memory = new HashMap<>();
  private BC bc;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = bc.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }

  public Parser(Reader r) {
    bc = new BC(r, this);
  }

  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("-- BC simples calculadora --");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[para ajudar CTRL-H]");		
      System.out.println("[para sair CTRL-D]");
      System.out.print("> ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("É hora de dar tchau..");
    }
  }


