/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	defVar:definicion -> string:String  tipo:tipo

public class DefVar extends AbstractDefinicion {

	public DefVar(String string, Tipo tipo) {
		this.string = string;
		this.tipo = tipo;

		searchForPositions(tipo);	// Obtener linea/columna a partir de los hijos
	}

	public DefVar(Object string, Object tipo) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.tipo = (Tipo) tipo;

		searchForPositions(string, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private Tipo tipo;
}

