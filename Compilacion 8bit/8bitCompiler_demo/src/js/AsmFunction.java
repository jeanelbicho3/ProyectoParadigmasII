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
       /*if(this.name.getValue() != "main"){
           out.print("ERRROR NO MAIN FUNCTION");
           return;
       }else{

       }*/
      // if(this.name.getValue() == "main"){
           /*hacer main y si no viene main*/

        if(this.name.getValue() == "main"){
               out.print("MAINCULEADITO");
        }
           out.print(".init:\n");
                out.print("\tMOV D, 232\n");
                out.print("\tJMP main\n");
                out.print(".main_data:\n");
                out.print(".UNDEF: DB 255\n");
           
      // }
       

	   if (this.body != null)
	      this.body.genCode(out);
	  
   }
}
