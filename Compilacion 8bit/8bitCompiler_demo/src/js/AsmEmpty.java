package eightBit.js;
import java.util.*;
import java.io.*;

public class AsmEmpty implements AsmAst{
   @Override
   public void genCode(PrintStream out){
	   out.println("/* empty statement! */");
   }
}
