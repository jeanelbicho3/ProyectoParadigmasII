package eightBit.js;
import java.util.*;
import java.io.*;
public class AsmCall implements AsmAst{

   private AsmId f;
   private List<AsmAst> args;
   private TablaDeSimbolos tc;
   public static int contCL;

// Aqui hay bronca pero hay algo mas atras
   public AsmCall(AsmId f, AsmAst e){
      
      this(f, Arrays.asList(e));
      System.err.println("AsmCall");
   }
   public AsmCall(AsmId f, List<AsmAst> args){
      this.tc = new TablaDeSimbolos(); 
      this.f = f;
      this.args = args;
      this.contCL = 0;
   }
   public List<AsmAst> getArgs(){
       return args;
        //("QUEDE POR AQUI\n");
       //PASE POR AQUI WEA WEA CULIADA
   }

   /*NO BORRAR PORQUE ES ORO*/
   /*public void genCode(PrintStream out){
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
   }*/
   public void genCode/*culeadito*/(PrintStream out){
       
      /* String args = "ARGS";

       out.print("\n");
       out.print("\t JMP ");
       out.format("%s \n", this.f.getValue());
        
       out.print( args + " : ");
       out.print("\n");
       out.print("\t DB ");

       
	   if (this.args != null)
	      this.args
	          .stream()
	          .filter(f -> f != null)
	          .forEach(f -> f.genCode(out));
        out.print(";\n");      
        out.print("\t DB 0;");

        out.print("\n");
        out.format("%s: ", this.f.getValue());
        out.print("\n");
        */
        //out.print("Hello World culeadito? -> "+args);

        this.args.stream().filter( t -> t != null).forEach(f -> f.genCode(out));
        
        out.print("\n");

        out.print(this.f.getValue()+":\n");
        out.print("\tPOP C\n");
        out.print("\tPOP B\n");
        out.print("\tPUSH C\n");

        //System.err.println(tc.hm.get(this.f.getValue()));
        switch(Integer.parseInt(tc.hm.get(this.f.getValue()).toString())){
            //System.err.println("Pase por aqui");
            case 1: 
                    System.err.println("Pase por print_string");
                    contCL++;
                    out.print(".print_string_loop_"+ contCL + ":\n");
                    //out.print("\n");
                    out.print("\tMOV C, [B]\n");
                    out.print("\tCMP C, 0\n");
                    out.print("\tJE .print_string_exit\n");
                    out.print("\tMOV [D], C\n");
                    out.print("\tINC D\n");
                    out.print("\tINC B\n");
                    out.print("\tJMP .print_string_loop_01\n");

             break;

             default:  break; //Funcion vacia      
  
        }
        out.print("."+this.f.getValue()+":\n");
        out.print("\tPOP C\n");
        out.print("\tPUSH .UNDEF\n");
        out.print("\tPUSH C\n");
        out.print("\tRET\n");
	}
   
}
