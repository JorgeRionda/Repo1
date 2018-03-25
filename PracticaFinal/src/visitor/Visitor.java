/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Programa node, Object param);
	public Object visit(DefVar node, Object param);
	public Object visit(DefStruct node, Object param);
	public Object visit(Funcion node, Object param);
	public Object visit(Parametro node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(Print node, Object param);
	public Object visit(Printsp node, Object param);
	public Object visit(Println node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(While node, Object param);
	public Object visit(SentIf node, Object param);
	public Object visit(LlamadaFunc node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(ExprArit node, Object param);
	public Object visit(ExprLogic node, Object param);
	public Object visit(ExprIndice node, Object param);
	public Object visit(ExprNot node, Object param);
	public Object visit(ExprComp node, Object param);
	public Object visit(ExprLlamada node, Object param);
	public Object visit(ExprLlamadaFunc node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(Var node, Object param);
	public Object visit(LitEnt node, Object param);
	public Object visit(LitReal node, Object param);
	public Object visit(ExprChar node, Object param);
	public Object visit(TipoInt node, Object param);
	public Object visit(TipoReal node, Object param);
	public Object visit(TipoChar node, Object param);
	public Object visit(TipoIdent node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoVoid node, Object param);
}
