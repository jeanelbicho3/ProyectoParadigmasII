package eightBit.js;
import java.util.*;
import java.io.*;
public class AsmBlock implements AsmAst {
   protected List<AsmAst> members;
   public List<AsmAst> getMembers(){
	   return this.members;
   }
   public AsmBlock(List<AsmAst> members){
      this.members = members;
   }
   public void genCode(PrintStream out){
       this.members.stream().filter(t -> t != null)
	                        .forEach( t -> t.genCode(out));
   }

}
