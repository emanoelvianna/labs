%%

%{
  private int comment_count = 0;

  private BC bc;

  public Lexico(java.io.Reader r, BC bc) {
	this(r);
	this.bc = bc;
  }
%} 


%public
%class Lexico
%integer
%unicode
%line


%{

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
public static int MENOR = 271;
public static int MAIOR = 272;
public static int MAIORIGUAL = 273;
public static int IGUAL = 274;
public static int DIFERENTE = 275;
public static int WHILE = 276;
public static int FOR = 277;
public static int MENOSMENOS = 278;
public static int MAISMAIS = 279;
public static int RETORNO = 280;
public static int PRINT = 281;


/**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    Lexico scanner;
    if (argv.length == 0) {
      try {
          scanner = new Lexico( System.in );
          while ( !scanner.zzAtEOF )
	        System.out.println("token: "+scanner.yylex()+"\t<"+scanner.yytext()+">");
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }

    }
    else {
      for (int i = 0; i < argv.length; i++) {
        scanner = null;
        try {
          scanner = new Lexico( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF )
                System.out.println("token: "+scanner.yylex()+"\t<"+scanner.yytext()+">");
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


%}

DIGIT=		[0-9]
LETTER=		[a-zA-Z]

%%

if				{return IF;}
else				{return ELSE;} 
while 				{return WHILE;}
define				{return DEFINE;} 
auto				{return AUTO;} 
return				{return RETORNO;} 
print				{return PRINT;} 

not 				{return NOT;}
or 				{return OR;}
and 				{return AND;}

{LETTER}({LETTER}|{DIGIT})* 	{ return VARIAVEL; }
{LETTER}({LETTER}|{DIGIT})* 	{ return IDENTIFICADOR;}
([0-9]*\.)*[0-9]+ 		{ return NUMERO; }
\"[^\"]*\"  			{ return STRING; } 

"<" |
">" |
"(" |
")" |
"{" |
"}" |
"," |
";" |
"+" |
"-" |
"*" |
"/" |
"^" |
"="    				{ return yytext().charAt(0); } 

"<="                        	{return MENORIGUAL;}
">="                        	{return MAIORIGUAL;}
"=="				{return IGUAL;}
"!="				{return DIFERENTE;}
"++"				{return MAISMAIS;}
"--"				{return MENOSMENOS;}
 
. { System.out.println("Erro lexico: caracter invalido: <" + yytext() + ">"); }
