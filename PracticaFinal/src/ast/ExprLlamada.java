/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	exprLlamada:expresion -> expresion:expresion  string:String

public class ExprLlamada extends AbstractExpresion {

	public ExprLlamada(Expresion expresion, String string) {
		this.expresion = expresion;
		this.string = string;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	public ExprLlamada(Object expresion, Object string) {
		this.expresion = (Expresion) expresion;
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;

		searchForPositions(expresion, string);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getExpresion() {
		return expresion;
	}
	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion expresion;
	private String string;
}

