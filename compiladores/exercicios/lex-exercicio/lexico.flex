%%


%public
%class Lexico
%integer
%unicode
%line


%{

public static int IDENT		= 257;
public static int TRACO		= 258;
public static int STRING	= 259;
public static int GRAPH 	= 260; 


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
WHITESPACE=	[ \t]
LineTerminator = \r|\n|\r\n    
COMENTARIO = \/

%%
GRAPH				{ return GRAPH; }
{LETTER}({LETTER}|{DIGIT})* 	{ return IDENT; }
\"[^\"]*\"  			{ return STRING; } 

";" |
"{" |
"}" |
"--"                        	{ return TRACO;}
{COMENTARIO}{COMENTARIO}	{ }
{WHITESPACE}*               	{ }
{LineTerminator}		{ }
.          {System.out.println(yyline+1 + ": caracter invalido: "+yytext());}
