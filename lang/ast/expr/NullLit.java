package lang.ast.expr;

import lang.ast.NodeVisitor;

public class NullLit extends Expr {
   
   public NullLit(){}
   
   public void accept(NodeVisitor v){ v.visit(this); }
}
