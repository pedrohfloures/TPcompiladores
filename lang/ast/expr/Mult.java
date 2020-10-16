package lang.ast.expr;

import lang.ast.NodeVisitor;

public class Mult extends BinOP {
   
   public Mult(Expr left, Expr right ){
       super(left,right);
   }
   public void accept(NodeVisitor v){ v.visit(this);}
}
