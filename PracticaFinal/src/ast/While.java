/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	while:sentencia -> cond:expresion  cuerpo:sentencia*

public class While extends AbstractSentencia {

	public While(Expresion cond, List<Sentencia> cuerpo) {
		this.cond = cond;
		this.cuerpo = cuerpo;

		searchForPositions(cond, cuerpo);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public While(Object cond, Object cuerpo) {
		this.cond = (Expresion) cond;
		this.cuerpo = (List<Sentencia>) cuerpo;

		searchForPositions(cond, cuerpo);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getCond() {
		return cond;
	}
	public void setCond(Expresion cond) {
		this.cond = cond;
	}

	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(List<Sentencia> cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion cond;
	private List<Sentencia> cuerpo;
}

