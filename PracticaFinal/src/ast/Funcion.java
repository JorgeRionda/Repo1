/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	funcion:definicion -> string:String  parametro:parametro*  tipo:tipo  defvar:defVar*  sentencia:sentencia*

public class Funcion extends AbstractDefinicion {

	public Funcion(String string, List<Parametro> parametro, Tipo tipo, List<DefVar> defvar, List<Sentencia> sentencia) {
		this.string = string;
		this.parametro = parametro;
		this.tipo = tipo;
		this.defvar = defvar;
		this.sentencia = sentencia;

		searchForPositions(parametro, tipo, defvar, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Funcion(Object string, Object parametro, Object tipo, Object defvar, Object sentencia) {
		this.string = (string instanceof Token) ? ((Token)string).getLexeme() : (String) string;
		this.parametro = (List<Parametro>) parametro;
		this.tipo = (Tipo) tipo;
		this.defvar = (List<DefVar>) defvar;
		this.sentencia = (List<Sentencia>) sentencia;

		searchForPositions(string, parametro, tipo, defvar, sentencia);	// Obtener linea/columna a partir de los hijos
	}

	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

	public List<Parametro> getParametro() {
		return parametro;
	}
	public void setParametro(List<Parametro> parametro) {
		this.parametro = parametro;
	}

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<DefVar> getDefvar() {
		return defvar;
	}
	public void setDefvar(List<DefVar> defvar) {
		this.defvar = defvar;
	}

	public List<Sentencia> getSentencia() {
		return sentencia;
	}
	public void setSentencia(List<Sentencia> sentencia) {
		this.sentencia = sentencia;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String string;
	private List<Parametro> parametro;
	private Tipo tipo;
	private List<DefVar> defvar;
	private List<Sentencia> sentencia;
}

