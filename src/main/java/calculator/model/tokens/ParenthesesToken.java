package calculator.model.tokens;

import calculator.IllegalCharacterException;

import java.util.ArrayList;

public class ParenthesesToken implements Token {
    public enum Type {
        OPEN('('),
        CLOSED(')');

        public final char label;

        Type (char label) {
            this.label = label;
        }
    }

    public ParenthesesToken(char token) throws IllegalCharacterException {
        switch (token) {
            case '(':
                parenthesesType = Type.OPEN;
                break;
            case ')':
                parenthesesType = Type.CLOSED;
                break;
            default:
                throw new IllegalCharacterException();
        }
    }

    public ParenthesesToken(Type type)
    {
        parenthesesType = type;
    }

    public final Type parenthesesType;

    private ArrayList<Token> nestedTokens;
}
