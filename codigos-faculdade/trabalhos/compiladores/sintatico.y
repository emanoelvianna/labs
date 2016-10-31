/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Trabalho Compiladores - 2016			                           *
 * BC - Calculadora Unix			                           *
 * 						                           *
 * Alunos:					                           *
 * Emanoel A Vianna Fabiano - Matricula: 13202030                          *
 * Emanoel A Vianna Fabiano - Matricula: 13202030                          *
 * Emanoel A Vianna Fabiano - Matricula: 13202030                          *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
	import java.io.*;
%}

%token NL
%token <dval> NUMERO
%token <sval> IDENTIFICADOR 
%token <sval> VARIAVEL
%token STRING
%token IGUAL, DIFERENTE, MAIORIGUAL, MENORIGUAL
%token INCREMENTO, DECREMENTO, AND, OR, NOT, TRUE, FALSE
%token DEFINE, AUTO, RETORNO
%token PRINT
%token IF, ELSE, FOR, WHILE

%type <dval> exp

%nonassoc '<'
%left '-' '+'
%left '*', '/'
%right '^'

%%

bc:   	/* empty string */
       	| bc line
       	;

line:	NL		{ if (interactive) System.out.print("\n> "); $$ = null; }
       	| exp NL  	{ $$ = $1;
		   	System.out.println("\n= " + $1); 
                   	if (interactive) System.out.print("\n>: "); }
       	| imediato NL
	;
      
imediato	: NL      	{ if (interactive) System.out.print("> "); }
       		| exp NL  	{ System.out.println(" = " + $1); 
                   		if (interactive) System.out.print("> "); }
       		;

atribuicao	: IDENTIFICADOR '=' exp NL	{ $$ = $1}
		;
      
exp		: NUMERO            	{ $$ = $1; }
		| VARIAVEL            	{ $$ = $1; }
       		| exp '+' exp   	{ $$ = $1 + $3; }
       		| exp '-' exp       	{ $$ = $1 - $3; }
       		| exp '*' exp        	{ $$ = $1 * $3; }
       		| exp '/' exp        	{ $$ = $1 / $3; }
       		| '-' exp 	    	{ $$ = -$2; }
       		| exp '^' exp        	{ $$ = Math.pow($1, $3); }
       		| '(' exp ')'        	{ $$ = $2; }
       		;

%%


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
    System.out.println("-- BC simple calculator - version: 2016 --");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Sair CTRL-D]");
      System.out.print("> ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Tchau");
    }
  }


