package lang.ast;

import lang.ast.expr.*;
import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.decls.*;

public abstract class NodeVisitor {

  // Root of the tree !
  public abstract void visit(Program d);

  // Declarations
  public abstract void visit(Func d);
  public abstract void visit(TyBind d);
  public abstract void visit(Data d);
  public abstract void visit(Field d);
  
  // Aritmética, lógica e comparações.
  public abstract void visit(Add e);
  public abstract void visit(Sub e);
  public abstract void visit(Mult e);
  public abstract void visit(Div e);
  public abstract void visit(Mod e);
  public abstract void visit(And e);
  public abstract void visit(Not e);
  public abstract void visit(Eq e);
  public abstract void visit(Lt e);
  public abstract void visit(Call e);
  public abstract void visit(Location c);
  public abstract void visit(Index idx);
  public abstract void visit(NullLit e);
  public abstract void visit(BoolLit e);
  public abstract void visit(IntLit e);
  public abstract void visit(CharLit e);
  public abstract void visit(FloatLit e);
  public abstract void visit(Instanciate e);  
  public abstract void visit(Var e);  
  
  // Comandos
  public abstract void visit(Attr c);
  public abstract void visit(Iterate c);
  public abstract void visit(While c);
  public abstract void visit(If c);
  public abstract void visit(Seq c);
  public abstract void visit(CallCmd c);
  public abstract void visit(Print c);
  public abstract void visit(Read c);
  public abstract void visit(Return c);
 
  // Types 
  public abstract void visit(TyVoid ty);
  public abstract void visit(TyInt ty);
  public abstract void visit(TyFloat ty);
  public abstract void visit(TyChar ty);
  public abstract void visit(TyBool ty);
  public abstract void visit(TyID ty);
  public abstract void visit(TyArr ty);
  public abstract void visit(TyErr ty);
  public abstract void visit(TyFun ty);
  
 

}
