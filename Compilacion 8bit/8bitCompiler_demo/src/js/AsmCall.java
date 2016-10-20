package eightBit.js;
import java.util.*;
import java.io.*;
public class AsmCall implements AsmAst{

   private AsmId f;
   private List<AsmAst> args;

// Aqui hay bronca pero hay algo mas atras
   public AsmCall(AsmId f, AsmAst e){
      
      this(f, Arrays.asList(e));
      System.err.println("AsmCall");
   }
   public AsmCall(AsmId f, List<AsmAst> args){
      this.f = f;
      this.args = args;

   }
   public void genCode(PrintStream out){
       System.err.println("Generando call");
       System.err.println(this.f.getValue());
       out.format("%s(", this.f.getValue());
	   if (this.args != null)
	      this.args
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> f.genCode(out));

	   out.print(")");

	   out.print("; \n");
   }
   
}
