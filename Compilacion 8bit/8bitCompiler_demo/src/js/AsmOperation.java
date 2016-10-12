package eightBit.js;
import java.io.*;
public class AsmOperation implements  AsmAst{
   private AsmAst oper;
   private AsmAst left, right;
   public AsmAst getOper(){return this.oper;}
   public AsmAst getLeft(){return this.left;}
   public AsmAst getRight(){return this.right;}
   public AsmOperation(AsmAst oper, AsmAst left, AsmAst right){
      this.oper = oper;
	  this.left = left;
	  this.right = right;
   }
   public void genCode(PrintStream out){
      left.genCode(out);
	  oper.genCode(out);
	  right.genCode(out);
   }
}
