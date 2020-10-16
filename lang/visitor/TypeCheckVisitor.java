package lang.visitor;

import lang.ast.NodeVisitor;
import lang.ast.Program;
import lang.ast.SuperNode;
import lang.ast.cmd.*;
import lang.ast.decls.Data;
import lang.ast.decls.Field;
import lang.ast.decls.Func;
import lang.ast.decls.TyBind;
import lang.ast.expr.*;
import lang.ast.types.*;

import java.util.Stack;
import java.util.ArrayList;

public class TypeCheckVisitor extends NodeVisitor {

    private TyInt tyInt = new TyInt();
    private TyFloat tyFloat = new TyFloat();
    private TyBool tyBool = new TyBool();
    private TyErr tyErr = new TyErr();
    private TyChar tyChar = new TyChar();
    private TyVoid tyVoid = new TyVoid();

    private ArrayList<String> logError;

    private TyEnv<LocalEnv<SType>> env;
    private LocalEnv<SType> temp;

    private Stack<SType> stk;
    private boolean retChk;

    public TypeCheckVisitor() {
        stk = new Stack<SType>();
        env = new TyEnv<LocalEnv<SType>>();
        logError = new ArrayList<String>();
    }

    public int getNumErrors() {
        return logError.size();
    }

    public void printErrors() {
        for (String s : logError) {
            System.out.println(s);
        }
    }

    @Override
    public void visit(Program d) {
        for (Func f : d.getFuncList()) {
            TyFun ty;
            SType[] vetor_parametros = new SType[f.getParams().length];
            SType[] vetor_retornos = new SType[f.getReturns().length];

            for (int i = 0; i < f.getParams().length; i++) {
                f.getParams()[i].getFirst().accept(this);
                vetor_parametros[i] = stk.pop();
            }
            for (int i = 0; i < f.getReturns().length; i++) {
                f.getReturns()[i].accept(this);
                vetor_retornos[i] = stk.pop();
            }

            env.set(f.getFuncName(), new LocalEnv<SType>(f.getFuncName(), new TyFun(vetor_parametros, vetor_retornos)));
        }
        for (Func f : d.getFuncList()) {
            f.accept(this);
        }
    }

    @Override
    public void visit(Func d) {
        retChk = false;
        temp = env.get(d.getFuncName());
        for (TyBind p : d.getParams()) {
            p.getFirst().accept(this);
            temp.set(p.getSecond(), stk.pop());
        }
        d.getBody().accept(this);
        if (!retChk && d.getReturns().length > 0) {
            logError.add(d.getLine() + ", " + d.getColumn() + ": Função " + d.getFuncName() + " deve retornar algum valor.");
        }
    }

    @Override
    public void visit(TyBind d) {

    }

    @Override
    public void visit(Data d) {

    }

    @Override
    public void visit(Field d) {

    }

    private void typeArithmeticBinOp(SuperNode n, String OpName) {
        SType tyr = stk.pop();
        SType tyl = stk.pop();
        if ((tyr.match(tyInt))) {
            if (tyl.match(tyInt) || tyl.match(tyFloat)) {
                stk.push(tyl);
            } else {
                logError.add(n.getLine() + ", " + n.getColumn() + ": Operador" + OpName + "não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyErr);
            }

        } else if (tyr.match(tyFloat)) {
            if (tyl.match(tyInt) || tyl.match(tyFloat)) {
                stk.push(tyl);
            } else {
                logError.add(n.getLine() + ", " + n.getColumn() + ": Operador " + OpName + " não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
                stk.push(tyErr);
            }
        } else {
            logError.add(n.getLine() + ", " + n.getColumn() + ": Operador " + OpName + " não se aplica aos tipos " + tyl.toString() + " e " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // ADD OK
    @Override
    public void visit(Add e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "+");
    }

    // SUB OK
    @Override
    public void visit(Sub e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "-");
    }

    // MULT OK
    @Override
    public void visit(Mult e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "*");
    }

    // DIV OK
    @Override
    public void visit(Div e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        typeArithmeticBinOp(e, "/");
    }

    // MOD OK
    @Override
    public void visit(Mod e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if (tyr.match(tyInt) && tyl.match(tyInt)) {
            stk.push(tyInt);
        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + "Operador % não se aplica para " + tyl.toString() + "e" + tyr.toString());
            stk.push(tyErr);
        }
    }

    // AND OK
    @Override
    public void visit(And e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if (tyr.match(tyBool) && tyl.match(tyBool)) {
            stk.push(tyBool);
        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + "Operador && não se aplica para " + tyl.toString() + "e" + tyr.toString());
            stk.push(tyErr);
        }
    }

    // NOT OK
    @Override
    public void visit(Not e) {
        e.getExpr().accept(this);
        SType tyr = stk.pop();
        ;

        if (tyr.match(tyBool)) {
            stk.push(tyBool);
        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + "Operador ! não se aplica para " + tyr.toString());
            stk.push(tyErr);
        }
    }

    // EQ OK
    @Override
    public void visit(Eq e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if ((tyr.match(tyInt) || tyr.match(tyFloat)) && (tyl.match(tyInt) || tyr.match(tyFloat))) {
            stk.push(tyBool);
        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + "Operador == não se aplica para " + tyl.toString() + "e" + tyr.toString());
            stk.push(tyErr);
        }
    }

    // MENOR OK
    @Override
    public void visit(Lt e) {
        e.getLeft().accept(this);
        e.getRight().accept(this);
        SType tyr = stk.pop();
        SType tyl = stk.pop();

        if ((tyr.match(tyInt) || tyr.match(tyFloat)) && (tyl.match(tyInt) || tyr.match(tyFloat))) {
            stk.push(tyBool);
        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + "Operador < não se aplica para " + tyl.toString() + "e" + tyr.toString());
            stk.push(tyErr);
        }
    }

    @Override
    public void visit(Call e) {

    }

    @Override
    public void visit(Location c) {

    }

    @Override
    public void visit(Index idx) {

    }

    public void visit(NullLit e) {
        stk.push(tyVoid);
    }

    public void visit(BoolLit e) {
        stk.push(tyBool);
    }

    public void visit(IntLit e) {
        stk.push(tyInt);
    }

    public void visit(CharLit e) {
        stk.push(tyChar);
    }

    public void visit(FloatLit e) {
        stk.push(tyFloat);
    }

    @Override
    public void visit(Instanciate e) {

    }

    // VAR FALTA UMA PARTE
    @Override
    public void visit(Var e) {
        SType t = temp.get(e.getVarName());
        if (t != null) {
//            for(Expr r : e.getVarName() )
//                if(t instanceof TyArr){
//                    t = ((TyArr) t).getTyArg();
//                }else{
//                    t = tyErr;
//                }
//            }

            if (t == tyErr) {
                logError.add(e.getLine() + ", " + e.getColumn() + ": Atribuição incompatível: " + e.getVarName());
                // NÃO TEM PUSH TYERR???
            }
            stk.push(t);

        } else {
            logError.add(e.getLine() + ", " + e.getColumn() + ": Variável não declarada: " + e.getVarName());
            stk.push(tyErr);
        }
    }

    // leftside é o ID??
    @Override
    public void visit(Attr c) {
        /*
        if( temp.get(c.getID().getName()) == null && (c.getID().getIdx() == null || c.getID().getIdx().length == 0) ){

            c.getExp().accept(this);
            temp.set(c.getID().getName(),stk.pop());
        }else{
            c.getID().accept(this);
            c.getExp().accept(this);
            if(! stk.pop().match( stk.pop())){
                logError.add( e.getLine() + ", " + e.getCol() + ": Atribuição ilegal para a variável " + e.getID());
            }
        }
        */
    }

    @Override
    public void visit(Iterate c) {
        c.getCondition().accept(this);
        SType pilha = stk.pop();
        if(!(pilha instanceof TyInt)) logError.add(c.getLine() + ", " + c.getColumn() + " : Iterate funciona somente com tipo Int, tipo fornecido foi " + pilha);
    }

    // WHILE OK
    @Override
    public void visit(While c) {
        c.getCondition().accept(this);
        if (stk.pop().match(tyBool)) {
            c.getBody().accept(this);
        } else {
            logError.add(c.getLine() + ", " + c.getColumn() + ": Expressão de teste do WHILE deve ter tipo Bool");
        }
    }

    // IF OK
    @Override
    public void visit(If c) {
        boolean rt, re;
        re = true;
        c.getCondition().accept(this);
        if (stk.pop().match(tyBool)) {
            retChk = false;
            c.getThenBody().accept(this);
            rt = retChk;
            if (c.getElseBody() != null) {
                retChk = false;
                c.getElseBody().accept(this);
                re = retChk;
            }
            retChk = rt && re;
        } else {
            logError.add(c.getLine() + ", " + c.getColumn() + ": Expressão de teste do IF deve ter tipo Bool");
        }
    }

    @Override
    public void visit(Seq c) {
        c.getLeft().accept(this);
        c.getRight().accept(this);
    }

    // FUDEU
    @Override
    public void visit(CallCmd c) {
        /* ADAPTAR PARA VARIOS RETORNOS
        LocalEnv<SType> le = env.get(c.getFunName());
        if(le != null){
            TyFun tf = (TyFun) le.getFuncType();
            if(c.getArgs().length == tf.getReturns().length)
        }*/
    }

    // PRINT OK
    @Override
    public void visit(Print c) {
        c.getExpr().accept(this);
        stk.pop();
    }

    @Override
    public void visit(Read c) {

    }

    @Override
    public void visit(Return c) {
        for (Expr e : c.getExpr()) e.accept(this);

        if (temp.getFuncType() instanceof TyFun) {
            SType[] t = ((TyFun) temp.getFuncType()).getReturns();

            for (int i = t.length - 1; i >= 0; i--) {
                SType pilha = stk.pop();
                if (!t[i].match(pilha)) {
                    logError.add(c.getLine() + ", " + c.getColumn() + " : Tipo de retorno incompativel, esperado: " + t[i]+ ", atual: " + pilha);
                }
            }

        } else {
            stk.pop().match(temp.getFuncType());
        }
        retChk = true;
    }

    @Override
    public void visit(TyVoid ty) {
        stk.push(tyVoid);
    }

    @Override
    public void visit(TyInt ty) {
        stk.push(tyInt);
    }

    @Override
    public void visit(TyFloat ty) {
        stk.push(tyFloat);
    }

    @Override
    public void visit(TyChar ty) {
        stk.push(tyChar);
    }

    @Override
    public void visit(TyBool ty) {
        stk.push(tyBool);
    }

    @Override
    public void visit(TyID ty) {
    }

    @Override
    public void visit(TyArr ty) {
    }

    @Override
    public void visit(TyErr ty) {

    }

    @Override
    public void visit(TyFun ty) {

    }
}
