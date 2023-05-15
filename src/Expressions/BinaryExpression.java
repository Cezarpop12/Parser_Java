package Expressions;

import Token.Token;

public class BinaryExpression extends Expression{
    private String type = "BinaryExpression";
    private Expression left;
    private Expression right;
    private Token operator;

    public BinaryExpression (Expression Left, Expression Right, Token Operator) {
        this.left = Left;
        this.right = Right;
        this.operator = Operator;
    }

    public BinaryExpression () {
    }

    @Override
    public String toString() {
        String result = "{" + "\n" + "Type: " + type + "\n" + "value: " + operator.getTokenValue() + "\n" + "Lefthandside:" + " " + left.toString()
                + "\n" + "Righthandside:" + " " + right.toString() + "\n" + "}";
        return result;
    }
}
