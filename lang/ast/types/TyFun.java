package lang.ast.types;

import lang.ast.NodeVisitor;
import lang.ast.SuperNode;

public class TyFun extends SType {

    private SType args[];
    private SType returns[];

    public TyFun(SType args[], SType returns[]) {
        this.args = args;
        this.returns = returns;
    }

    public SType[] getArgs() {
        return args;
    }

    public SType[] getReturns(){
        return returns;
    }


    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    public boolean match(SType v) {
        boolean r = false;
        if (v instanceof TyFun)
            if (((TyFun) v).getArgs().length == args.length) {
                r = true;
                for (int i = 0; i < args.length; i++) r = r && args[i].match(((TyFun) v).getArgs()[i]);
            }
        return r;
    }
}
