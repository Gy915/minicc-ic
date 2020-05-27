package bit.minisys.minicc.icgen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bit.minisys.minicc.parser.ast.*;
// 一个简单样例，只实现了加法
public class ExampleICBuilder implements ASTVisitor{

	private Map<ASTNode, ASTNode> map;				// 使用map存储子节点的返回值，key对应子节点，value对应返回值，value目前类别包括ASTIdentifier,ASTIntegerConstant,TemportaryValue...
	public List<Quat> quats;						// 生成的四元式列表
	private Integer tmpId;							// 临时变量编号
	public Order_Table order_table;
	public Integer order_id;
	public Integer cond_num;

	public String Cond_name(boolean flag){
		String name ="Cond" + Integer.toString(cond_num);
		if(flag){
			name = name + "true";
		}
		else{
			name = name + "false";
		}

		return name;

	}
	public ExampleICBuilder() {
		map = new HashMap<ASTNode, ASTNode>();
		quats = new LinkedList<Quat>();
		tmpId = 0;
		order_table = new Order_Table();
		order_id = 0;
		cond_num = 0;
	}
	public List<Quat> getQuats() {
		return quats;
	}

	@Override
	public void visit(ASTCompilationUnit program) throws Exception {
		for (ASTNode node : program.items) {
			if(node instanceof ASTFunctionDefine)
				visit((ASTFunctionDefine)node);
		}
	}

	@Override
	public void visit(ASTDeclaration declaration) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTArrayDeclarator arrayDeclarator) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTVariableDeclarator variableDeclarator) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTFunctionDeclarator functionDeclarator) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTParamsDeclarator paramsDeclarator) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTArrayAccess arrayAccess) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTBinaryExpression binaryExpression) throws Exception {
		String op = binaryExpression.op.value;
		ASTNode res = null;
		ASTNode opnd1 = null;
		ASTNode opnd2 = null;
		
		if (op.equals("=")) {
			// 赋值操作
			// 获取被赋值的对象res
			visit(binaryExpression.expr1);
			res = map.get(binaryExpression.expr1);
			// 判断源操作数类型, 为了避免出现a = b + c; 生成两个四元式：tmp1 = b + c; a = tmp1;的情况。也可以用别的方法解决
			if (binaryExpression.expr2 instanceof ASTIdentifier) {
				opnd1 = binaryExpression.expr2;
			}else if(binaryExpression.expr2 instanceof ASTIntegerConstant) {
				opnd1 = binaryExpression.expr2;
			}else if(binaryExpression.expr2 instanceof ASTBinaryExpression) {
				ASTBinaryExpression value = (ASTBinaryExpression)binaryExpression.expr2;
				op = value.op.value;
				visit(value.expr1);
				opnd1 = map.get(value.expr1);
				visit(value.expr2);
				opnd2 = map.get(value.expr2);
			}else {
				// else ...
			}
			
		}else if (op.equals("+")||op.equals("-")||op.equals(">")||op.equals("<")) {
			// 加法操作，结果存储到中间变量
			res = new TemporaryValue(++tmpId);
			visit(binaryExpression.expr1);
			opnd1 = map.get(binaryExpression.expr1);
			visit(binaryExpression.expr2);
			opnd2 = map.get(binaryExpression.expr2);
		}
		
		// build quat
		Quat quat = new Quat(op, res, opnd1, opnd2, order_id++);

		quats.add(quat);
		map.put(binaryExpression, res);
	}

	@Override
	public void visit(ASTBreakStatement breakStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTContinueStatement continueStatement) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTCastExpression castExpression) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTCharConstant charConst) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTCompoundStatement compoundStat) throws Exception {
		for (ASTNode node : compoundStat.blockItems) {
			if(node instanceof ASTDeclaration) {
				// visit((ASTDeclaration)node);
			}else if (node instanceof ASTStatement) {
				visit((ASTStatement)node);
			}
		}
		
	}

	@Override
	public void visit(ASTConditionExpression conditionExpression) throws Exception {
		// TODO Auto-generated method stub
		





		
	}

	@Override
	public void visit(ASTExpression expression) throws Exception {
		if(expression instanceof ASTArrayAccess) {
			visit((ASTArrayAccess)expression);
		}else if(expression instanceof ASTBinaryExpression) {
			visit((ASTBinaryExpression)expression);
		}else if(expression instanceof ASTCastExpression) {
			visit((ASTCastExpression)expression);
		}else if(expression instanceof ASTCharConstant) {
			visit((ASTCharConstant)expression);
		}else if(expression instanceof ASTConditionExpression) {
			visit((ASTConditionExpression)expression);
		}else if(expression instanceof ASTFloatConstant) {
			visit((ASTFloatConstant)expression);
		}else if(expression instanceof ASTFunctionCall) {
			visit((ASTFunctionCall)expression);
		}else if(expression instanceof ASTIdentifier) {
			visit((ASTIdentifier)expression);
		}else if(expression instanceof ASTIntegerConstant) {
			visit((ASTIntegerConstant)expression);
		}else if(expression instanceof ASTMemberAccess) {
			visit((ASTMemberAccess)expression);
		}else if(expression instanceof ASTPostfixExpression) {
			visit((ASTPostfixExpression)expression);
		}else if(expression instanceof ASTStringConstant) {
			visit((ASTStringConstant)expression);
		}else if(expression instanceof ASTUnaryExpression) {
			visit((ASTUnaryExpression)expression);
		}else if(expression instanceof ASTUnaryTypename){
			visit((ASTUnaryTypename)expression);
		}
	}

	@Override
	public void visit(ASTExpressionStatement expressionStat) throws Exception {
		for (ASTExpression node : expressionStat.exprs) {
			visit((ASTExpression)node);
		}
	}

	@Override
	public void visit(ASTFloatConstant floatConst) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ASTFunctionCall funcCall) throws Exception {
		// TODO Auto-generated method stub




		//param
		int i = 0;
		for(ASTExpression e:funcCall.argList){
			String op = "param";
			ASTNode res = new ParamValue(i++);
			ASTNode opnd1 = null;
			ASTNode opnd2 = null;
			visit(e);
			opnd1 = map.get(e);

		}

		//call
		

		//retrieve



	}

	@Override
	public void visit(ASTGotoStatement gotoStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTIdentifier identifier) throws Exception {
		map.put(identifier, identifier);
	}

	@Override
	public void visit(ASTInitList initList) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTIntegerConstant intConst) throws Exception {
		map.put(intConst, intConst);
	}

	@Override
	public void visit(ASTIterationDeclaredStatement iterationDeclaredStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTIterationStatement iterationStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTLabeledStatement labeledStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTMemberAccess memberAccess) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTPostfixExpression postfixExpression) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTReturnStatement returnStat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTSelectionStatement selectionStat) throws Exception {
		// TODO Auto-generated method stub
		for(ASTExpression e: selectionStat.cond){
			visit((ASTExpression)e);
		}
		String true_name = Cond_name(true);
		order_table.add_unsure(true_name, order_id);
		LabelValue lvT = new LabelValue(true_name);
		Quat qT = new Quat("JT", lvT, null, null, order_id++ );
		quats.add(qT);

		String false_name = Cond_name(false);
		order_table.add_unsure(false_name, order_id);
		LabelValue lvF = new LabelValue(false_name);
		Quat qF = new Quat("JF", lvF, null, null, order_id++ );
		quats.add(qF);

		//visit true
		int true_label = order_id;
		order_table.Back_fill(true_name, true_label);
		ASTCompoundStatement csT = (ASTCompoundStatement)selectionStat.then;
		visit(csT);

		int false_label = order_id;
		order_table.Back_fill(false_name, false_label);
		ASTCompoundStatement csF = (ASTCompoundStatement)selectionStat.otherwise;
		visit(csF);

		
	}

	@Override
	public void visit(ASTStringConstant stringConst) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTTypename typename) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTUnaryExpression unaryExpression) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTUnaryTypename unaryTypename) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTFunctionDefine functionDefine) throws Exception {
		visit(functionDefine.body);
	}

	@Override
	public void visit(ASTDeclarator declarator) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ASTStatement statement) throws Exception {
		if(statement instanceof ASTIterationDeclaredStatement) {
			visit((ASTIterationDeclaredStatement)statement);
		}else if(statement instanceof ASTIterationStatement) {
			visit((ASTIterationStatement)statement);
		}else if(statement instanceof ASTCompoundStatement) {
			visit((ASTCompoundStatement)statement);
		}else if(statement instanceof ASTSelectionStatement) {
			visit((ASTSelectionStatement)statement);
		}else if(statement instanceof ASTExpressionStatement) {
			visit((ASTExpressionStatement)statement);
		}else if(statement instanceof ASTBreakStatement) {
			visit((ASTBreakStatement)statement);
		}else if(statement instanceof ASTContinueStatement) {
			visit((ASTContinueStatement)statement);
		}else if(statement instanceof ASTReturnStatement) {
			visit((ASTReturnStatement)statement);
		}else if(statement instanceof ASTGotoStatement) {
			visit((ASTGotoStatement)statement);
		}else if(statement instanceof ASTLabeledStatement) {
			visit((ASTLabeledStatement)statement);
		}
	}

	@Override
	public void visit(ASTToken token) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
