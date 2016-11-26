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

%token NL, HELP, SAVE
%token IDENTIFICADOR, FLOAT, BOOL, NUMERO, LITERAL, PRINT, IF, ELSE, DEFINE
%token WHILE,TRUE, FALSE, IF, ELSE
%token IGUAL, DIFERENTE, MAIORIGUAL, MENORIGUAL 
%token RETURN, DEFINE
%token AND, OR, FOR
%token BREAK

%token <dval> NUMERO
%token <sval> IDENTIFICADOR
%token <sval> VARIAVEL

%type <obj> bc, line, exp, atribuicao, help, save, funcao

%right '='
%left OR
%left AND
%left  '>' '<' IGUAL DIFERENTE MAIORIGUAL MENORIGUAL
%left '+' '-'
%left '*' '/' '%'
%left NEG          
%right '^'       

%%

bc:   	/* empty string */ {$$=null;}
	| bc line { System.out.print("\n> "); }
	| error NL { System.out.println("entrada ignorada"); }
	;

line:    NL		{ if (interactive) System.out.print("\n> "); $$ = null; }
	| exp NL	{ 
				if ($1 != null) {
					System.out.println("\n Expressão = " + $1); 			  		
					System.out.print(" Resultado: " + ((INodo) $$).avalia()); 
					$$=$1;
			 	}
			  	if (interactive){ 
					System.out.print("\n "); 
				}
			}
  	| atribuicao NL { 
				if ($1 != null) {		  		
					System.out.print("\n Resultado: " + ((INodo) $$).avalia()); 
					$$=$1;
			 	}
			  	if (interactive){ 
					System.out.print("\n "); 
				}
			}
	| funcao NL 	{ 	
				System.out.print("percebi que é uma funcao ");
			}
	| help NL
	| save NL	
	;

atribuicao:	IDENTIFICADOR '=' exp	{ $$ = new NodoNT(TipoOperacao.ATRIB, $1, (INodo)$3); }	
		;

funcao:		DEFINE IDENTIFICADOR'(' ')'{ System.out.println("_start:"); }
        	'{' '}'
         	; 

exp:	NUMERO				{ $$ = new NodoTDouble($1); }
       | IDENTIFICADOR			{ $$ = new NodoID($1);}
       | exp '+' exp			{ $$ = new NodoNT(TipoOperacao.ADD,(INodo)$1,(INodo)$3); }
       | exp '-' exp			{ $$ = new NodoNT(TipoOperacao.SUB,(INodo)$1,(INodo)$3); }
       | exp '*' exp			{ $$ = new NodoNT(TipoOperacao.MULL,(INodo)$1,(INodo)$3); }
       | exp '/' exp			{ $$ = new NodoNT(TipoOperacao.DIV,(INodo)$1,(INodo)$3); }
       | exp '<' exp			{ $$ = new NodoNT(TipoOperacao.LESS,(INodo)$1,(INodo)$3); }
       | '-' exp  %prec NEG		{ $$ = new NodoNT(TipoOperacao.UMINUS,(INodo)$2,null); }
       | exp '^' exp			{ $$ = new NodoNT(TipoOperacao.POW,(INodo)$1,(INodo)$3); }
       | '(' exp ')'			{ $$ = $2; }
       ;

help:	HELP				
	{ System.out.println(
		"O que é possível fazer:\n"+
		"- Operações imediatas. Exemplo: 2^3+5\n" +
 		"- Operações de atribuições. Exemplo: x = 2^b+5\n"+
		"- Declaração de função. Exemplo: define d (n) { return (2*n); } \n"
	); 
	}
	;

save: SAVE 
	{ System.out.println(
		"Gravando o conteúdo atual da tabela de funções.\n"
	); 
	}
	;

%%

  public static HashMap<String, ResultValue> memory = new HashMap<>();
  private BC bc;
  private TabSimb ts = new TabSimb();


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
    System.out.println("-- BC calculadora --");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    } else {
      // interactive mode
      System.out.println("[para ajudar #help]");		
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

