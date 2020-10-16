package lang.ast.expr;

import lang.ast.NodeVisitor;
import lang.ast.types.SType;

public class Instanciate extends Expr {
   
   private SType type; 
   private Expr size;
   
   public Instanciate(SType b, Expr size ){
       this.type = b;
       this.size = size;
   }
   
   public Expr getSize(){ return size;}
   public SType getType(){ return type;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
}
