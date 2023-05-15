package Expressions;

import Token.Token;

public class NumericLiteralExpression extends Expression {
    private Token token;

    public NumericLiteralExpression(Token Token) {
        this.token = Token;
    }

    public NumericLiteralExpression() {
    }

    @Override
    public String toString() {
        String result = "type: NumericLiteral" + "\n" + "value:" + this.token.getTokenValue();
        return result;
    }
}
