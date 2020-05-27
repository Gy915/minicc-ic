package bit.minisys.minicc.icgen;

import bit.minisys.minicc.parser.ast.ASTNode;
import bit.minisys.minicc.parser.ast.ASTVisitor;


public class LabelValue extends ASTNode {
    public String name;
    public LabelValue(String name){
        super("LabelValue");
        this.name = name;

    }
    public String name(){
        return "Line "+ name;
    }

    @Override
    public void accept(ASTVisitor visitor) throws Exception {

    }
}
