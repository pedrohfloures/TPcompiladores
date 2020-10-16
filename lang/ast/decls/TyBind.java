package lang.ast.decls;

import lang.ast.types.SType;
import lang.ast.SuperNode;
import lang.ast.NodeVisitor;

public class TyBind extends SuperNode{
	private SType x;
	private String y;
	
	public TyBind(SType x, String y) {
		this.x = x;
		this.y = y;
	}
	
	public SType getFirst(){ return x;}
	public String getSecond(){return y;}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TyBind) {
			TyBind pair = (TyBind) obj;
			return x.equals(pair.x) && y.equals(pair.y);
		}
		return false;
	}

	@Override
	public String toString() {
		return  x.toString() + " :: " + y.toString() ;
	}
	
	public void accept(NodeVisitor v){ v.visit(this);}

}
