package lang.ast;

import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.decls.*;

public class Program extends SuperNode {
   
   private Func[] funcs;
   private Data[] ds;
   
   public Program(Data[] ds, Func[] f){
      funcs = f;
      this.ds = ds; 
   }
   
   public Func[] getFuncList(){ return funcs;}
   public Data[] getDataDecs(){ return ds;}
   
   public void accept(NodeVisitor v){ v.visit(this);}
}

