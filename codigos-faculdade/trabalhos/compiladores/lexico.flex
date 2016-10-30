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

if				{return Parser.IF;}
else				{return Parser.ELSE;} 
while 				{return Parser.WHILE;}
for 				{return Parser.FOR;}
define				{return Parser.DEFINE;} 
auto				{return Parser.AUTO;} 
return				{return Parser.RETORNO;} 
print				{return Parser.PRINT;} 

not 				{return Parser.NOT;}
or 				{return Parser.OR;}
and 				{return Parser.AND;}

true 				{return Parser.TRUE;}
false 				{return Parser.FALSE;}

{LETTER}({LETTER}|{DIGIT})* 	{ return Parser.IDENTIFICADOR;}
{LETTER}({LETTER}|{DIGIT})* 	{ return Parser.VARIAVEL;}
{NUMERO}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.NUMERO; }
\"[^\"]*\"  			{ return Parser.STRING; } 

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

"<="                        	{return Parser.MENORIGUAL;}
">="                        	{return Parser.MAIORIGUAL;}
"=="				{return Parser.IGUAL;}
"!="				{return Parser.DIFERENTE;}
"++"				{return Parser.INCREMENTO;}
"--"				{return Parser.DECREMENTO;}

{NL}   { return Parser.NL; } /* nova linha */ 
[ \t]+ { }
 
. { System.err.println("Erro (linha " + yyline + "): unexpected character '" + yytext() + "'"); return YYEOF; }
