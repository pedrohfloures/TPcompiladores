package lang.ast.expr;

import lang.ast.NodeVisitor;

public class Location extends Expr {
   
   private Expr val;
   private Expr base;
   
   public Location(Expr base , Expr n){
       this.val = n;
       this.base = base;
   }
   
   public Expr getVal(){ return val;}
   public Expr getBase(){ return base;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
}
