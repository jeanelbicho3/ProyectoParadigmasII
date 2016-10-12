package eightBit.js;
import java.io.*;
public class AsmPrintString<T> implements AsmAst{
   private T value;
   public T getValue(){return this.value;}

   public AsmPrintString(T value){
      this.value = value;
   }
   public void genCode(PrintStream out){
      //out.print(this.value);
      out.print("La wea rara");
   }
}
