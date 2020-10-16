package lang.ast.types;

import lang.ast.NodeVisitor;

public class TyErr extends SType {
     
     public boolean match(SType v){
          return true;
     }
     
     public String toString(){
         return "TyError";
     }

    @Override
    public void accept(NodeVisitor v){ v.visit(this);}
}
