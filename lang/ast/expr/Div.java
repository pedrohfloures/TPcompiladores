package lang.ast.expr;

import lang.ast.NodeVisitor;

public class Div extends BinOP {
   
   public Div(Expr left, Expr right ){
       super(left,right);
   }
   public void accept(NodeVisitor v){ v.visit(this);}
}
