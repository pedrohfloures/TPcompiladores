package lang.ast.decls;

import lang.ast.SuperNode;
import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.expr.Expr;
import lang.ast.NodeVisitor;


//              | Field|    | Field |
// Data Ponto { |Int x |  , | Int y | } 

public class Data extends SuperNode {
   
   private Field[] fields;
   private TyID i;
   
   public Data(TyID t, Field[] fields){
       i = t ;
       this.fields = fields;
   } 
   
   public TyID getTyID(){return i;}
   public Field[] getFields(){return fields;}
   public void accept(NodeVisitor v){ v.visit(this);}
   
}

