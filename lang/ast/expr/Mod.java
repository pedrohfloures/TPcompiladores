package lang.ast.expr;

import lang.ast.NodeVisitor;

public class Mod extends BinOP {
   
   public Mod(Expr left, Expr right ){
       super(left,right);
   }
   public void accept(NodeVisitor v){ v.visit(this);}
}
