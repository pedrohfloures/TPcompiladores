package lang.ast.expr;

import lang.ast.NodeVisitor;

public class FloatLit extends Expr {
   
   private float val; 
   
   public FloatLit(Float b ){
       this.val = b;
   }
   
   public float getValue(){ return val;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
}
