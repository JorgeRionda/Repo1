/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Programa { List<Definicion> definicion; }
	public Object visit(Programa node, Object param) {
		visitChildren(node.getDefinicion(), param);
		return null;
	}

	//	class DefVar { String string;  Tipo tipo; }
	public Object visit(DefVar node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class DefStruct { String string;  List<DefVar> defvar; }
	public Object visit(DefStruct node, Object param) {
		visitChildren(node.getDefvar(), param);
		return null;
	}

	//	class Funcion { String string;  List<Parametro> parametro;  Tipo tipo;  List<DefVar> defvar;  List<Sentencia> sentencia; }
	public Object visit(Funcion node, Object param) {
		visitChildren(node.getParametro(), param);
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		visitChildren(node.getDefvar(), param);
		visitChildren(node.getSentencia(), param);
		return null;
	}

	//	class Parametro { String string;  List<DefVar> defvar; }
	public Object visit(Parametro node, Object param) {
		visitChildren(node.getDefvar(), param);
		return null;
	}

	//	class Asignacion { Expresion left;  Expresion right; }
	public Object visit(Asignacion node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class Print { Expresion expresion; }
	public Object visit(Print node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Printsp { Expresion expresion; }
	public Object visit(Printsp node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Println { Expresion expresion; }
	public Object visit(Println node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Read { Expresion expresion; }
	public Object visit(Read node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class While { Expresion cond;  List<Sentencia> cuerpo; }
	public Object visit(While node, Object param) {
		if (node.getCond() != null)
			node.getCond().accept(this, param);
		visitChildren(node.getCuerpo(), param);
		return null;
	}

	//	class SentIf { Expresion cond;  List<Sentencia> sentencia;  List<Sentencia> sentElse; }
	public Object visit(SentIf node, Object param) {
		if (node.getCond() != null)
			node.getCond().accept(this, param);
		visitChildren(node.getSentencia(), param);
		visitChildren(node.getSentElse(), param);
		return null;
	}

	//	class LlamadaFunc { String string;  List<Expresion> expresion; }
	public Object visit(LlamadaFunc node, Object param) {
		visitChildren(node.getExpresion(), param);
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class ExprArit { Expresion left;  String string;  Expresion right; }
	public Object visit(ExprArit node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprLogic { Expresion left;  String string;  Expresion right; }
	public Object visit(ExprLogic node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprIndice { Expresion left;  Expresion right; }
	public Object visit(ExprIndice node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprNot { Expresion expresion; }
	public Object visit(ExprNot node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class ExprComp { Expresion left;  String string;  Expresion right; }
	public Object visit(ExprComp node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class ExprLlamada { Expresion expresion;  String string; }
	public Object visit(ExprLlamada node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class ExprLlamadaFunc { String string;  List<Expresion> expresion; }
	public Object visit(ExprLlamadaFunc node, Object param) {
		visitChildren(node.getExpresion(), param);
		return null;
	}

	//	class Cast { Tipo tipo;  Expresion expresion; }
	public Object visit(Cast node, Object param) {
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class Var { String string; }
	public Object visit(Var node, Object param) {
		return null;
	}

	//	class LitEnt { String string; }
	public Object visit(LitEnt node, Object param) {
		return null;
	}

	//	class LitReal { String string; }
	public Object visit(LitReal node, Object param) {
		return null;
	}

	//	class ExprChar { String string; }
	public Object visit(ExprChar node, Object param) {
		return null;
	}

	//	class TipoInt {  }
	public Object visit(TipoInt node, Object param) {
		return null;
	}

	//	class TipoReal {  }
	public Object visit(TipoReal node, Object param) {
		return null;
	}

	//	class TipoChar {  }
	public Object visit(TipoChar node, Object param) {
		return null;
	}

	//	class TipoIdent { String string; }
	public Object visit(TipoIdent node, Object param) {
		return null;
	}

	//	class TipoArray { LitEnt litent;  Tipo tipo; }
	public Object visit(TipoArray node, Object param) {
		if (node.getLitent() != null)
			node.getLitent().accept(this, param);
		if (node.getTipo() != null)
			node.getTipo().accept(this, param);
		return null;
	}

	//	class TipoVoid {  }
	public Object visit(TipoVoid node, Object param) {
		return null;
	}
	
	// Método auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
