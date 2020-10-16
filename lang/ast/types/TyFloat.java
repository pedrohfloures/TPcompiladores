package lang.ast.types;

import lang.ast.NodeVisitor;

public class TyFloat extends SType {

    public void accept(NodeVisitor v){ v.visit(this);}

    public boolean match(SType v){
        return (v instanceof TyErr || v instanceof TyFloat);
    }

    @Override
    public String toString() {
        return "Float";
    }
}
