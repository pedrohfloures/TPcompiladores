package lang.ast.decls;

import lang.ast.SuperNode;
import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.expr.Expr;
import lang.ast.NodeVisitor;

public class Func extends SuperNode {
   
   private SType[] returns;
   private String fname;
   private TyBind[] params;
   private Cmd body;
   
   public Func(SType[] rt, String s, TyBind[] params, Cmd b ){
       returns = rt;
       fname = s;
       this.params = params;
       body = b;
   }
   
   public Cmd getBody(){return body;}
   public SType[] getReturns(){return returns;}
   public TyBind[] getParams(){return params;}
   public String getFuncName(){return fname;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
   
}

