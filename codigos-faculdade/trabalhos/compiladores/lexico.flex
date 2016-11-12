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

%%
%byaccj
%class BC

%{
	private Parser yyparser;

	public BC(java.io.Reader r, Parser yyparser) {
		this(r);
		this.yyparser = yyparser;
	}

	public int getLine() {
		return yyline + 1;
	}
%}

NUMERO=		([0-9]*\.)*[0-9]+
DIGIT=		[0-9]
LETTER=		[a-zA-Z]
NL  = \n | \r | \r\n

%%

{LETTER}({LETTER}|{DIGIT})* 	{ return Parser.IDENTIFICADOR;}
{LETTER}({LETTER}|{DIGIT})* 	{ return Parser.VARIAVEL;}
{NUMERO}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.NUMERO; }
STRING	{ return Parser.STRING; }

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
"%" |
"^" |
"="    				{ return (int) yycharat(0); } 

{NL}   { return Parser.NL; } /* nova linha */ 
[ \t]+ { }
 
. { System.err.println("Erro (linha " + yyline + "): unexpected character '" + yytext() + "'"); return YYEOF; }
