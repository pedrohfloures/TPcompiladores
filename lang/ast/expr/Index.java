package lang.ast.expr;

import lang.ast.NodeVisitor;

public class Index extends Expr {
   
   private Expr idx;
   
   public Index(Expr i){
       this.idx = i; 
   }
   
   public Expr getIndex(){ return idx;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
}
