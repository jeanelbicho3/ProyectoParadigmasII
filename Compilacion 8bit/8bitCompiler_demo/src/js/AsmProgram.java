package eightBit.js;
import java.util.*;
import java.io.*;
public class AsmProgram implements AsmAst{
   static private AsmId UNK = new AsmId("");
   private AsmId name;
   private List<AsmAst> functions;
   private AsmAst body;
   public AsmProgram(List<AsmAst> functions){
      this(UNK, functions);
   }
   public AsmProgram(AsmId name, List<AsmAst> functions){
      this.functions = functions;
	  this.name = name;
   }
   public void genCode(PrintStream out){
       functions.stream().forEach( t -> t.genCode(out));
   }
}
