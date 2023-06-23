import Class.*;
import Token.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
          Lexer lexer = new Lexer("(4 + -2) - 3 * -2");
//        Lexer lexer = new Lexer("string woord = \"World\"");
          List <Token> tokens = lexer.getTokens();
          Parser3 parser = new Parser3(tokens);
          String ast = parser.parse();
          System.out.print(ast);
    }
}