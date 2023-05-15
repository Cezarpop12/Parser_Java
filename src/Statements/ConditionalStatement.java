package Statements;

import Token.Token;

public class ConditionalStatement {
    private Token keyword;
    private String condition;
    private String body;

    public ConditionalStatement(Token Keyword, String Condition, String Body) {
        this.keyword = Keyword;
        this.condition = Condition;
        this.body = Body;
    }

    public ConditionalStatement() {
    }
}
