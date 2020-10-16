package lang.ast.types;

import lang.ast.NodeVisitor;

public class TyArr extends SType {

    private SType arg;

    public TyArr(SType t) {
        arg = t;
    }

    public SType getTyArg() {
        return arg;
    }

    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public boolean match(SType v) {
        return (v instanceof TyErr || v instanceof TyArr && (arg.match(((TyArr) v).getTyArg())));
    }

    @Override
    public String toString() {
        return arg.toString() + "[]";
    }

}
