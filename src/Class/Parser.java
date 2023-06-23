package Class;

import Expressions.SingleExpression;
import Expressions.Expression;
import Expressions.BinaryExpression;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class Parser {
    private int _cursor;
    private List<Token> tokens;

    public Parser(List<Token> Tokens) {
        this.tokens = Tokens;
        this._cursor = 0;
    }

    //Helper functions (Start)
    public Token getCurrentToken() {
        return this.tokens.get(this._cursor);
    }

    public Token getNextToken() {
        return this.tokens.get(this._cursor + 1);
    }

    public void eatToken(TokenType tokenType) throws Exception {
        if (tokenType == this.getCurrentToken().getTokenType()) {
            this._cursor++;
        } else {
            throw new Exception("Expected a token to be of type:" + tokenType + "instead received:" + this.getCurrentToken().getTokenType());
        }
    }
    //Helper functions (End)

    public String parse () throws Exception {
        return this.parse_expression().toString();
    }

    public Expression parse_expression() throws Exception {
        Expression expression = parse_term();
        while (this.getCurrentToken().getTokenType() == TokenType.PLUS || this.getCurrentToken().getTokenType() == TokenType.MINUS) {
            Token operator = this.getCurrentToken();
            Expression operatorExpression = new SingleExpression(operator);
            TokenType tokentype = null;
            if (this.getCurrentToken().getTokenType() == TokenType.PLUS) {
                tokentype = TokenType.PLUS;
                this.eatToken(tokentype);
            }
            else if (this.getCurrentToken().getTokenType() == TokenType.MINUS) {
                tokentype = TokenType.MINUS;
                this.eatToken(tokentype);
            }
            Expression rightHandSide = this.parse_term();
//            expression = operator.LED(expression, operator, rightHandSide);
            expression = new BinaryExpression(expression, rightHandSide, operatorExpression);
        }
        return expression;
    }

    public Expression parse_term() throws Exception {
        Expression expression = this.parse_factor();
        while (this.getCurrentToken().getTokenType() == TokenType.MULTIPLY || this.getCurrentToken().getTokenType() == TokenType.DIVIDE) {
            Token operator = this.getCurrentToken();
            Expression operatorExpression = new SingleExpression(operator);
            TokenType tokentype = null;
            if (this.getCurrentToken().getTokenType() == TokenType.MULTIPLY) {
                tokentype = TokenType.MULTIPLY;
                this.eatToken(tokentype);
            }
            else if (this.getCurrentToken().getTokenType() == TokenType.DIVIDE) {
                tokentype = TokenType.DIVIDE;
                this.eatToken(tokentype);
            }
            Expression rightHandSide = this.parse_factor();
//            expression = operator.LED(expression, operator, rightHandSide);
            expression = new BinaryExpression(expression, rightHandSide, operatorExpression);
        }
        return expression;
    }

    public Expression parse_factor() throws Exception {
        if(this.getCurrentToken().getTokenType() == TokenType.NUMERIC_LITERAL) {
            Token numericLiteral = this.getCurrentToken();
            Expression numericExpression = new SingleExpression(numericLiteral);
            this.eatToken(TokenType.NUMERIC_LITERAL);
            return numericExpression;
//            return numericToken.NUD(literalExpression);
        }
        if(this.getCurrentToken().getTokenType() == TokenType.BRACKETS_OPEN) {
            this.eatToken((TokenType.BRACKETS_OPEN));
            Expression expression = this.parse_expression();
            this.eatToken(TokenType.BRACKETS_CLOSE);
            return expression;
        }
        throw new Exception("Expected a HAAKJEOPEN token or a NUMERICAL token");
    }
//    public String parse(String input) throws Exception {
//        this._input = input;
//        lexer = new Lexer(input);
//        this._lookahead = this.lexer.getNextToken();
//        return this.program();
//    }
//
//    public String program() throws Exception {
//        String result = "type: Program" +
//                 "\n" + " " + "body:" + "\n" + "  " +this.literal();
//        return result;
//    }
//
//    public String literal() throws Exception {
//        switch (this._lookahead.getTokenType()) {
//            case "NUMBER":
//                return this.numericLiteral();
//        }
//        throw new Exception("Literal: unexpected literal production");
//    }
//
//    public String numericLiteral() throws Exception {
//        Token token = this.eat("NUMBER");
//        return token.toString();
//    }
//
//    public String stringLiteral() throws Exception {
//        Token token = this.eat("STRING");
//        return token.toString();
//    }
//
//    public Token eat(String tokenType) throws Exception {
//        Token token = this._lookahead;
//        if (token == null) {
//            throw new Exception("Unexpected end of input, expected:" + tokenType);
//        }
//        if (token.getTokenType() != tokenType) {
//            throw new Exception("Unexpected token:" + token.getTokenValue() + "expected:" + tokenType);
//        }
//        this._lookahead = this.lexer.getNextToken();
//        return token;
//    }
}

