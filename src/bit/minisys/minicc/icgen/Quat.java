package bit.minisys.minicc.icgen;

import bit.minisys.minicc.parser.ast.ASTNode;

// ��Ԫʽ��ʽ���м����, �������ͷ���ֵ�Ľṹֱ��ʹ��AST�ڵ㣬Ҳ�����Զ���IR�ڵ�
public class Quat {
	private String op;
	private ASTNode res;
	private ASTNode opnd1;
	private ASTNode opnd2;
	private Integer order_id;
	public Quat(String op, ASTNode res, ASTNode opnd1, ASTNode opnd2, Integer order_id) {
		this.op = op;
		this.res = res;
		this.opnd1 = opnd1;
		this.opnd2 = opnd2;
		this.order_id = order_id;
		
	}
	
	public String getOp() {
		return op;
	}
	public ASTNode getOpnd1() {
		return opnd1;
	}
	public ASTNode getOpnd2() {
		return opnd2;
	}
	public ASTNode getRes() {
		return res;
	}
	public void setres(ASTNode n){
		this.res = n;
	}
}
