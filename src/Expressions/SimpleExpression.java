package Expressions;

import Token.Token;

public class SimpleExpression extends Expression {
    private String type = "SimpleExpression";
    private Token datatype;
    private Token identifier;
    private Token operator;
    private Token value;

    public SimpleExpression(Token Datatype, Token Identifier, Token Operator, Token Value) {
        this.datatype = Datatype;
        this.identifier = Identifier;
        this.operator = Operator;
        this.value = Value;
    }

    public SimpleExpression() {
    }

    @Override
    public String toString() {
        String result = "Type: " + type + " {" + "\n" + "Middle: " + "type: " + operator.getTokenType() + " " + "value: " + operator.getTokenValue()
                + "\n" + "Lefthandside: " + "type: " + datatype.getTokenType() + " " + "value: " + datatype.getTokenValue()
                + " " + "Identifier: " + identifier.getTokenType() + " " + "value: " + identifier.getTokenValue()
                + "\n" + "Righthandside: " + "type: " + value.getTokenType() + " " + "value: " + value.getTokenValue() + "\n" + "}";
        return result;
    }
}
