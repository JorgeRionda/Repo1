/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	sentIf:sentencia -> cond:expresion  sentencia:sentencia*  sentElse:sentencia*

public class SentIf extends AbstractSentencia {

	public SentIf(Expresion cond, List<Sentencia> sentencia, List<Sentencia> sentElse) {
		this.cond = cond;
		this.sentencia = sentencia;
		this.sentElse = sentElse;

		searchForPositions(cond, sentencia, sentElse);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public SentIf(Object cond, Object sentencia, Object sentElse) {
		this.cond = (Expresion) cond;
		this.sentencia = (List<Sentencia>) sentencia;
		this.sentElse = (List<Sentencia>) sentElse;

		searchForPositions(cond, sentencia, sentElse);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getCond() {
		return cond;
	}
	public void setCond(Expresion cond) {
		this.cond = cond;
	}

	public List<Sentencia> getSentencia() {
		return sentencia;
	}
	public void setSentencia(List<Sentencia> sentencia) {
		this.sentencia = sentencia;
	}

	public List<Sentencia> getSentElse() {
		return sentElse;
	}
	public void setSentElse(List<Sentencia> sentElse) {
		this.sentElse = sentElse;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion cond;
	private List<Sentencia> sentencia;
	private List<Sentencia> sentElse;
}

