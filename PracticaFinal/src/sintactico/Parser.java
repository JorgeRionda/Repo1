//### This file created by BYACC 1.8(/Java extension  1.14)
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






//#line 5 "sintac.y"
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
//#line 24 "Parser.java"




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
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short AND=257;
public final static short OR=258;
public final static short MAYORIGUAL=259;
public final static short MENORIGUAL=260;
public final static short DISTINTO=261;
public final static short IGUAL=262;
public final static short VAR=263;
public final static short IDENT=264;
public final static short LITENT=265;
public final static short INT=266;
public final static short FLOAT=267;
public final static short CHAR=268;
public final static short STRUCT=269;
public final static short PRINT=270;
public final static short PRINTSP=271;
public final static short PRINTLN=272;
public final static short READ=273;
public final static short WHILE=274;
public final static short IF=275;
public final static short ELSE=276;
public final static short RETURN=277;
public final static short LITREAL=278;
public final static short CAST=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    3,    6,    6,    6,
    7,    7,    7,    4,    8,    8,    9,    5,   12,   12,
   10,   10,   14,   14,   15,   11,   11,   13,   13,   16,
   16,   16,   16,   16,   16,   16,   17,   18,   18,   18,
   19,   20,   21,   21,   22,   25,   25,   26,   26,   23,
   27,   27,   24,   24,   24,   24,   24,   24,   24,   24,
   24,   24,   24,   24,   24,   24,   24,   24,   24,   24,
   24,   24,   24,   24,
};
final static short yylen[] = {                            2,
    1,    0,    2,    1,    1,    1,    5,    1,    4,    1,
    1,    1,    1,    6,    0,    2,    4,    9,    0,    2,
    0,    1,    1,    3,    3,    0,    2,    0,    2,    1,
    1,    1,    1,    1,    1,    1,    4,    3,    3,    3,
    3,    7,    7,   11,    5,    0,    1,    1,    3,    3,
    0,    1,    1,    1,    1,    1,    4,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    3,    7,    4,
};
final static short yydefred[] = {                         2,
    0,    0,    0,    0,    0,    3,    4,    5,    6,    0,
    0,    0,    0,    0,    0,    0,   23,   15,    0,    8,
   11,   12,   13,    0,   10,    0,    0,    0,    0,    0,
    7,   25,    0,    0,   24,    0,    0,   16,    0,   27,
   19,    0,   14,    9,    0,    0,   20,    0,   17,    0,
    0,    0,   54,   56,   18,    0,    0,    0,    0,    0,
    0,    0,   55,    0,   29,   30,   31,   32,   33,   34,
   35,   36,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   62,    0,    0,    0,   38,   39,   40,   41,
    0,    0,   50,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   72,    0,    0,
    0,    0,    0,    0,    0,   57,   37,   74,   45,    0,
   28,   28,    0,    0,    0,    0,   42,    0,   73,    0,
   28,    0,   44,
};
final static short yydgoto[] = {                          1,
    2,    6,    7,    8,    9,   24,   25,   29,   38,   15,
   34,   45,   48,   16,   17,   65,   66,   67,   68,   69,
   70,   71,   72,   73,  105,  106,   85,
};
final static short yysindex[] = {                         0,
    0, -201, -258,  -18, -256,    0,    0,    0,    0,  -34,
 -238,  -89,  -44,  -20,    1,   10,    0,    0, -207,    0,
    0,    0,    0,    6,    0, -194,    2, -238, -122,  -26,
    0,    0, -194,  -53,    0,   17,   18,    0,  -44,    0,
    0,  -44,    0,    0, -184,   22,    0,  -33,    0,   63,
   63,   42,    0,    0,    0,   63,   63,   63,   63,   43,
   44,   63,    0,   25,    0,    0,    0,    0,    0,    0,
    0,    0,  450,   46,  721,  116,   63,  457,  480,  487,
  530,   63,   63,  715,   29, -194,   63,   63,   63,   63,
   63,   63,   63,   63,   63,   63,   63,   63,   63, -174,
   63,   63,    0,  715,   50,   49,    0,    0,    0,    0,
  124,  152,    0,   32,  721,  721,   -2,   -2,   -2,   -2,
   -2,   -2,    4,    4,  -15,  -15,  537,    0,  567,   56,
   39,   63,  -23,  -22,   59,    0,    0,    0,    0,  715,
    0,    0,   63,  -17,   -1,  159,    0, -172,    0,  -21,
    0,   15,    0,
};
final static short yyrindex[] = {                         0,
    0,  105,    0,    0,    0,    0,    0,    0,    0,    0,
   65,    0,    0,    0,    0,   66,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -14,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,    0,    0,    0,    0,    0,
    0,  573,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -32,  -40,    0,   70,    0,    0,    0,
    0,    0,    0,   53,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   70,    0,  -39,    0,   72,    0,    0,    0,    0,
    0,    0,    0,    0,  -24,   82,  381,  387,  393,  416,
  422,  428,  308,  346,   75,   86,    0,    0,    0,    0,
  709,    0,    0,    0,    0,    0,    0,    0,    0,    8,
    0,    0,    0,    0,    0,    0,    0,   47,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   69,    0,    0,   -6,   -8,    0,    0,    0,
    0,    0,  -85,    0,   87,    0,    0,    0,    0,    0,
    0,    0,    0,  759,   19,    0,    0,
};
final static int YYTABLESIZE=983;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         50,
   71,   48,   37,   71,   48,   10,   51,   12,   53,   53,
   53,   53,   53,   53,   53,   50,   69,   32,   71,   69,
   71,   11,   51,   13,   40,   14,   53,   53,   53,   53,
  100,   50,   44,   18,   69,   46,   69,   26,   51,   97,
   95,   27,   96,  100,   98,   97,   19,   50,   49,  100,
   98,   49,   71,   28,   51,  144,  145,   30,   53,   33,
   53,    3,    4,   28,   31,  152,   39,    5,   69,   41,
   28,   21,   22,   23,   42,   99,   43,  114,    3,   43,
   49,   77,   82,   83,   86,  102,   43,  113,   99,  128,
  131,   55,  132,  135,   99,   50,  138,  139,  143,  141,
  142,  151,   51,  150,    1,   21,   22,  147,   26,   51,
   46,   52,   47,   47,   35,   60,   60,   60,   60,   60,
  130,   60,   70,  148,    0,   70,   61,   61,   61,   61,
   61,    0,   61,   60,   60,   60,   60,    0,    0,  153,
   70,   36,   70,    0,   61,   61,   61,   61,    0,    0,
    0,    0,    0,    0,    0,   28,  103,   97,   95,    0,
   96,  100,   98,    0,  133,   97,   95,   60,   96,  100,
   98,   43,    0,    0,   70,   89,    0,   90,   61,    0,
    0,    0,    0,   89,    0,   90,    0,    0,    0,    0,
    0,    0,  134,   97,   95,    0,   96,  100,   98,  149,
   97,   95,    0,   96,  100,   98,   99,    0,    0,    0,
    0,   89,    0,   90,   99,    0,   71,   71,   89,   20,
   90,   21,   22,   23,   53,   53,   53,   53,   53,   53,
   52,   53,   69,   69,   54,    0,   56,   57,   58,   59,
   60,   61,   99,   62,   63,   64,   52,   53,    0,   99,
   54,    0,   56,   57,   58,   59,   60,   61,    0,   62,
   63,   64,   52,   53,    0,    0,   54,    0,   56,   57,
   58,   59,   60,   61,    0,   62,   63,   64,   52,   53,
    0,    0,   54,    0,   56,   57,   58,   59,   60,   61,
    0,   62,   63,   64,   28,   28,    0,    0,   28,    0,
   28,   28,   28,   28,   28,   28,    0,   28,   28,   28,
   43,   43,    0,    0,   43,    0,   43,   43,   43,   43,
   43,   43,    0,   43,   43,   43,   74,   53,    0,    0,
   54,   60,   60,   60,   60,   60,   60,    0,   70,   70,
   63,   64,   61,   61,   61,   61,   61,   61,   58,    0,
   58,   58,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   58,   58,   58,   58,
    0,    0,   87,   88,   91,   92,   93,   94,    0,    0,
   87,   88,   91,   92,   93,   94,   59,    0,   59,   59,
   59,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   58,    0,    0,    0,   59,   59,   59,   59,   87,   88,
   91,   92,   93,   94,    0,   87,   88,   91,   92,   93,
   94,   63,    0,    0,   63,    0,    0,   64,    0,    0,
   64,    0,    0,   66,    0,    0,   66,    0,   59,   63,
   63,   63,   63,    0,    0,   64,   64,   64,   64,    0,
    0,   66,   66,   66,   66,    0,   65,    0,    0,   65,
    0,    0,   67,    0,    0,   67,    0,    0,   68,    0,
    0,   68,    0,   63,   65,   65,   65,   65,    0,   64,
   67,   67,   67,   67,    0,   66,   68,   68,   68,   68,
    0,   97,   95,    0,   96,  100,   98,    0,   97,   95,
    0,   96,  100,   98,    0,    0,    0,    0,   65,   89,
  101,   90,    0,    0,   67,  107,   89,    0,   90,    0,
   68,   97,   95,    0,   96,  100,   98,    0,   97,   95,
    0,   96,  100,   98,    0,    0,    0,    0,  108,   89,
   99,   90,    0,    0,    0,  109,   89,   99,   90,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,   58,   58,   58,   58,   58,
   99,   97,   95,    0,   96,  100,   98,   99,   97,   95,
    0,   96,  100,   98,    0,    0,    0,    0,  110,   89,
    0,   90,    0,    0,    0,    0,   89,    0,   90,    0,
    0,    0,   59,   59,   59,   59,   59,   59,   97,   95,
    0,   96,  100,   98,   53,   53,    0,   53,   53,   53,
   99,    0,    0,    0,    0,  137,   89,   99,   90,  136,
    0,    0,   53,   53,   53,    0,    0,   63,   63,   63,
   63,   63,   63,   64,   64,   64,   64,   64,   64,   66,
   66,   66,   66,   66,   66,    0,    0,   99,    0,    0,
    0,    0,    0,   53,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   65,   65,   65,   65,   65,   67,   67,
   67,   67,   67,   67,   68,   68,   68,   68,   68,   68,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   87,   88,   91,   92,
   93,   94,    0,   87,   88,   91,   92,   93,   94,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   87,   88,   91,   92,
   93,   94,    0,   87,   88,   91,   92,   93,   94,    0,
   74,   74,    0,   74,   74,   74,   97,   95,    0,   96,
  100,   98,   97,   95,    0,   96,  100,   98,   74,   74,
   74,    0,    0,    0,   89,    0,   90,    0,    0,    0,
   89,    0,   90,    0,    0,    0,   87,   88,   91,   92,
   93,   94,    0,   87,   88,   91,   92,   93,   94,   74,
    0,    0,    0,    0,    0,   99,    0,    0,   75,   76,
    0,   99,    0,    0,   78,   79,   80,   81,    0,    0,
   84,    0,    0,   87,   88,   91,   92,   93,   94,   53,
   53,   53,   53,   53,   53,  104,    0,    0,    0,    0,
  111,  112,    0,    0,    0,  115,  116,  117,  118,  119,
  120,  121,  122,  123,  124,  125,  126,  127,    0,  129,
  104,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  140,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  146,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   74,   74,   74,   74,   74,
   74,   87,   88,   91,   92,   93,   94,    0,    0,   91,
   92,   93,   94,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   41,  125,   44,   44,  264,   40,  264,   41,   42,
   43,   44,   45,   46,   47,   33,   41,   26,   59,   44,
   61,   40,   40,   58,   33,  264,   59,   60,   61,   62,
   46,   33,   39,  123,   59,   42,   61,   58,   40,   42,
   43,   41,   45,   46,   47,   42,   91,   33,   41,   46,
   47,   44,   93,   44,   40,  141,  142,  265,   91,   58,
   93,  263,  264,   33,   59,  151,   93,  269,   93,  123,
   40,  266,  267,  268,   58,   91,   59,   86,  263,   33,
   59,   40,   40,   40,   60,   40,   40,   59,   91,  264,
   41,  125,   44,   62,   91,   33,   41,   59,   40,  123,
  123,  123,   40,  276,    0,   41,   41,  125,  123,   59,
   41,   59,   41,   45,   28,   41,   42,   43,   44,   45,
  102,   47,   41,  125,   -1,   44,   41,   42,   43,   44,
   45,   -1,   47,   59,   60,   61,   62,   -1,   -1,  125,
   59,  264,   61,   -1,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   41,   42,   43,   -1,
   45,   46,   47,   -1,   41,   42,   43,   93,   45,   46,
   47,  125,   -1,   -1,   93,   60,   -1,   62,   93,   -1,
   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,   41,
   42,   43,   -1,   45,   46,   47,   91,   -1,   -1,   -1,
   -1,   60,   -1,   62,   91,   -1,  257,  258,   60,  264,
   62,  266,  267,  268,  257,  258,  259,  260,  261,  262,
  264,  265,  257,  258,  268,   -1,  270,  271,  272,  273,
  274,  275,   91,  277,  278,  279,  264,  265,   -1,   91,
  268,   -1,  270,  271,  272,  273,  274,  275,   -1,  277,
  278,  279,  264,  265,   -1,   -1,  268,   -1,  270,  271,
  272,  273,  274,  275,   -1,  277,  278,  279,  264,  265,
   -1,   -1,  268,   -1,  270,  271,  272,  273,  274,  275,
   -1,  277,  278,  279,  264,  265,   -1,   -1,  268,   -1,
  270,  271,  272,  273,  274,  275,   -1,  277,  278,  279,
  264,  265,   -1,   -1,  268,   -1,  270,  271,  272,  273,
  274,  275,   -1,  277,  278,  279,  264,  265,   -1,   -1,
  268,  257,  258,  259,  260,  261,  262,   -1,  257,  258,
  278,  279,  257,  258,  259,  260,  261,  262,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,
  257,  258,  259,  260,  261,  262,   41,   -1,   43,   44,
   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   59,   60,   61,   62,  257,  258,
  259,  260,  261,  262,   -1,  257,  258,  259,  260,  261,
  262,   41,   -1,   -1,   44,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   41,   -1,   -1,   44,   -1,   93,   59,
   60,   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   59,   60,   61,   62,   -1,   41,   -1,   -1,   44,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   41,   -1,
   -1,   44,   -1,   93,   59,   60,   61,   62,   -1,   93,
   59,   60,   61,   62,   -1,   93,   59,   60,   61,   62,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   93,   60,
   61,   62,   -1,   -1,   93,   59,   60,   -1,   62,   -1,
   93,   42,   43,   -1,   45,   46,   47,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   59,   60,
   91,   62,   -1,   -1,   -1,   59,   60,   91,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   91,   42,   43,   -1,   45,   46,   47,   91,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   60,   -1,   62,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   42,   43,
   -1,   45,   46,   47,   42,   43,   -1,   45,   46,   47,
   91,   -1,   -1,   -1,   -1,   59,   60,   91,   62,   93,
   -1,   -1,   60,   61,   62,   -1,   -1,  257,  258,  259,
  260,  261,  262,  257,  258,  259,  260,  261,  262,  257,
  258,  259,  260,  261,  262,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,  257,  258,
  259,  260,  261,  262,  257,  258,  259,  260,  261,  262,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  257,  258,  259,  260,  261,  262,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  257,  258,  259,  260,  261,  262,   -1,
   42,   43,   -1,   45,   46,   47,   42,   43,   -1,   45,
   46,   47,   42,   43,   -1,   45,   46,   47,   60,   61,
   62,   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,
   60,   -1,   62,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  257,  258,  259,  260,  261,  262,   91,
   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   50,   51,
   -1,   91,   -1,   -1,   56,   57,   58,   59,   -1,   -1,
   62,   -1,   -1,  257,  258,  259,  260,  261,  262,  257,
  258,  259,  260,  261,  262,   77,   -1,   -1,   -1,   -1,
   82,   83,   -1,   -1,   -1,   87,   88,   89,   90,   91,
   92,   93,   94,   95,   96,   97,   98,   99,   -1,  101,
  102,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  132,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  143,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,  257,  258,  259,  260,  261,  262,   -1,   -1,  259,
  260,  261,  262,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"\"AND\"","\"OR\"",
"\"MAYORIGUAL\"","\"MENORIGUAL\"","\"DISTINTO\"","\"IGUAL\"","\"VAR\"",
"\"IDENT\"","\"LITENT\"","\"INT\"","\"FLOAT\"","\"CHAR\"","\"STRUCT\"",
"\"PRINT\"","\"PRINTSP\"","\"PRINTLN\"","\"READ\"","\"WHILE\"","\"IF\"",
"\"ELSE\"","\"RETURN\"","\"LITREAL\"","\"CAST\"",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones :",
"definiciones : definiciones definicion",
"definicion : definicionVariable",
"definicion : definicionEstructura",
"definicion : definicionFuncion",
"definicionVariable : \"VAR\" \"IDENT\" ':' tipo ';'",
"tipo : \"IDENT\"",
"tipo : '[' \"LITENT\" ']' tipo",
"tipo : tipoSimple",
"tipoSimple : \"INT\"",
"tipoSimple : \"FLOAT\"",
"tipoSimple : \"CHAR\"",
"definicionEstructura : \"STRUCT\" \"IDENT\" '{' cuerpoEstructura '}' ';'",
"cuerpoEstructura :",
"cuerpoEstructura : cuerpoEstructura cuerpo",
"cuerpo : \"IDENT\" ':' tipo ';'",
"definicionFuncion : \"IDENT\" '(' parametros ')' retorno '{' definicionesVariable sentencias '}'",
"definicionesVariable :",
"definicionesVariable : definicionesVariable definicionVariable",
"parametros :",
"parametros : parametro",
"parametro : param",
"parametro : parametro ',' param",
"param : \"IDENT\" ':' tipoSimple",
"retorno :",
"retorno : ':' tipoSimple",
"sentencias :",
"sentencias : sentencias sentencia",
"sentencia : sentenciaAsignacion",
"sentencia : sentenciaPrint",
"sentencia : sentenciaRead",
"sentencia : sentenciaWhile",
"sentencia : sentenciaIf",
"sentencia : sentenciaLlamadaFuncion",
"sentencia : sentenciaReturn",
"sentenciaAsignacion : expresion '=' expresion ';'",
"sentenciaPrint : \"PRINT\" expresion ';'",
"sentenciaPrint : \"PRINTSP\" expresion ';'",
"sentenciaPrint : \"PRINTLN\" expresion ';'",
"sentenciaRead : \"READ\" expresion ';'",
"sentenciaWhile : \"WHILE\" '(' expresion ')' '{' sentencias '}'",
"sentenciaIf : \"IF\" '(' expresion ')' '{' sentencias '}'",
"sentenciaIf : \"IF\" '(' expresion ')' '{' sentencias '}' \"ELSE\" '{' sentencias '}'",
"sentenciaLlamadaFuncion : \"IDENT\" '(' argumentos ')' ';'",
"argumentos :",
"argumentos : argumento",
"argumento : expresion",
"argumento : argumento ',' expresion",
"sentenciaReturn : \"RETURN\" return ';'",
"return :",
"return : expresion",
"expresion : \"IDENT\"",
"expresion : \"LITENT\"",
"expresion : \"LITREAL\"",
"expresion : \"CHAR\"",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : '(' expresion ')'",
"expresion : expresion '<' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion \"MENORIGUAL\" expresion",
"expresion : expresion \"MAYORIGUAL\" expresion",
"expresion : expresion \"DISTINTO\" expresion",
"expresion : expresion \"IGUAL\" expresion",
"expresion : expresion \"AND\" expresion",
"expresion : expresion \"OR\" expresion",
"expresion : '!' expresion",
"expresion : expresion '.' \"IDENT\"",
"expresion : \"CAST\" '<' tipoSimple '>' '(' expresion ')'",
"expresion : \"IDENT\" '(' argumentos ')'",
};

//#line 154 "sintac.y"
/* No es necesario modificar esta sección ------------------ */

public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}

// Métodos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }

// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sintáctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
}

int yylex() {
	try {
		int token = lex.yylex();
		yylval = new Token(token, lex.lexeme(), lex.line(), lex.column());
		return token;
	} catch (IOException e) {
		return -1;
	}
}

private Yylex lex;
private GestorErrores gestor;
private AST raiz;
//#line 547 "Parser.java"
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
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 24 "sintac.y"
{raiz = new Programa(val_peek(0));}
break;
case 2:
//#line 27 "sintac.y"
{yyval = new ArrayList();}
break;
case 3:
//#line 28 "sintac.y"
{yyval = val_peek(1); ((ArrayList)val_peek(1)).add(val_peek(0));}
break;
case 4:
//#line 31 "sintac.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 32 "sintac.y"
{yyval = val_peek(0);}
break;
case 6:
//#line 33 "sintac.y"
{yyval = val_peek(0);}
break;
case 7:
//#line 36 "sintac.y"
{yyval = new DefVar(val_peek(3),val_peek(1));}
break;
case 8:
//#line 39 "sintac.y"
{yyval = new TipoIdent(val_peek(0));}
break;
case 9:
//#line 40 "sintac.y"
{yyval = new TipoArray(new LitEnt(val_peek(2)),val_peek(0));}
break;
case 10:
//#line 41 "sintac.y"
{yyval = val_peek(0);}
break;
case 11:
//#line 44 "sintac.y"
{yyval = new TipoInt();}
break;
case 12:
//#line 45 "sintac.y"
{yyval = new TipoReal();}
break;
case 13:
//#line 46 "sintac.y"
{yyval = new TipoChar();}
break;
case 14:
//#line 49 "sintac.y"
{yyval = new DefStruct(val_peek(4),val_peek(2));}
break;
case 15:
//#line 52 "sintac.y"
{ yyval = new ArrayList();}
break;
case 16:
//#line 53 "sintac.y"
{ yyval = val_peek(1); ((ArrayList)val_peek(1)).add(val_peek(0));}
break;
case 17:
//#line 56 "sintac.y"
{yyval = new DefVar(val_peek(3),val_peek(1));}
break;
case 18:
//#line 59 "sintac.y"
{yyval = new Funcion(val_peek(8),val_peek(6),val_peek(4),val_peek(2),val_peek(1));}
break;
case 19:
//#line 62 "sintac.y"
{yyval = new ArrayList();}
break;
case 20:
//#line 63 "sintac.y"
{yyval = val_peek(1); ((ArrayList)val_peek(1)).add(val_peek(0));}
break;
case 21:
//#line 66 "sintac.y"
{yyval = new ArrayList();}
break;
case 22:
//#line 67 "sintac.y"
{yyval = val_peek(0);}
break;
case 23:
//#line 70 "sintac.y"
{ArrayList lista = new ArrayList(); lista.add((DefVar)val_peek(0)); yyval = lista;}
break;
case 24:
//#line 71 "sintac.y"
{ArrayList lista = (ArrayList)val_peek(2); lista.add((DefVar)val_peek(0)); yyval = lista;}
break;
case 25:
//#line 73 "sintac.y"
{yyval = new DefVar(val_peek(2),(Tipo)val_peek(0));}
break;
case 26:
//#line 76 "sintac.y"
{yyval = new TipoVoid();}
break;
case 27:
//#line 77 "sintac.y"
{yyval = (Tipo)val_peek(0);}
break;
case 28:
//#line 81 "sintac.y"
{yyval = new ArrayList();}
break;
case 29:
//#line 82 "sintac.y"
{yyval = val_peek(1); ((ArrayList)val_peek(1)).add(val_peek(0));}
break;
case 30:
//#line 84 "sintac.y"
{yyval = val_peek(0);}
break;
case 31:
//#line 85 "sintac.y"
{yyval = val_peek(0);}
break;
case 32:
//#line 86 "sintac.y"
{yyval = val_peek(0);}
break;
case 33:
//#line 87 "sintac.y"
{yyval = val_peek(0);}
break;
case 34:
//#line 88 "sintac.y"
{yyval = val_peek(0);}
break;
case 35:
//#line 89 "sintac.y"
{yyval = val_peek(0);}
break;
case 36:
//#line 90 "sintac.y"
{yyval = val_peek(0);}
break;
case 37:
//#line 93 "sintac.y"
{yyval = new Asignacion(val_peek(3),val_peek(1));}
break;
case 38:
//#line 96 "sintac.y"
{yyval = new Print(val_peek(1));}
break;
case 39:
//#line 97 "sintac.y"
{yyval = new Printsp(val_peek(1));}
break;
case 40:
//#line 98 "sintac.y"
{yyval = new Println(val_peek(1));}
break;
case 41:
//#line 101 "sintac.y"
{yyval = new Read(val_peek(1));}
break;
case 42:
//#line 104 "sintac.y"
{yyval = new While(val_peek(4),val_peek(1));}
break;
case 43:
//#line 107 "sintac.y"
{yyval = new SentIf(val_peek(4),val_peek(1),null);}
break;
case 44:
//#line 108 "sintac.y"
{yyval = new SentIf(val_peek(8),val_peek(5),val_peek(1));}
break;
case 45:
//#line 111 "sintac.y"
{yyval = new LlamadaFunc(val_peek(4),val_peek(2));}
break;
case 46:
//#line 114 "sintac.y"
{yyval = new ArrayList();}
break;
case 47:
//#line 115 "sintac.y"
{yyval = val_peek(0);}
break;
case 48:
//#line 118 "sintac.y"
{ArrayList lista = new ArrayList(); lista.add((Expresion)val_peek(0));yyval = lista;}
break;
case 49:
//#line 119 "sintac.y"
{yyval = val_peek(2); ((ArrayList)val_peek(2)).add((Expresion)val_peek(0));}
break;
case 50:
//#line 122 "sintac.y"
{yyval = val_peek(1);}
break;
case 51:
//#line 125 "sintac.y"
{yyval = new Return(null);}
break;
case 52:
//#line 126 "sintac.y"
{yyval = new Return(val_peek(0));}
break;
case 53:
//#line 129 "sintac.y"
{yyval = new Var(val_peek(0));}
break;
case 54:
//#line 130 "sintac.y"
{yyval = new LitEnt(val_peek(0));}
break;
case 55:
//#line 131 "sintac.y"
{yyval = new LitReal(val_peek(0));}
break;
case 56:
//#line 132 "sintac.y"
{yyval = new ExprChar(val_peek(0));}
break;
case 57:
//#line 133 "sintac.y"
{yyval = new ExprIndice(val_peek(3),val_peek(1));}
break;
case 58:
//#line 134 "sintac.y"
{yyval = new ExprArit(val_peek(2),"+",val_peek(0));}
break;
case 59:
//#line 135 "sintac.y"
{yyval = new ExprArit(val_peek(2),"-",val_peek(0));}
break;
case 60:
//#line 136 "sintac.y"
{yyval = new ExprArit(val_peek(2),"*",val_peek(0));}
break;
case 61:
//#line 137 "sintac.y"
{yyval = new ExprArit(val_peek(2),"/",val_peek(0));}
break;
case 62:
//#line 138 "sintac.y"
{yyval = val_peek(1);}
break;
case 63:
//#line 139 "sintac.y"
{yyval = new ExprComp(val_peek(2),"<",val_peek(0));}
break;
case 64:
//#line 140 "sintac.y"
{yyval = new ExprComp(val_peek(2),">",val_peek(0));}
break;
case 65:
//#line 141 "sintac.y"
{yyval = new ExprComp(val_peek(2),"<=",val_peek(0));}
break;
case 66:
//#line 142 "sintac.y"
{yyval = new ExprComp(val_peek(2),">=",val_peek(0));}
break;
case 67:
//#line 143 "sintac.y"
{yyval = new ExprComp(val_peek(2),"!=",val_peek(0));}
break;
case 68:
//#line 144 "sintac.y"
{yyval = new ExprComp(val_peek(2),"==",val_peek(0));}
break;
case 69:
//#line 145 "sintac.y"
{yyval = new ExprLogic(val_peek(2),"&&",val_peek(0));}
break;
case 70:
//#line 146 "sintac.y"
{yyval = new ExprLogic(val_peek(2),"OR",val_peek(0));}
break;
case 71:
//#line 147 "sintac.y"
{yyval = new ExprNot(val_peek(0));}
break;
case 72:
//#line 148 "sintac.y"
{yyval = new ExprLlamada(val_peek(2), val_peek(0));}
break;
case 73:
//#line 149 "sintac.y"
{yyval = new Cast(val_peek(4),val_peek(1));}
break;
case 74:
//#line 150 "sintac.y"
{yyval = new ExprLlamadaFunc(val_peek(3),val_peek(1));}
break;
//#line 991 "Parser.java"
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
