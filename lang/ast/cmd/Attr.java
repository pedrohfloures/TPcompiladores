package lang.ast.cmd;

import lang.ast.NodeVisitor;
import lang.ast.expr.Expr;
import lang.ast.expr.Location;

public class Attr extends Cmd { 
    private Location vname;
    private Expr e;
    
    public Attr(Location v, Expr e){
        vname = v;
        this.e = e;
    }
    
    public Location getLeftSide(){ return vname;}
    public Expr getRightSide(){ return e;}
    
    public void accept(NodeVisitor v){ v.visit(this);}
}
