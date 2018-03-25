/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoArray:tipo -> litent:litEnt  tipo:tipo

public class TipoArray extends AbstractTipo {

	public TipoArray(LitEnt litent, Tipo tipo) {
		this.litent = litent;
		this.tipo = tipo;

		searchForPositions(litent, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public TipoArray(Object litent, Object tipo) {
		this.litent = (LitEnt) litent;
		this.tipo = (Tipo) tipo;

		searchForPositions(litent, tipo);	// Obtener linea/columna a partir de los hijos
	}

	public LitEnt getLitent() {
		return litent;
	}
	public void setLitent(LitEnt litent) {
		this.litent = litent;
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

	private LitEnt litent;
	private Tipo tipo;
}

