/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoVoid:tipo -> 

public class TipoVoid extends AbstractTipo {

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

}

