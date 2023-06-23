package Class;

import Expressions.SimpleExpression;
import Expressions.SingleExpression;
import Expressions.Expression;
import Expressions.BinaryExpression;
import Token.Token;
import Token.TokenType;

import java.util.List;

public class Parser3 {
    private int _cursor;
    private List<Token> tokens;

    public Parser3(List<Token> Tokens) {
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

    public String parse() throws Exception {
        return parse_expression().toString();
    }

    public Expression parse_expression() throws Exception {
        Expression expr = parse_term();
        while (this.getCurrentToken().getTokenType() == TokenType.PLUS || this.getCurrentToken().getTokenType() == TokenType.MINUS) {
            Token operator = this.getCurrentToken();
            Expression operatorExpression = new SingleExpression(operator);
            TokenType tokentype = null;
            if (this.getCurrentToken().getTokenType() == TokenType.PLUS) {
                tokentype = TokenType.PLUS;
                this.eatToken(tokentype);
            } else if (this.getCurrentToken().getTokenType() == TokenType.MINUS) {
                tokentype = TokenType.MINUS;
                this.eatToken(tokentype);
            }
            Expression rightHandSide = this.parse_term();
//            expression = operator.LED(expression, operator, rightHandSide);
            expr = new BinaryExpression(expr, rightHandSide, operatorExpression);
        }
        return expr;
    }

    public Expression parse_term() throws Exception {
        Expression expression = this.NUD();
        while (this.getCurrentToken().getTokenType() == TokenType.MULTIPLY || this.getCurrentToken().getTokenType() == TokenType.DIVIDE) {
            Token operator = this.getCurrentToken();
            Expression operatorExpression = new SingleExpression(operator);
            TokenType tokentype = null;
            if (this.getCurrentToken().getTokenType() == TokenType.MULTIPLY) {
                tokentype = TokenType.MULTIPLY;
                this.eatToken(tokentype);
            } else if (this.getCurrentToken().getTokenType() == TokenType.DIVIDE) {
                tokentype = TokenType.DIVIDE;
                this.eatToken(tokentype);
            }
            Expression rightHandSide = NUD();
//            expression = operator.LED(expression, operator, rightHandSide);
            expression = new BinaryExpression(expression, rightHandSide, operatorExpression);
        }
        return expression;
    }

    public Expression NUD() throws Exception {
        switch (this.getCurrentToken().getTokenType()) {
            case NUMERIC_LITERAL:
                Token numericToken = this.getCurrentToken();
                Expression numericExpression = new SingleExpression(numericToken);
                this.eatToken(this.getCurrentToken().getTokenType());
                return numericExpression;
            case BRACKETS_OPEN:
                this.eatToken((TokenType.BRACKETS_OPEN));
                Expression expression = this.parse_expression();
                this.eatToken(TokenType.BRACKETS_CLOSE);
                return expression;
            case DATATYPE:
                Token datatypeToken = this.getCurrentToken();
                this.eatToken(this.getCurrentToken().getTokenType());
                if (getCurrentToken().getTokenType() == TokenType.IDENTIFIER && getNextToken().getTokenType() == TokenType.EQUAL_TO) {
                    Token identifierToken = this.getCurrentToken();
                    this.eatToken(this.getCurrentToken().getTokenType());
                    Token equalToToken = this.getCurrentToken();
                    this.eatToken(this.getCurrentToken().getTokenType());
                    Expression simpleExpression = new SimpleExpression(datatypeToken, identifierToken, equalToToken, this.getCurrentToken());
                    this.eatToken(this.getCurrentToken().getTokenType());
                    return simpleExpression;
                }
            default:
                throw new Exception("Foei");
        }
    }
}
