package eightBit.js;
import java.io.*;
public class AsmAssign extends  AsmOperation{
   final public static AsmId EQ = new AsmId("=");
   public AsmAssign(AsmAst left, AsmAst right){
      super(EQ, left, right);
   }
   public void genCode(PrintStream out){
      super.genCode(out);
	  out.print(";");
   }
}
