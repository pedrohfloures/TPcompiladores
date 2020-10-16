package lang.ast.cmd;

import lang.ast.NodeVisitor;
import lang.ast.expr.Expr;

public class Return extends Cmd { 
    private Expr[] ex;
    
    public Return(Expr[] ex){
        this.ex = ex;
    }
    
    public Expr[] getExpr(){ return ex;}
    
    public void accept(NodeVisitor v){ v.visit(this);}
}
