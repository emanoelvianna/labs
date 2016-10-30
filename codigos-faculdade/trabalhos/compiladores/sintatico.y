%{
	import java.io.*;
%}

%right '='
%left IGUAL DIFERENTE MAIORIGUAL MENORIGUAL
%left AND OR
%left ','
%left '+' '-' INCREMENTO DECREMENTO
%left '*' '/' '%'
%left '^'

%token IDENTIFICADOR, VARIAVEL, NUMERO, STRING
%token IGUAL, DIFERENTE, MAIORIGUAL, MENORIGUAL
%token INCREMENTO, DECREMENTO, AND, OR, NOT
%token DEFINE, AUTO, RETORNO
%token PRINT
%token IF, ELSE, FOR, WHILE

%%

BC	: Imediato BC
  	| Atribuicao BC
       	| Funcao BC
       	| Controle BC
       	| Loop BC
       	|
	;

Imediato 	: ValorImedianto AuxiliarImediato
		;

AuxiliarImediato     : Operador Imediato
		     | 	
		     ;	
		 
ValorImedianto	: VARIAVEL
	 	| IDENTIFICADOR
	  	| NUMERO
		| TRUE
		| FALSE
		;

Atribuicao : Valor '=' Expressao
	   ;	

Expressao  : ValorExpressao AuxiliarExpressao
	   | '(' ValorExpressao AuxiliarExpressao ')'
	   ;

AuxiliarExpressao  : Operador Expressao
		   | 
		   ;
		      
ValorExpressao	: VARIAVEL
	 	| IDENTIFICADOR
	        | NUMERO		     
 		;

Funcao	: DEFINE IDENTIFICADOR '(' Parametros ')' '{' CorpoFuncao '}'
	;

Parametros	: VARIAVEL AuxiliarParametros
	  	;	

AuxiliarParametros	: ',' Parametros
		       	|
			;

CorpoFuncao	: ExpressaoCorpo CorpoFuncao
		| Declaracao CorpoFuncao
		| Condicional CorpoFuncao
		| Loop CorpoFuncao
		| Retorna CorpoFuncao 
		| Escrever CorpoFuncao
		|
		;

Declaracao	: AUTO VARIAVEL AuxiliarDeclaracao ';'
		;

AuxiliarDeclaracao	: ',' VARIAVEL AuxiliarDeclaracao
		       	|
			;

Condicional	: '(' ExpressaoSimples ')' '{' CorpoFuncao '}' AuxiliarCondicional
		;

AuxiliarCondicional	:ELSE CorpoFuncao
			| ';' Condicional
			|
			;

ExpressaoSimples	: ValorExpressaoSimples AuxiliarExpressaoSimples
		     	| NOT ValorExpressaoSimples AuxiliarExpressaoSimples
			;

AuxiliarExpressaoSimples	: OR ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| AND ValorExpressaoSimples AuxiliarExpressaoSimples
                             	| MENORIGUAL ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| MENOR ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| MAIOR ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| MAIORIGUAL ValorExpressaoSimples AuxiliarExpressaoSimples
                             	| IGUAL ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| DIFERENTE ValorExpressaoSimples AuxiliarExpressaoSimples
			     	| Operador ValorExpressaoSimples AuxiliarExpressaoSimples
			     	|
				;
			     
ValorExpressaoSimples	: VARIAVEL
	 		| NUMERO			  
			| TRUE
			| FALSE
			;
	 
Loop	: WHILE '(' ExpressaoSimples ')' '{' CorpoFuncao '}'
	| FOR '(' VARIAVEL '=' Valor ';' ExpressaoSimples ';' Mutavel ')' '{' CorpoFuncao '}'
	;

Mutavel	: NUMERO AuxiliarMutavel
	;

AuxiliarMutavel	: DECREMENTO
		| INCREMENTO
		;

Retorna	: RETORNA AuxiliarRetorna
	;

AuxiliarRetorna	: ';'
		| Expressao ';'
		;

Escrever	: PRINT AuxiliarEscrever
		;

AuxiliarEscrever	: STRING
			| NUMERO
		    	| Expressao
			;

ExpressaoCorpo		: Expressao ';'
		   	;

Valor 	 : VARIAVEL
	 | IDENTIFICADOR
	 | NUMERO
	 ;

Operador    : '+'
	    | '-'
            | '*'
	    | '/'
	    | '^'
            | '%' 
	    | '='
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
      System.out.print(">");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Tchau");
    }
  }


