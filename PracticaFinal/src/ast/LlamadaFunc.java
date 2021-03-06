/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	llamadaFunc:sentencia -> string:String  expresion:expresion*

public class LlamadaFunc extends AbstractSentencia {

	public LlamadaFunc(String string, List<Expresion> expresion) {
		this.string = string;
		this.expresion = expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public LlamadaFunc(Object string, Object expresion) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.expresion = (List<Expresion>) expresion;

		searchForPositions(string, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public List<Expresion> getExpresion() {
		return expresion;
	}
	public void setExpresion(List<Expresion> expresion) {
		this.expresion = expresion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private List<Expresion> expresion;
}

