
import java.util.*;
import java.io.*;

public class TablaDeSimbolos {

    HashMap hm;
    int auxiliar;

    public TablaDeSimbolos(){
        hm = new HashMap();
        auxiliar = 0;
        hm.put("printString",1);
    }
    public T get(K){
        return hm.get(K);
    }
    public void put(K,V){
        hm.put(K,V);
    }

}