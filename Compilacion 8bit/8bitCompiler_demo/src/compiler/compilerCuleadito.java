/*
 loriacarlos@gmail.com EIF400 II-2016
 EightBit starting compiler
*/
package eightBit.compile;


import eightBit.js.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;
import java.util.stream.*;


public class compilerCuleadito extends EightBitBaseVisitor<AsmAst> implements AsmEmiter{
   protected AsmAst program;
   public AsmAst getProgram(){
	   return this.program;
   }
   protected List<AsmAst> statements = new ArrayList<>();

   public void genCode(){
      this.statements.stream()
	                 .forEach( t -> t.genCode());
   }
   public AsmAst compile(ParseTree tree){
      return visit(tree);
   }
   @Override
   public AsmAst visitEightProgram(EightBitParser.EightProgramContext ctx){
	   ctx.eightFunction().stream()
	                      .forEach( fun -> visit(fun) );
	   return this.program = PROGRAM(this.statements);
   }
   @Override
   public AsmAst visitEightFunction(EightBitParser.EightFunctionContext ctx){

      AsmId id = (AsmId)visit(ctx.id());
	  AsmAst f = visit(ctx.formals());
	  AsmAst body = visit(ctx.funBody());
	  AsmAst function = FUNCTION(id, FORMALS(f), body);
	  this.statements.add(function);
	  return function;
   }
   @Override
   public AsmAst visitEmptyStatement(EightBitParser.EmptyStatementContext ctx){
      return EMPTY();

   }

   @Override
   public AsmAst visitReturnStatement(EightBitParser.ReturnStatementContext ctx){
      return RET(visit(ctx.expr()));

   }
   @Override
   public AsmAst visitAssignStatement(EightBitParser.AssignStatementContext ctx){
	  return ASSIGN(visit(ctx.id()), visit(ctx.expr()));

   }
   @Override
   public AsmAst visitBlockStatement(EightBitParser.BlockStatementContext ctx){
	  EightBitParser.ClosedListContext closedList = ctx.closedList();
      return (closedList == null ) ? BLOCK()
	                               : visit(closedList);
   }
   @Override
   public AsmAst visitClosedList(EightBitParser.ClosedListContext ctx){
					   return  BLOCK(ctx.closedStatement().stream()
	                                                      .map( c -> visit(c))
										                  .collect(Collectors.toList()));

   }
   @Override
   public AsmAst visitFormals(EightBitParser.FormalsContext ctx){
	   EightBitParser.IdListContext idList = ctx.idList();
	   return (idList == null ) ? BLOCK()
	                            : visit(idList);
   }
   @Override
   public AsmAst visitIdList(EightBitParser.IdListContext ctx){
	   return  BLOCK(ctx.id().stream()
						     .map( c -> visit(c))
						     .collect(Collectors.toList()));

   }
   @Override
   public AsmAst visitId(EightBitParser.IdContext ctx){
	  return  ID(ctx.ID().getText());
   }
   @Override
    public AsmAst visitArithOperation(EightBitParser.ArithOperationContext ctx) {
	   if (ctx.oper == null)
		   return visit(ctx.arithMonom().get(0));
	   AsmAst oper = ( ctx.oper.getType() == EightBitParser.ADD ) ? ADD : MINUS;
       List<AsmAst> exprs = ctx.arithMonom().stream()
	                                       .map( c -> visit(c) )
										   .collect(Collectors.toList());
	   return exprs.stream()
	               .skip(1)
				   .reduce(exprs.get(0), (opers, expr) ->
	                              OPERATION(oper, opers , expr));

    }
    @Override
    public AsmAst visitArithMonom(EightBitParser.ArithMonomContext ctx){
		//System.err.println(" ArithMonom " + ctx.getText());
		AsmAst left = visit(ctx.arithSingle());
		return (ctx.operTDArithSingle() == null)
		       ? left
		       :ctx.operTDArithSingle().stream()
	                                   .map( c -> visit(c) )
									   .reduce(left, (opers, expr)
									                      -> FOLD_LEFT(opers , expr));
   }
   @Override
   public AsmAst visitOperTDArithSingle(EightBitParser.OperTDArithSingleContext ctx){
	   //System.err.println(" OperTDArithSingle " + ctx.getText() + ctx.oper);
	   AsmAst oper = ( ctx.oper.getType() == EightBitParser.MUL ) ? MUL : DIV;
	   AsmAst right = visit(ctx.arithSingle());
	   return OPERATION(oper, NULL, right);
   }
   @Override
   public AsmAst visitArithIdSingle(EightBitParser.ArithIdSingleContext ctx){
      return visit(ctx.id()); // ignoring by now arguments!!
   }
   @Override
   public AsmAst visitExprNum(EightBitParser.ExprNumContext ctx){
      return NUM(Double.valueOf(ctx.NUMBER().getText()));
   }
   @Override
   public AsmAst visitExprTrue(EightBitParser.ExprTrueContext ctx){
      return TRUE;
   }
   @Override
   public AsmAst visitExprFalse(EightBitParser.ExprFalseContext ctx){
      return FALSE;
    }

    public AsmAst visitPrintString(EightBitParser.ExprStringContext ctx){

      return  STRING(ctx.STRING().getText());

    }


}
