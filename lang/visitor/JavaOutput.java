package lang.visitor;

import lang.ast.*;
import lang.ast.expr.*;
import lang.ast.cmd.*;
import lang.ast.types.*;
import lang.ast.decls.*;
import lang.ast.NodeVisitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class JavaOutput extends NodeVisitor {

    private LinkedList<String> lines;
    private Stack<Object> pilha;
    private HashMap<String, Object> variaveis;
    private String l;
    private String ident;
    private int iterate = 0;

    private void incIdent() {
        ident += "      ";
    }

    private void decIdent() {
        if (ident.length() > 4) {
            ident = ident.substring(0, ident.length() - 4);
        } else {
            ident = "";
        }
        l = null;
    }

    private void putLine(String s) {
        if (l == null) {
            l = ident + "";
        }
        l = l + s + "\n";
        lines.add(l);
        l = null;
    }

    private void putStr(String s) {
        if (l == null) {
            l = ident + "";
        }
        l += s;
    }

    private void flush() {
        if (l != null) {
            lines.add(l + "\n");
            l = null;
        }
    }

    public JavaOutput() {
        lines = new LinkedList<String>();
        pilha = new Stack<>();
        variaveis = new HashMap<>();
        ident = "";
    }

    public void print() {
        for (String l : lines) {
            System.out.print(l);
        }
    }

    // Root of the tree !
    public void visit(Program d) {
        for (Data dx : d.getDataDecs()) {
            dx.accept(this);
        }
        putLine("public class Lang {");
        incIdent();
        flush();
        for (Func fx : d.getFuncList()) {
            fx.accept(this);
        }
        decIdent();
        putLine("}");
    }

    // Declarations
    public void visit(Func d) {
        if (d.getFuncName().equals("main")) {
            putLine("public static void main(String[] args){");
            incIdent();
            flush();
            d.getBody().accept(this);
            decIdent();
            putLine("}");
        } else {
            putStr(d.getFuncName() + "(");
            if (d.getParams().length > 0) {
                d.getParams()[0].accept(this);
                for (int i = 1; i < d.getParams().length; i++) {
                    putStr(", ");
                    d.getParams()[i].accept(this);
                }
            }
            putStr(")");
            if (d.getReturns().length > 0) {
                putStr(":");
                d.getReturns()[0].accept(this);
                for (int j = 1; j < d.getReturns().length; j++) {
                    putStr(", ");
                    d.getReturns()[j].accept(this);
                }
            }
            putStr("{");
            incIdent();
            flush();
            d.getBody().accept(this);
            decIdent();
            putLine("}");
        }
    }

    public void visit(TyBind d) {
        putStr(d.getSecond() + " :: ");
        d.getFirst().accept(this);
    }

    public void visit(Data d) {
        putStr("data ");
        d.getTyID().accept(this);
        putStr(" {");
        incIdent();
        flush();
        if (d.getFields().length > 0) {
            d.getFields()[0].accept(this);
            putLine(";");
            for (int i = 1; i < d.getFields().length; i++) {
                d.getFields()[i].accept(this);
                putStr(";");
                flush();
            }
        }
        decIdent();
        putLine("}");
    }

    public void visit(Field d) {
        putStr(d.getFieldId());
        putStr(" :: ");
        d.getType().accept(this);
    }

    // Aritmética, lógica e comparações.
    public void visit(Add e) {
        e.getLeft().accept(this);
        putStr("+");
        e.getRight().accept(this);
    }

    public void visit(Sub e) {
        e.getLeft().accept(this);
        putStr("-");
        e.getRight().accept(this);
    }

    public void visit(Mult e) {
        e.getLeft().accept(this);
        putStr("*");
        e.getRight().accept(this);
    }

    public void visit(Div e) {
        e.getLeft().accept(this);
        putStr("/");
        e.getRight().accept(this);
    }

    public void visit(Mod e) {
        e.getLeft().accept(this);
        putStr("%");
        e.getRight().accept(this);
    }

    public void visit(And e) {
        e.getLeft().accept(this);
        putStr("&&");
        e.getRight().accept(this);
    }

    public void visit(Not e) {
        putStr("!");
        e.getExpr().accept(this);
    }

    public void visit(Eq e) {
        e.getLeft().accept(this);
        putStr("==");
        e.getRight().accept(this);
    }

    public void visit(Lt e) {
        e.getLeft().accept(this);
        putStr("<");
        e.getRight().accept(this);
    }

    public void visit(Call e) {
        putStr(e.getCalledName() + "(");
        if (e.getArgs().length > 0) {
            e.getArgs()[0].accept(this);
            for (int i = 1; i < e.getArgs().length; i++) {
                putStr(", ");
                e.getArgs()[i].accept(this);
            }
        }
        putStr(")[" + e.getTarget() + "]");
    }


    public void visit(Index idx) {
        putStr("[");
        idx.getIndex().accept(this);
        putStr("]");
    }

    public void visit(NullLit e) {
        putStr("null");
    }


    public void visit(BoolLit e) {
        if (e.getValue()) {
            putStr("true");
        } else {
            putStr("false");
        }
    }

    public void visit(IntLit e) {
        pilha.push(e.getValue());
        putStr(e.getValue() + "");
    }

    public void visit(CharLit e) {
        if (e.getValue() == '\n') {
            putStr("'\\n'");
            return;
        }
        if (e.getValue() == '\b') {
            putStr("'\\b'");
            return;
        }
        if (e.getValue() == '\t') {
            putStr("'\\t'");
            return;
        }
        if (e.getValue() == '\r') {
            putStr("'\\r'");
            return;
        }
        if (e.getValue() == '\\') {
            putStr("'\\\\'");
            return;
        }
        putStr("\"" + e.getValue() + "\"");
    }

    public void visit(FloatLit e) {
        putStr(e.getValue() + "");
    }

    public void visit(Instanciate e) {
        putStr("new ");
        e.getType().accept(this);
        if (e.getSize() != null) {
            putStr("[");
            e.getSize().accept(this);
            putStr("]");
        }
    }

    public void visit(Var e) {
        variaveis.put(e.getVarName(), "Simples");
        putStr(e.getVarName());
    }

    // Comandos
    public void visit(Attr c) {
        c.getLeftSide().accept(this);
        putStr(" = ");
        c.getRightSide().accept(this);
        putLine(";");
    }

    public void visit(Location c) {
        if (c.getBase() != null) {
            c.getBase().accept(this);
            if (!(c.getVal() instanceof Index)) putStr(".");

        }
        // Variável simples
        c.getVal().accept(this);
    }

    public void visit(Iterate c) {
        putStr("for(int iteratevar" + iterate + " = 0; iteratevar" + iterate + " < ");
        c.getCondition().accept(this);
        putLine("; iteratevar" + iterate++ + "++){");
        incIdent();
        if (c.getBody() != null) {
            c.getBody().accept(this);
        }
        decIdent();
        putLine("}");
    }

    public void visit(While c) {
        putStr("while(");
        c.getCondition().accept(this);
        putStr("){");
        flush();
        incIdent();
        if (c.getBody() != null) {
            c.getBody().accept(this);
        }
        decIdent();
        flush();
        putStr("}");
    }


    public void visit(If c) {
        putStr("if(");
        c.getCondition().accept(this);
        putStr("){");
        flush();
        incIdent();
        c.getThenBody().accept(this);
        flush();
        decIdent();
        if (c.getElseBody() != null) {
            putLine("} else {");
            incIdent();
            c.getElseBody().accept(this);
            decIdent();
        }
        flush();
        putStr("}");
    }

    public void visit(Seq c) {
        c.getLeft().accept(this);
        flush();
        c.getRight().accept(this);
        flush();
    }

    public void visit(CallCmd c) {
        putStr(c.getFunName() + "(");
        if (c.getArgs().length > 0) {
            c.getArgs()[0].accept(this);
            for (int i = 1; i < c.getArgs().length; i++) {
                putStr(", ");
                c.getArgs()[0].accept(this);
            }
        }
        putStr(")");
        if (c.getRets().length > 0) {
            putStr("<:");
            c.getRets()[0].accept(this);
            for (int i = 1; i < c.getRets().length; i++) {
                putStr(", ");
                c.getRets()[0].accept(this);
            }
            putStr(":>");
        }
        putLine(";");
    }

    public void visit(Print c) {
        putStr("System.out.print(");
        c.getExpr().accept(this);
        putLine(");");
    }

    public void visit(Read c) {
        putStr("read ");
        c.getLocation().accept(this);
        putLine(";");
    }


    public void visit(Return c) {
        putStr("return ");
        if (c.getExpr().length > 0) {
            c.getExpr()[0].accept(this);
            for (int i = 1; i < c.getExpr().length; i++) {
                c.getExpr()[i].accept(this);
            }
        }
    }

    // Types
    public void visit(TyVoid ty) {
        putStr("Void");
    }

    public void visit(TyInt ty) {
        putStr("Int");
    }

    public void visit(TyFloat ty) {
        putStr("Float");
    }

    public void visit(TyChar ty) {
        putStr("Char");
    }

    public void visit(TyBool ty) {
        putStr("Bool");
    }

    public void visit(TyID ty) {
        putStr(ty.getTyID());
    }

    public void visit(TyArr ty) {
        ty.getTyArg().accept(this);
        putStr("[]");
    }

    @Override
    public void visit(TyErr ty) {

    }

    @Override
    public void visit(TyFun ty) {

    }
}
