package Class;

import Token.Token;
import Token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String _input;
    private int _cursor;

    public Lexer() {
        this._cursor = 0;
    }

    public Lexer(String _Input) {
        this._input = _Input;
        this._cursor = 0;
    }

    //Helper functions (start)
    private boolean canStillTokenize() {
        if (this._cursor < this._input.length()) {
            return true;
        }
        return false;
    }

    private char currentCharValue(String input) {
        char currentChar = input.charAt(this._cursor);
        return currentChar;
    }

    private char nextCharValue(String input) {
        char nextChar = input.charAt(this._cursor + 1);
        return nextChar;
    }

    private boolean currentCharIsNumeric(String input) {
        if (Character.isDigit(input.charAt(this._cursor))) {
            return true;
        }
        return false;
    }

    private boolean nextCharIsNumeric(String input) {
        char nextChar = input.charAt(this._cursor + 1);
        if (Character.isDigit(nextChar)) {
            return true;
        }
        return false;
    }

    private boolean currentCharIsStringLiteral(String input) {
        if (Character.isLetter(input.charAt(this._cursor))) {
            return true;
        }
        return false;
    }

    private boolean commentCheck(String input) {
        if (currentCharValue(input) == '/' && nextCharValue(input) == '/') {
            return true;
        }
        return false;
    }

    private boolean prefixCheck(String input) {
        if (currentCharValue(input) == '-' && nextCharIsNumeric(input) == true) {
            return true;
        }
        return false;
    }
    //Helper functions (end)

    public List<Token> getTokens() throws Exception {
        List<Token> tokens = new ArrayList<>();
        if (canStillTokenize() != true) {
            return null;
        }
        while (canStillTokenize() == true) {
            char input = this._input.charAt(this._cursor);
            switch (input) {
                case ' ':
                case '\n':
                case '\t':
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*"));
                    break;
                case '(':
                    tokens.add(new Token(TokenType.BRACKETS_OPEN, "("));
                    break;
                case ')':
                    tokens.add(new Token(TokenType.BRACKETS_CLOSE, ")"));
                    break;
                default:
                    if (commentCheck(this._input) == true) {
                        while (currentCharValue(this._input) != '\n') {
                            this._cursor++;
                        }
                        this._cursor--;
                    }
                    else if (prefixCheck(this._input) == true) {
                        StringBuilder negativeNumber = new StringBuilder();
                        negativeNumber.append(currentCharValue(this._input));
                        this._cursor++;
                        while (canStillTokenize() == true && currentCharIsNumeric(this._input) == true) {
                            negativeNumber.append(currentCharValue(this._input));
                            this._cursor++;
                        }
                        tokens.add(new Token(TokenType.NUMERIC_LITERAL, negativeNumber.toString()));
                        this._cursor--;
                    }
                    else if (currentCharIsNumeric(this._input) == true) {
                        StringBuilder number = new StringBuilder();
                        while (canStillTokenize() == true && currentCharIsNumeric(this._input) == true) {
                            number.append(currentCharValue(this._input));
                            this._cursor++;
                        }
                        tokens.add(new Token(TokenType.NUMERIC_LITERAL, number.toString()));
                        this._cursor--;
                    }
                    else if (currentCharIsStringLiteral(this._input) == true) {
                        StringBuilder string = new StringBuilder();
                        while (canStillTokenize() == true && currentCharIsStringLiteral(this._input) == true) {
                            string.append(currentCharValue(this._input));
                            this._cursor++;
                        }
                        switch (string.toString()) {
                            case " ":
                                break;
                            case "if":
                                tokens.add(new Token(TokenType.KEYWORD, "if"));
                                break;
                            case "while":
                                tokens.add(new Token(TokenType.KEYWORD, "while"));
                                break;
                            case "for":
                                tokens.add(new Token(TokenType.KEYWORD, "for"));
                                break;
                            case "int":
                                tokens.add(new Token(TokenType.DATATYPE, "int"));
                                break;
                            case "string":
                                tokens.add(new Token(TokenType.DATATYPE, "string"));
                                break;
                            default:
//                                  Token token = tokens.get(tokens.size() - 1);
//                                  if (token.getTokenType() == TokenType.DATATYPE) {
//                                      tokens.add(new Token(TokenType.IDENTIFIER, string.toString()));
//                                  }
//                                  else {
//                                      break;
//                                  }
//                                  this._cursor++;
//                          }
                                tokens.add(new Token(TokenType.IDENTIFIER, string.toString()));
                                break;
                        }
                    }
                    else if (currentCharValue(this._input) == '=' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.IS_THE_SAME, "=="));
                    }
                    else if (currentCharValue(this._input) == '!' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.IS_NOT_THE_SAME, "!="));
                    }
                    else if (currentCharValue(this._input) == '>' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.BIGGER_OR_EQUAL, ">="));
                    }
                    else if (currentCharValue(this._input) == '<' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.SMALLER_OR_EQUAL, "<="));
                    }
                    else if (currentCharValue(this._input) == '|' && nextCharValue(this._input) == '|') {
                        tokens.add(new Token(TokenType.CONDITIONAL_OR, "||"));
                    }
                    else if (currentCharValue(this._input) == '&' && nextCharValue(this._input) == '&') {
                        tokens.add(new Token(TokenType.CONDITIONAL_AND, "&&"));
                    }
                    else if (currentCharValue(this._input) == '+' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.ADD_TO, "+="));
                    }
                    else if (currentCharValue(this._input) == '-' && nextCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.SUBTRACT_FROM, "-="));
                    }
                    else if (currentCharValue(this._input) == '=') {
                        tokens.add(new Token(TokenType.EQUAL_TO, "="));
                    }
                    else if (currentCharValue(this._input) == '>') {
                        tokens.add(new Token(TokenType.BIGGER, ">"));
                    }
                    else if (currentCharValue(this._input) == '<') {
                        tokens.add(new Token(TokenType.SMALLER, "<"));
                    }
                    else if (currentCharValue(this._input) == '-') {
                        tokens.add(new Token(TokenType.MINUS, "-"));
                    }
                    else if (currentCharValue(this._input) == '/') {
                        tokens.add(new Token(TokenType.DIVIDE, "/"));
                    }
                    else {
                        throw new Exception("Expected a valid token at position:" + " " + this._cursor);
                    }
                    break;
                }
                this._cursor++;
            }
            tokens.add(new Token(TokenType.EOF, "EOF"));
            return tokens;
        }
    }