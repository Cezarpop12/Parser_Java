import Token.Token;
import Class.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
          Lexer lexer = new Lexer("-5 - 5 + -5");
          List <Token> tokens = lexer.getTokens();
          Parser parser = new Parser(tokens);
          String ast = parser.parse();
          System.out.print(ast);
    }
}