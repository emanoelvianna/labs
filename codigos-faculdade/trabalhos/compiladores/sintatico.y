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
  import java.util.ArrayList;
  import java.util.Stack;
%}

%token NL, HELP, SAVE
%token IDENTIFICADOR, FLOAT, BOOL, NUMERO, LITERAL, PRINT, DEFINE
%token WHILE, TRUE, FALSE, IF, ELSE
%token IGUAL, DIFERENTE, MAIORIGUAL, MENORIGUAL 
%token RETURN, DEFINE
%token AND, OR, FOR
%token BREAK

%token <dval> NUMERO
%token <sval> IDENTIFICADOR
%token <sval> VARIAVEL
%type <sval>  LITERAL

%type <obj> bc, line, exp, atribuicao, help, save, declaracaofuncao, chamadaFuncao, cmd

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
	| bc line { currEscopo = ""; System.out.print("\n> "); } 	
	| error NL { System.out.println("entrada ignorada"); System.out.print("\n> "); }
	;

line:    NL		{ $$ = null; }
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
	| declaracaofuncao NL
	| chamadaFuncao NL
	| cmd NL
	| help NL
	| save NL	
	;

atribuicao:	IDENTIFICADOR '=' exp	{ $$ = new NodoNT(TipoOperacao.ATRIB, $1, (INodo)$3); }	
		;

declaracaofuncao:	IDENTIFICADOR'(' ')'	
        		'{' cmd '}' 	{  	
						TS_entry nodo = ts.pesquisa($1);
                      				if (nodo != null) {
                         				yyerror("funcao " + $1 + " já declarada");
						}
                       				else {
							ts.insert(new TS_entry($1, Tp_DEFINE, currEscopo, ClasseID.NomeFuncao));
						}						
						currEscopo = $1; currClass = ClasseID.CampoDefine; 
					}		
         	; 

chamadaFuncao:	IDENTIFICADOR'(' ')'    {  	
						TS_entry nodo = ts.pesquisa($1);
                      				if (nodo != null) {
                         				System.out.print(" executando a funcao... ");
						}
                       				else {
							System.out.print(" funcao não declarada ");
						}
					}
		; 

cmd :  exp 				{ $$ = $1; }
    |  IDENTIFICADOR '=' exp ';'        { $$ = new NodoNT(TipoOperacao.ATRIB, $1, (INodo)$3); }
    |  WHILE '(' exp ')' cmd       	{ $$ = new NodoNT(TipoOperacao.WHILE,(INodo)$3, (INodo)$5, null); }
    |  IF '(' exp ')' cmd               { $$ = new NodoNT(TipoOperacao.IF,(INodo)$3, (INodo)$5, null); }	
    | '{' cmd '}'                 	{ $$ = $2; }
    ;

exp:	NUMERO				{ $$ = new NodoTDouble($1); }
       | IDENTIFICADOR			{ $$ = new NodoID($1);}
       | exp '+' exp			{ $$ = new NodoNT(TipoOperacao.ADD,(INodo)$1,(INodo)$3); System.out.println("exp '+' exp");}
       | exp '-' exp			{ $$ = new NodoNT(TipoOperacao.SUB,(INodo)$1,(INodo)$3); }
       | exp '*' exp			{ $$ = new NodoNT(TipoOperacao.MULL,(INodo)$1,(INodo)$3); }
       | exp '/' exp			{ $$ = new NodoNT(TipoOperacao.DIV,(INodo)$1,(INodo)$3); }
       | exp '<' exp			{ $$ = new NodoNT(TipoOperacao.LESS,(INodo)$1,(INodo)$3); }
       | exp '>' exp			{ $$ = new NodoNT(TipoOperacao.MORE,(INodo)$1,(INodo)$3); }	
       | '-' exp  %prec NEG		{ $$ = new NodoNT(TipoOperacao.UMINUS,(INodo)$2,null); }
       | exp '^' exp			{ $$ = new NodoNT(TipoOperacao.POW,(INodo)$1,(INodo)$3); }
       | '(' exp ')'			{ $$ = $2; }
       ;

help:	HELP				
	{ System.out.println(
		"O que é possível fazer:\n"+
		"- Operações imediatas. Exemplo: 2^3+5\n" +
 		"- Operações de atribuições. Exemplo: x = 2^b+5\n"+
		"- Declaração de função. Exemplo: define d (n) { return (2*n); } \n\n" +
		"--- \n" +		
		"- É possível também executar os exemplos por linha de comando. \n"
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

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();
  private Stack<Integer> pRotSel = new Stack<Integer>();
  private int proxRot = 1;

  private String currEscopo;
  private ClasseID currClass;


  public static TS_entry Tp_BOOL = new TS_entry("bool", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_FLOAT = new TS_entry("float", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_DEFINE = new TS_entry("define", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_ARRAY = new TS_entry("array", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, "", ClasseID.TipoBase);

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

