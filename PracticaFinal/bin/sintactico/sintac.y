// @author Raúl Izquierdo

/* No es necesario modificar esta sección ------------------ */
%{
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
%}

/* Precedencias aquí --------------------------------------- */
%left 'AND' 'OR' '!'
%left '<' '>' 'MAYORIGUAL' 'MENORIGUAL' 'DISTINTO' 'IGUAL'
%left '+' '-'
%left '*' '/'
%left '(' ')' '[' ']' '.'

%%

/* Añadir las reglas en esta sección ----------------------- */

programa: definiciones							{raiz = new Programa($1);}


definiciones: 									{$$ = new ArrayList();}
	|definiciones definicion					{$$ = $1; ((ArrayList)$1).add($2);}
	
	
definicion: definicionVariable      			{$$ = $1;}
	|definicionEstructura						{$$ = $1;}
	|definicionFuncion							{$$ = $1;}
	
	
definicionVariable: 'VAR' 'IDENT' ':' tipo ';'	{$$ = new DefVar($2,$4);}


tipo: 'IDENT'									{$$ = new TipoIdent($1);}
	|'[''LITENT'']' tipo						{$$ = new TipoArray(new LitEnt($2),$4);}
	|tipoSimple									{$$ = $1;}
	
	
tipoSimple: 'INT'								{$$ = new TipoInt();}
	|'FLOAT'									{$$ = new TipoReal();}
	|'CHAR'										{$$ = new TipoChar();}
	
	
definicionEstructura: 'STRUCT' 'IDENT' '{' cuerpoEstructura '}' ';' {$$ = new DefStruct($2,$4);}


cuerpoEstructura: 												{ $$ = new ArrayList();}
	|cuerpoEstructura cuerpo									{ $$ = $1; ((ArrayList)$1).add($2);}
	
	
cuerpo: 'IDENT' ':' tipo ';'										{$$ = new DefVar($1,$3);}


definicionFuncion: 'IDENT' '(' parametros ')' retorno '{' definicionesVariable sentencias '}'  {$$ = new Funcion($1,$3,$5,$7,$8);}


definicionesVariable:														{$$ = new ArrayList();}
	 |definicionesVariable definicionVariable								{$$ = $1; ((ArrayList)$1).add($2);}
	
	
parametros:																	{$$ = new ArrayList();}
	|parametro																{$$ = $1;}
	
	
parametro: param							{ArrayList lista = new ArrayList(); lista.add((DefVar)$1); $$ = lista;}
	|parametro ','  param					{ArrayList lista = (ArrayList)$1; lista.add((DefVar)$3); $$ = lista;}
	
param: 'IDENT' ':' tipoSimple				{$$ = new DefVar($1,(Tipo)$3);}
	
	
retorno:									{$$ = new TipoVoid();}
	|':' tipoSimple							{$$ = (Tipo)$2;}


	
sentencias: 									{$$ = new ArrayList();}
	|sentencias sentencia						{$$ = $1; ((ArrayList)$1).add($2);}
	
sentencia: sentenciaAsignacion					{$$ = $1;}
	|sentenciaPrint								{$$ = $1;}
	|sentenciaRead								{$$ = $1;}
	|sentenciaWhile								{$$ = $1;}
	|sentenciaIf								{$$ = $1;}
	|sentenciaLlamadaFuncion					{$$ = $1;}
	|sentenciaReturn							{$$ = $1;}
	
	
sentenciaAsignacion: expresion '=' expresion ';'		{$$ = new Asignacion($1,$3);}


sentenciaPrint: 'PRINT' expresion ';'					{$$ = new Print($2);}
	|'PRINTSP'  expresion ';'							{$$ = new Printsp($2);}
	|'PRINTLN'  expresion ';'							{$$ = new Println($2);}
	
	
sentenciaRead: 'READ' expresion ';' 					{$$ = new Read($2);}


sentenciaWhile: 'WHILE' '(' expresion ')' '{' sentencias '}'   {$$ = new While($3,$6);}


sentenciaIf: 'IF' '(' expresion ')' '{' sentencias '}'   					{$$ = new SentIf($3,$6,null);}
	|'IF' '(' expresion ')' '{' sentencias '}' 'ELSE' '{' sentencias '}'	{$$ = new SentIf($3,$6,$10);}


sentenciaLlamadaFuncion: 'IDENT' '(' argumentos ')' ';'        {$$ = new LlamadaFunc($1,$3);}


argumentos:								{$$ = new ArrayList();}
	|argumento							{$$ = $1;}
	

argumento: expresion					{ArrayList lista = new ArrayList(); lista.add((Expresion)$1);$$ = lista;}
	| argumento ',' expresion			{$$ = $1; ((ArrayList)$1).add((Expresion)$3);} 
	
	
sentenciaReturn: 'RETURN' return ';'	{$$ = $2;}

	
return:									{$$ = new Return(null);}																
	|expresion							{$$ = new Return($1);}
	

expresion: 'IDENT'									{$$ = new Var($1);}
	|'LITENT'										{$$ = new LitEnt($1);}
	|'LITREAL'										{$$ = new LitReal($1);}
	|'CHAR'											{$$ = new ExprChar($1);}
	|expresion '[' expresion ']'					{$$ = new ExprIndice($1,$3);}
	|expresion '+' expresion						{$$ = new ExprArit($1,"+",$3);}
	|expresion '-' expresion						{$$ = new ExprArit($1,"-",$3);}
	|expresion '*' expresion						{$$ = new ExprArit($1,"*",$3);}
	|expresion '/' expresion						{$$ = new ExprArit($1,"/",$3);}
	|'(' expresion ')'								{$$ = $2;}
	|expresion '<' expresion						{$$ = new ExprComp($1,"<",$3);}
	|expresion '>' expresion						{$$ = new ExprComp($1,">",$3);}
	|expresion 'MENORIGUAL' expresion				{$$ = new ExprComp($1,"<=",$3);}
	|expresion 'MAYORIGUAL' expresion				{$$ = new ExprComp($1,">=",$3);}
	|expresion 'DISTINTO' expresion					{$$ = new ExprComp($1,"!=",$3);}
	|expresion 'IGUAL' expresion					{$$ = new ExprComp($1,"==",$3);}
	|expresion 'AND' expresion						{$$ = new ExprLogic($1,"&&",$3);}
	|expresion 'OR' expresion						{$$ = new ExprLogic($1,"OR",$3);}
	|'!' expresion									{$$ = new ExprNot($2);}
	|expresion '.' 'IDENT'							{$$ = new ExprLlamada($1, $3);}
	|'CAST' '<' tipoSimple '>' '(' expresion ')'	{$$ = new Cast($3,$6);}
	|'IDENT' '(' argumentos ')' 					{$$ = new ExprLlamadaFunc($1,$3);}
	

%%
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
