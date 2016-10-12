package eightBit.compile;

import eightBit.js.*;
import java.util.*;

public interface AsmEmiter{

   default AsmAst PROGRAM(List<AsmAst> functions){ return new AsmProgram(functions);}

   default AsmAst BLOCK(List<AsmAst> members){ return new AsmBlock(members);}
   default AsmAst BLOCK(){ return new AsmBlock(Arrays.asList());}

   default AsmAst EMPTY(){
	   return new AsmEmpty();
   }

   default AsmNum NUM(double value){ return new AsmNum(value);}

   default AsmId  ID(String value){return new AsmId(value);}

   default AsmFunction FUNCTION(AsmId id, List<AsmAst> formals, AsmAst body){
           return new AsmFunction(id, formals, body);
   }


   default AsmIf IF(AsmAst c, AsmAst t, AsmAst e){
       return new AsmIf(c, e, t);
   }

   default AsmCall CALL(AsmAst f, List<AsmAst> args){
       return new AsmCall(f, args);
   }
   default AsmAst OPERATION(AsmAst oper, AsmAst left, AsmAst right){
	   return new AsmOperation(oper, left, right);
   }
   default AsmAst FOLD_LEFT(AsmAst left, AsmAst right){
	   // Expected right = OPERATION(oper, null, r)
	   AsmOperation rightOperation = (AsmOperation)right;
	   // Returns OPERATION(oper, left, r)
	   return new AsmOperation(rightOperation.getOper(), left, rightOperation.getRight());
   }
   default AsmAst ASSIGN(AsmAst left, AsmAst right){
	   return new AsmAssign(left, right);
   }
   default List<AsmAst> ARGS(List<AsmAst>  args){ return args;}
   //default List<AsmAst> ARGS(AsmAst... args){ return Arrays.asList(args);}
   default List<AsmAst> FORMALS(AsmAst... args){ return Arrays.asList(args);}

	 default AsmString  STRING(String value){return new AsmString(value);}


   default AsmAst RET(AsmAst e){ return new AsmReturn(e);}
   default AsmAst OPER(String op){return new AsmId(op);}
   final AsmBool TRUE = new AsmBool(true);
   final AsmBool FALSE = new AsmBool(false);
   final AsmAst ADD = new AsmId("+");
   final AsmAst MINUS = new AsmId("-");
   final AsmAst MUL = new AsmId("*");
   final AsmAst DIV = new AsmId("/");
   final AsmAst NULL = new AsmId("null");



}
