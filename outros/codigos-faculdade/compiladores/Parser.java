//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 14 "sintatico.y"
  import java.io.*;
  import java.util.HashMap;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short HELP=258;
public final static short SAVE=259;
public final static short IDENTIFICADOR=260;
public final static short FLOAT=261;
public final static short BOOL=262;
public final static short NUMERO=263;
public final static short LITERAL=264;
public final static short PRINT=265;
public final static short DEFINE=266;
public final static short WHILE=267;
public final static short TRUE=268;
public final static short FALSE=269;
public final static short IF=270;
public final static short ELSE=271;
public final static short IGUAL=272;
public final static short DIFERENTE=273;
public final static short MAIORIGUAL=274;
public final static short MENORIGUAL=275;
public final static short RETURN=276;
public final static short AND=277;
public final static short OR=278;
public final static short FOR=279;
public final static short BREAK=280;
public final static short VARIAVEL=281;
public final static short NEG=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    1,    1,    1,    1,    1,    1,    1,
    1,    3,    6,    7,    8,    8,    8,    8,    8,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    4,    5,
};
final static short yylen[] = {                            2,
    0,    2,    2,    1,    2,    2,    2,    2,    2,    2,
    2,    3,    6,    3,    1,    4,    5,    5,    3,    1,
    1,    3,    3,    3,    3,    3,    3,    2,    3,    3,
    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    3,    4,   31,   32,    0,   20,    0,    0,
    0,    0,    0,    2,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   21,    0,    0,    0,    0,
    0,    5,    0,    0,    0,    0,    0,    0,    0,    6,
   10,   11,    7,    8,    9,    0,    0,    0,    0,   30,
    0,   19,    0,    0,    0,    0,    0,    0,    0,   16,
    0,    0,    0,    0,    0,   17,   18,   13,
};
final static short yydgoto[] = {                          2,
   14,   30,   16,   17,   18,   19,   20,   21,
};
final static short yysindex[] = {                      -253,
 -248,  -40,    0,    0,    0,    0,  -34,    0,  -18,  -15,
  -32,  -32,  -38,    0,   67, -231, -227, -225, -224, -223,
 -220,  -32,   -2,  -32,  -32,    0,  -54,  103,  -11,  147,
  -81,    0,  -32,  -32,  -32,  -32,  -32,  -32,  -32,    0,
    0,    0,    0,    0,    0,  117,  -71,  111,  140,    0,
  -32,    0,  156,  156,  -27,  -27,  -54,  -54,  -54,    0,
  -38,  -38,  -38,  117,  -68,    0,    0,    0,
};
final static short yyrindex[] = {                         1,
    0,    0,    0,    0,    0,    0,   75,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -31,    0,   53, -121,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -195, -193,    0,    0,    0,
    0,    0,  -17,   30,   18,   43,  -24,    6,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  255,    0,    0,    0,    0,    0,   78,
};
final static int YYTABLESIZE=332;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         12,
    1,   12,    1,   15,   11,   23,   11,   12,    3,   28,
   28,   28,   11,   28,   37,   28,   24,   24,   24,   38,
   24,   24,   24,   27,   25,   40,   22,   28,   28,   41,
   28,   42,   43,   44,   24,   24,   45,   24,   47,   39,
    1,   27,   27,   52,   27,    1,   25,   25,   25,   51,
   25,   61,   25,   29,   29,   29,   68,   29,   22,   29,
   22,   12,   22,   14,   25,   25,   39,   25,    0,    0,
   26,   29,   29,    0,   29,    0,   22,   22,    0,   22,
    0,    0,   13,   23,   13,   23,    0,   23,   26,   26,
   31,   26,    0,   28,   21,   21,    0,   21,    0,   21,
   24,   23,   23,    0,   23,    0,    0,   27,   37,   35,
    0,   36,   21,   38,   21,    0,   21,   21,    0,   21,
    0,   21,    0,    1,    0,    0,   34,    0,   33,    0,
   25,    0,    0,    0,   21,   15,   21,   29,   65,   66,
   67,    0,   22,   50,   37,   35,   21,   36,    0,   38,
    0,   62,   37,   35,   26,   36,    0,   38,   37,   35,
   39,   36,   34,   38,   33,    0,    0,   23,   21,    0,
   34,    0,   33,    0,    0,   60,   34,   21,   33,    0,
   63,   37,   35,    0,   36,    0,   38,    0,   37,   35,
    0,   36,    0,   38,    0,    0,   39,   37,   35,   34,
   36,   33,   38,    0,   39,    0,   34,    0,   33,    0,
   39,    0,    0,    0,    0,    0,    4,    5,    6,    7,
    0,   29,    8,    0,    8,   28,    9,   26,    9,   10,
    8,   10,   24,   39,    0,    0,    0,    0,    0,   27,
   39,    0,    0,    0,    0,    0,    0,    0,    0,   39,
    0,    0,    0,    0,    0,    0,   15,    1,    1,    1,
    1,    0,   25,    1,    0,   27,   28,    1,    0,   29,
    1,    0,    0,    0,   22,    0,   46,    0,   48,   49,
    0,    0,    0,    0,    0,    0,   26,   53,   54,   55,
   56,   57,   58,   59,    0,    0,    0,    0,    0,   23,
    0,    0,    0,    0,    0,   64,    0,    0,    0,   21,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   32,    0,    0,    0,    0,    0,    0,
    0,   21,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   40,  256,  125,   45,   40,   45,   40,  257,   41,
   42,   43,   45,   45,   42,   47,   41,   42,   43,   47,
   45,   40,   47,   41,   40,  257,   61,   59,   60,  257,
   62,  257,  257,  257,   59,   60,  257,   62,   41,   94,
   40,   59,   60,  125,   62,   45,   41,   42,   43,   61,
   45,  123,   47,   41,   42,   43,  125,   45,   41,   47,
   43,  257,   45,  257,   59,   60,   94,   62,   -1,   -1,
   41,   59,   60,   -1,   62,   -1,   59,   60,   -1,   62,
   -1,   -1,  123,   41,  123,   43,   -1,   45,   59,   60,
   13,   62,   -1,  125,   42,   43,   -1,   45,   -1,   47,
  125,   59,   60,   -1,   62,   -1,   -1,  125,   42,   43,
   -1,   45,   60,   47,   62,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,  123,   -1,   -1,   60,   -1,   62,   -1,
  125,   -1,   -1,   -1,   60,  257,   62,  125,   61,   62,
   63,   -1,  125,   41,   42,   43,   94,   45,   -1,   47,
   -1,   41,   42,   43,  125,   45,   -1,   47,   42,   43,
   94,   45,   60,   47,   62,   -1,   -1,  125,   94,   -1,
   60,   -1,   62,   -1,   -1,   59,   60,  125,   62,   -1,
   41,   42,   43,   -1,   45,   -1,   47,   -1,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   94,   42,   43,   60,
   45,   62,   47,   -1,   94,   -1,   60,   -1,   62,   -1,
   94,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
   -1,  260,  263,   -1,  263,  257,  267,  260,  267,  270,
  263,  270,  257,   94,   -1,   -1,   -1,   -1,   -1,  257,
   94,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   94,
   -1,   -1,   -1,   -1,   -1,   -1,    2,  257,  258,  259,
  260,   -1,  257,  263,   -1,   11,   12,  267,   -1,  257,
  270,   -1,   -1,   -1,  257,   -1,   22,   -1,   24,   25,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   33,   34,   35,
   36,   37,   38,   39,   -1,   -1,   -1,   -1,   -1,  257,
   -1,   -1,   -1,   -1,   -1,   51,   -1,   -1,   -1,  257,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",null,
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'^'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"NL","HELP","SAVE","IDENTIFICADOR",
"FLOAT","BOOL","NUMERO","LITERAL","PRINT","DEFINE","WHILE","TRUE","FALSE","IF",
"ELSE","IGUAL","DIFERENTE","MAIORIGUAL","MENORIGUAL","RETURN","AND","OR","FOR",
"BREAK","VARIAVEL","NEG",
};
final static String yyrule[] = {
"$accept : bc",
"bc :",
"bc : bc line",
"bc : error NL",
"line : NL",
"line : exp NL",
"line : atribuicao NL",
"line : declaracaofuncao NL",
"line : chamadaFuncao NL",
"line : cmd NL",
"line : help NL",
"line : save NL",
"atribuicao : IDENTIFICADOR '=' exp",
"declaracaofuncao : IDENTIFICADOR '(' ')' '{' cmd '}'",
"chamadaFuncao : IDENTIFICADOR '(' ')'",
"cmd : exp",
"cmd : IDENTIFICADOR '=' exp ';'",
"cmd : WHILE '(' exp ')' cmd",
"cmd : IF '(' exp ')' cmd",
"cmd : '{' cmd '}'",
"exp : NUMERO",
"exp : IDENTIFICADOR",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '<' exp",
"exp : exp '>' exp",
"exp : '-' exp",
"exp : exp '^' exp",
"exp : '(' exp ')'",
"help : HELP",
"save : SAVE",
};

//#line 145 "sintatico.y"

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

//#line 374 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 46 "sintatico.y"
{yyval.obj=null;}
break;
case 2:
//#line 47 "sintatico.y"
{ currEscopo = ""; System.out.print("\n> "); }
break;
case 3:
//#line 48 "sintatico.y"
{ System.out.println("entrada ignorada"); System.out.print("\n> "); }
break;
case 4:
//#line 51 "sintatico.y"
{ yyval.obj = null; }
break;
case 5:
//#line 52 "sintatico.y"
{ 
				if (val_peek(1).obj != null) {
					System.out.println("\n Expressão = " + val_peek(1).obj); 			  		
					System.out.print(" Resultado: " + ((INodo) yyval.obj).avalia()); 
					yyval.obj=val_peek(1).obj;
			 	}
			  	if (interactive){ 
					System.out.print("\n "); 
				}
			}
break;
case 6:
//#line 62 "sintatico.y"
{ 
				if (val_peek(1).obj != null) {		  		
					System.out.print("\n Resultado: " + ((INodo) yyval.obj).avalia()); 
					yyval.obj=val_peek(1).obj;
			 	}
			  	if (interactive){ 
					System.out.print("\n "); 
				}
			}
break;
case 9:
//#line 73 "sintatico.y"
{ System.out.println("fui chamado"); }
break;
case 12:
//#line 78 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.ATRIB, val_peek(2).sval, (INodo)val_peek(0).obj); }
break;
case 13:
//#line 82 "sintatico.y"
{  	
						TS_entry nodo = ts.pesquisa(val_peek(5).sval);
                      				if (nodo != null) {
                         				yyerror("funcao " + val_peek(5).sval + " já declarada");
						}
                       				else {
							ts.insert(new TS_entry(val_peek(5).sval, Tp_DEFINE, currEscopo, ClasseID.NomeFuncao));
						}						
						currEscopo = val_peek(5).sval; currClass = ClasseID.CampoDefine; 
					}
break;
case 14:
//#line 94 "sintatico.y"
{  	
						TS_entry nodo = ts.pesquisa(val_peek(2).sval);
                      				if (nodo != null) {
                         				System.out.print(" executando a funcao... ");
						}
                       				else {
							System.out.print(" funcao não declarada ");
						}
					}
break;
case 15:
//#line 105 "sintatico.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 16:
//#line 106 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.ATRIB, val_peek(3).sval, (INodo)val_peek(1).obj); }
break;
case 17:
//#line 107 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.WHILE,(INodo)val_peek(2).obj, (INodo)val_peek(0).obj, null); }
break;
case 18:
//#line 108 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.IF,(INodo)val_peek(2).obj, (INodo)val_peek(0).obj, null); }
break;
case 19:
//#line 109 "sintatico.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 20:
//#line 112 "sintatico.y"
{ yyval.obj = new NodoTDouble(val_peek(0).dval); }
break;
case 21:
//#line 113 "sintatico.y"
{ yyval.obj = new NodoID(val_peek(0).sval);}
break;
case 22:
//#line 114 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.ADD,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); System.out.println("exp '+' exp");}
break;
case 23:
//#line 115 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.SUB,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 24:
//#line 116 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.MULL,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 25:
//#line 117 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.DIV,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 26:
//#line 118 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.LESS,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 27:
//#line 119 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.MORE,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 28:
//#line 120 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.UMINUS,(INodo)val_peek(0).obj,null); }
break;
case 29:
//#line 121 "sintatico.y"
{ yyval.obj = new NodoNT(TipoOperacao.POW,(INodo)val_peek(2).obj,(INodo)val_peek(0).obj); }
break;
case 30:
//#line 122 "sintatico.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 31:
//#line 126 "sintatico.y"
{ System.out.println(
		"O que é possível fazer:\n"+
		"- Operações imediatas. Exemplo: 2^3+5\n" +
 		"- Operações de atribuições. Exemplo: x = 2^b+5\n"+
		"- Declaração de função. Exemplo: define d (n) { return (2*n); } \n\n" +
		"--- \n" +		
		"- É possível também executar os exemplos por linha de comando. \n"
	); 
	}
break;
case 32:
//#line 138 "sintatico.y"
{ System.out.println(
		"Gravando o conteúdo atual da tabela de funções.\n"
	); 
	}
break;
//#line 680 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
