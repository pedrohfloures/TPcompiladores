package lang.parser;

import java.io.*;
import lang.ast.SuperNode;
import lang.parser.*;
import java.util.List;


// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class LangParseAdaptor implements ParseAdaptor {
   
   public SuperNode parseFile(String path){
      try{
          LangParser langParser = new LangParser();
          SuperNode result = (SuperNode) langParser.parse(new LangScanner( new BufferedReader(new FileReader(path)) ));
          if(langParser.isTainted()){
               return null;
          }
          return result;
      }catch(Exception e){
           e.printStackTrace();
           return null;
      }
   }
   
}
 
