 
package lang.visitor;

import lang.ast.*;
import lang.ast.expr.*;
import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.decls.*;
import lang.ast.NodeVisitor;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;

public class InterpretVisitor extends NodeVisitor{
   
  private Stack<HashMap<String,Object>> env;
  private Stack<Object> stk;
  private HashMap<String,Func> funcs;
  private HashMap<String,Data> dats;
  private boolean debug;
  private Scanner sc;
  
  public InterpretVisitor(){ 
      env = new Stack<HashMap<String,Object>>();
      stk = new Stack<Object>();
      funcs = new HashMap<String,Func> ();
      dats = new HashMap<String,Data>();
      debug = false;
      sc = new Scanner(System.in);
  }
  
  public InterpretVisitor(boolean debug){ 
        this();
        this.debug = debug;
  }
  
  private void printEnv(){
      System.out.println("---- The marvelous env Debugger ------ ");
      String line;
      for(java.util.Map.Entry<String,Object> e  : env.peek().entrySet()){
          line = e.getKey() + "  :  "+  e.getValue().toString();
          System.out.println(line);
      }
  }
  
  // Root of the tree !
  public  void visit(Program d){ 
     Func m = null;
     for( Data f : d.getDataDecs()){
         dats.put(f.getTyID().getTyID(),f);
     }
     for( Func f : d.getFuncList()){
         funcs.put(f.getFuncName(),f);
         if(f.getFuncName().equals("main")){
             m = f;
         }
     }
     if( m!= null){
         m.accept(this);
     }
  }

  public  void visit(Func d){ 
      HashMap<String,Object> g = new HashMap<String,Object>();
      for(int i = d.getParams().length-1; i >= 0; i--){
          g.put(d.getParams()[i].getSecond(), stk.pop());
      }
      env.push(g);
      d.getBody().accept(this);
      
      if(debug && d.getFuncName().equals("main")){
          printEnv();
      }
      env.pop();
  }
  
  public  void visit(TyBind d){ }
  public  void visit(Data d){ }
  public  void visit(Field d){ }
  
  // Aritmética, lógica e comparações.
  public  void visit(Add e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Float) || (ne instanceof Float)){
         stk.push(new Float(ne.floatValue() + nd.floatValue()) );
      }else{
         stk.push(new Integer(ne.intValue() + nd.intValue()) );
      }
  }
  
  public  void visit(Sub e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Float) || (ne instanceof Float)){
         stk.push(new Float(ne.floatValue() - nd.floatValue()) );
      }else{
         stk.push(new Integer(ne.intValue() - nd.intValue()) );
      }
  }
  
  public  void visit(Mult e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Float) || (ne instanceof Float)){
         stk.push(new Float(ne.floatValue() * nd.floatValue()) );
      }else{
         stk.push(new Integer(ne.intValue() * nd.intValue()) );
      }  
  }
  public  void visit(Div e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Float) || (ne instanceof Float)){
         stk.push(new Float(ne.floatValue() / nd.floatValue()) );
      }else{
         stk.push(new Integer(ne.intValue() / nd.intValue()) );
      }  
  }
  
  public  void visit(Mod e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Integer) && (ne instanceof Integer)){
         stk.push(new Integer(ne.intValue() % nd.intValue()) );
      }
  }
  
  public  void visit(And e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      
      Boolean nd = (Boolean)stk.pop();
      Boolean ne = (Boolean)stk.pop();
      stk.push(new Boolean(ne &&  nd) );
  }
  
  public  void visit(Not e){ 
      e.getExpr().accept(this);
      stk.push(new Boolean(! (Boolean)stk.pop() ) );
  }
  

  public void visit(Eq e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      stk.push( stk.pop().equals(stk.pop()) ) ;
  }
  
  public  void visit(Lt e){ 
      e.getLeft().accept(this);
      e.getRight().accept(this);
      Number nd = (Number)stk.pop();
      Number ne = (Number)stk.pop();
      if( (nd instanceof Float) || (ne instanceof Float)){
         stk.push(new Boolean(ne.floatValue() < nd.floatValue()) );
      }else{
         stk.push(new Boolean(ne.intValue() < nd.intValue()) );
      }  
  }
  
  public  void visit(Call e){
     int targ;
     Object val=null;
     Func f = funcs.get(e.getCalledName());
     if(f != null){  
       for(Expr x : e.getArgs()){
           x.accept(this);
       }
       f.accept(this);
       
       e.getTarget().accept(this);
       targ = (Integer)stk.pop();
       if(f.getReturns() != null && f.getReturns().length > 0){
            for(int j = f.getReturns().length -1; j >=0; j-- ){
                if(j == targ){ 
                    val = stk.pop();
                }else{ stk.pop();}
            }
            stk.push(val);
       }
     }        
  }
  

  private void writeToLocation(Location l, Object o){
        if(l.getBase() == null){
           env.peek().put( ((Var)l.getVal()).getVarName(),o);
        }else{
           l.getBase().accept(this);
           if(stk.peek() instanceof ArrayList){
               l.getVal().accept(this);
               Integer x = (Integer)stk.pop();
               ((ArrayList)stk.pop()).set(x,o);
           }else if( stk.peek() instanceof HashMap){
               ((HashMap)stk.pop()).put( ((Var)l.getVal()).getVarName(), o);
           }
        }  
  }
   
  public  void visit(Location c){ 
        if(c.getBase() == null){
           c.getVal().accept(this);
        }else{
           c.getBase().accept(this);
           if(stk.peek() instanceof ArrayList){
               c.getVal().accept(this);
               Integer x = (Integer)stk.pop();
               stk.push( ((ArrayList)stk.pop()).get(x) );
           }else if( stk.peek() instanceof HashMap){
               stk.push( ((HashMap)stk.pop()).get( ((Var)c.getVal()).getVarName() ) );
           }
        }
  }
  
  public  void visit(Index idx){ 
      idx.getIndex().accept(this);
  }
 
  public  void visit(NullLit e){ stk.push( new Error("Uso de valor nulo") ); }
  public  void visit(BoolLit e){ stk.push(e.getValue()); }
  
  public  void visit(IntLit e){  stk.push(e.getValue()); }
  
  
  public  void visit(CharLit e){ stk.push( e.getValue() ); }
  public  void visit(FloatLit e){ stk.push(e.getValue() ); }
  
  public  void visit(Instanciate e){ 
       if(e.getSize() == null){
          stk.push(new Integer(1));
       }else{
          e.getSize().accept(this);
       }
       e.getType().accept(this);
  }  
  
  public  void visit(Var e){ 
     stk.push(env.peek().get(e.getVarName()));
  }  
  
  // Comandos
  public  void visit(Attr c){  
      c.getRightSide().accept(this);
      writeToLocation(c.getLeftSide(),stk.pop());
  }
  
  public  void visit(Iterate c){ 
       c.getCondition().accept(this);
       Integer k = (Integer)stk.pop();
       for(int i =0; i < k; i++ ){
            c.getBody().accept(this);
       }
  }
  
  public  void visit(While c){ }
  
  public  void visit(If c){ 
     c.getCondition().accept(this);
     if((Boolean)stk.pop()){
        c.getThenBody().accept(this);
     }else if (c.getElseBody() != null){
        c.getElseBody().accept(this); 
     }
  
  }
  
  public  void visit(Seq c){ 
      c.getLeft().accept(this);
      c.getRight().accept(this);
  }
  
  public  void visit(CallCmd c){ 
     Func f = funcs.get(c.getFunName());
     if(f != null){  
       for(Expr e : c.getArgs()){
           e.accept(this);
       }
       f.accept(this);
       for( int  i = c.getRets().length-1; i >= 0; i --){
           writeToLocation(c.getRets()[i], stk.pop());
       }
     }
  }
  
  public  void visit(Print c){ 
      c.getExpr().accept(this);
      System.out.print(stk.pop().toString());
  }
  
  public  void visit(Read c){ 
      try{
          if(sc.hasNextInt() && !sc.hasNext(java.util.regex.Pattern.compile("\\d\\."))){
              stk.push(sc.nextInt());
          }
          else if(sc.hasNextFloat()){
              stk.push(sc.nextFloat() );
          }else{
              stk.push(new Character( sc.next(".").charAt(0) ) );
          }
          writeToLocation(c.getLocation(),stk.pop());
      }catch(Exception e){         
          e.printStackTrace();
          System.out.println("( " + c.getLine() +  ", " + c.getColumn() +") read exception"  );
          System.exit(1);
      }
  }
  
  public  void visit(Return c){ 
     for(Expr e : c.getExpr()){
         e.accept(this);
     }
     // Tem um erro aqui !
  }
  
  // Types 
  public  void visit(TyVoid ty){ }
 
 public  void visit(TyInt ty){ 
       ArrayList<Object> l = new ArrayList<Object>(); 
       int n =  (Integer)stk.pop();
       for(int i =0; i< n; i++){
          l.add(null);
       }
       stk.push(l);
  }
  public  void visit(TyFloat ty){ 
       ArrayList<Object> l = new ArrayList<Object>(); 
       stk.push(l);
  }
  public  void visit(TyChar ty){ 
       ArrayList<Object> l = new ArrayList<Object>(); 
       int n =  (Integer)stk.pop();
       for(int i =0; i< n; i++){
          l.add(null);
       }
       stk.push(l);
  }
  public  void visit(TyBool ty){ 
       ArrayList<Object> l = new ArrayList<Object>();
       int n =  (Integer)stk.pop();
       for(int i =0; i< n; i++){
          l.add(null);
       }
       stk.push(l);
  }
  
  public  void visit(TyID ty){ 
       if(( (Integer)stk.peek()).equals(1) ){
           stk.pop();
           HashMap<String,Object> rec = new HashMap<String,Object>();  
           Data reg = dats.get(ty.getTyID()); 
           for( Field f : reg.getFields()  ){
              rec.put(f.getFieldId(), new Error());
           }
           stk.push(rec); 
       }
       else{
           int n = (Integer)stk.pop();
           ArrayList<HashMap<String,Object>> rec = new ArrayList<HashMap<String,Object>>(n);
           
           for(int i = 0; i < n; i++ ){
              rec.add(null);
           }

           stk.push(rec);
       }
  }
  public  void visit(TyArr ty){ 
     ty.getTyArg().accept(this);
  }

    @Override
    public void visit(TyErr ty) {

    }

    @Override
    public void visit(TyFun ty) {

    }

}
