package Token;

import Expressions.BinaryExpression;
import Expressions.Expression;

public class Token {
    private TokenType tokenType;
    private String tokenValue;

    public Token(TokenType TokenType, String TokenValue) {
        this.tokenType = TokenType;
        this.tokenValue = TokenValue;
    }

    public Token() {
    }

    public TokenType getTokenType() {
        return tokenType;
    }
    public String getTokenValue() {
        return tokenValue;
    }
}
