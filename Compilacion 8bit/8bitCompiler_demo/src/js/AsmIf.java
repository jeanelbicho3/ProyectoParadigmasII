package eightBit.js;
import java.util.*;
import java.io.PrintStream;

public class AsmIf implements AsmAst{

   private AsmAst c, t, e;
   public AsmIf(AsmAst c, AsmAst t, AsmAst e){
      this.c = c;
	  this.t = t;
	  this.e = e;
   }
   @Override
   public void genCode(PrintStream out){
     out.format("if(");
	 this.c.genCode(out);
	 out.format(")");
	 this.t.genCode(out);
	 out.format("else ");
	 this.e.genCode(out);
	 out.format(";");
   }
}
