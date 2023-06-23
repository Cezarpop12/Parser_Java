package Expressions;

import Token.Token;

public class BinaryExpression extends Expression{
    private String type = "BinaryExpression";
    private Expression left;
    private Expression right;
    private Expression middle;

    public BinaryExpression (Expression Left, Expression Right, Expression Middle) {
        this.left = Left;
        this.right = Right;
        this.middle = Middle;

    }

    public BinaryExpression () {
    }

    @Override
    public String toString() {
        String result = "Type: " + type + " {" + "\n" + "Middle: " + middle.toString() + "\n" + "leftHandSide: " + left.toString() + "\n"
                + "Righthandside:" + " " + right.toString() + "\n" + "}";
        return result;
    }
}
