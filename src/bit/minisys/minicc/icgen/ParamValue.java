package bit.minisys.minicc.icgen;

import bit.minisys.minicc.parser.ast.ASTNode;
import bit.minisys.minicc.parser.ast.ASTVisitor;

public class ParamValue extends ASTNode {
    int loc;


    public String name(){

        return "Loc" + String.valueOf(loc);
    }
    public ParamValue(int loc) {
        super("ParamValue");
        this.loc = loc;
    }

    @Override
    public void accept(ASTVisitor visitor) throws Exception {

    }
}
