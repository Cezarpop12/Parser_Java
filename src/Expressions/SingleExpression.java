package Expressions;

import Token.Token;

public class SingleExpression extends Expression {
    private Token token;
    public SingleExpression(Token Token) {
        this.token = Token;
    }

    public SingleExpression() {
    }

    @Override
    public String toString() {
        String result = "type: " + token.getTokenType() + " " + "value: " + token.getTokenValue();
        return result;
    }
}
