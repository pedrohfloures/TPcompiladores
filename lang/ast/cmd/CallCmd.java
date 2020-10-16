package lang.ast.cmd;

import lang.ast.NodeVisitor;
import lang.ast.expr.Expr;
import lang.ast.expr.Location;

public class CallCmd extends Cmd { 

    private String fun;
    private Expr[] args;
    private Location[] vars;
    
    public CallCmd(String fun, Expr[] e, Location[] rets){
        args = e;
        vars = rets;
        this.fun = fun;
    }
    
    public String getFunName(){ return fun;}
    public Expr[] getArgs(){ return args;}
    public Location[] getRets(){ return vars;}
    
    public void accept(NodeVisitor v){ v.visit(this);}
    
}
