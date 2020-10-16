package lang.ast.types;

import lang.ast.NodeVisitor;

public class TyID extends SType {
    
    private String name;
    public TyID(String name){ this.name = name; }

    public String getTyID(){ return name;}

    public boolean match(SType v){
        return (v instanceof TyErr || v instanceof TyID);
    }

    @Override
    public String toString() {
        return name;
    }

    public void accept(NodeVisitor v){ v.visit(this); }
    
}
