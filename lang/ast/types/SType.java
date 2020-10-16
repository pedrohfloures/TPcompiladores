package lang.ast.types;

import lang.ast.NodeVisitor;
import lang.ast.SuperNode;

public abstract class SType extends SuperNode{

    public abstract boolean match(SType v);
    
}
