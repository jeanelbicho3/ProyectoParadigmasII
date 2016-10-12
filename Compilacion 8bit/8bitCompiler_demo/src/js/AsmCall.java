package eightBit.js;
import java.util.*;
public class AsmCall implements AsmAst{

   private AsmAst f;
   private List<AsmAst> args;

   public AsmCall(AsmAst f, AsmAst e){
      this(f, Arrays.asList(e));
   }
   public AsmCall(AsmAst f, List<AsmAst> args){
      this.f = f;
      this.args = args;

   }
}
