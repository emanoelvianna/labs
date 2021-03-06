%%

%byaccj

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

NL  = \n | \r | \r\n

%%

{NL}   {yyline++;}
[ \t]+ { }

"+" |
"-" |
"*" | 
"/" | 
"%" | 
">" |
"<" |
"=" |
"!" |
";" |
"(" | 
")" |
"{" |
"}" |
"," |
"\[" | 
"\]"    { return (int) yycharat(0); }

"=="		{ return Parser.IGUAL; }
"!="		{ return Parser.DIFERENTE; }
">="		{ return Parser.MAIORIGUAL; }
"<="		{ return Parser.MENORIGUAL; }
"&&"		{ return Parser.AND; }
"||"		{ return Parser.OR; }
 
DEFINE   	{ return Parser.DEFINE; }
void   		{ return Parser.VOID; }
main   		{ return Parser.MAIN; }

for   		{ return Parser.FOR;}
float  		{ return Parser.FLOAT;   }
bool   		{ return Parser.BOOL; }
print   	{ return Parser.PRINT; }
while   	{ return Parser.WHILE; }
if   		{ return Parser.IF; }
else   		{ return Parser.ELSE; }
true   		{ return Parser.TRUE; }
false   	{ return Parser.FALSE; }
break 		{return Parser.BREAK; }
return		{ return Parser.RETURN; }

[-]?[0-9]+\.[0-9]+  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.NUMERO; }

[a-zA-Z]+([a-zA-Z0-9]+)? { yyparser.yylval = new ParserVal(yytext()); return Parser.IDENTIFICADOR; }

\"[^\n]+\" { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1)); return Parser.LITERAL; }

[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
