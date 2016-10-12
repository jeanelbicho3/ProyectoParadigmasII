package eightBit.js;
import java.util.*;
import java.io.*;
public class AsmReturn implements AsmAst{

   private AsmAst e;
   public AsmReturn(AsmAst e){
      this.e = e;
   }
   public void genCode(PrintStream out){
      out.print("return ");
	  this.e.genCode();
	  out.print(";");
   }
}
