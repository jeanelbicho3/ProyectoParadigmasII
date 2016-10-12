package eightBit.js;
import java.io.*;

public interface AsmAst{
   default void genCode(){
      genCode(System.out);
   }
   default void genCode(PrintStream out){
   }
}
