package Token;

import Expressions.Expression;
import Expressions.NumericLiteralExpression;

public class Token {
    private TokenType tokenType;
    private String tokenValue;

    public Token(TokenType TokenType, String TokenValue) {
        this.tokenType = TokenType;
        this.tokenValue = TokenValue;
    }

    public Token() {
    }

    public NumericLiteralExpression NUD()
    public TokenType getTokenType() {
        return tokenType;
    }
    public String getTokenValue() {
        return tokenValue;
    }
}
