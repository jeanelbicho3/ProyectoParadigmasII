package eightBit.js;

import java.util.*;
import java.io.*;

public class AsmFunction implements AsmAst{
   static private AsmId UNK = new AsmId("");
   private AsmId name;
   private List<AsmAst> formals;
   private AsmAst body;
   public AsmFunction(List<AsmAst> formals, AsmAst body){
      this(UNK, formals, body);
   }
   public AsmFunction(AsmId name, List<AsmAst> formals, AsmAst body){
      this.formals = formals;
	  this.body = body;
	  this.name = name;
   }
   public void genCode(PrintStream out){
       out.format("function %s(", this.name.getValue());
	   if (this.formals != null)
	      this.formals
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> f.genCode(out));

	   out.print("){");

	   if (this.body != null)
	      this.body.genCode(out);
	   out.print("};");
   }
}
