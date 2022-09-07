package calculator.model.tokens;

import calculator.IllegalCharacterException;

public class OperatorToken extends TreeToken {

    public enum Operators {
        EXPONENT('^'),
        MULTIPLICATION('*'),
        DIVISION ('/'),
        ADDITION('+'),
        SUBTRACTION('-');
        public final char label;
        Operators(char c) {
            this.label = c;
        }
    }

    public final int precedence;
    private final Operators operator;
    protected int precendenceModifier = 0;

    public OperatorToken(Operators op) throws IllegalCharacterException {
        switch (op) {
            case EXPONENT:
                this.operator = Operators.EXPONENT;
                this.precedence = 2;
                break;
            case MULTIPLICATION:
                this.operator = Operators.MULTIPLICATION;
                this.precedence = 1;
                break;
            case DIVISION:
                this.operator = Operators.DIVISION;
                this.precedence = 1;
                break;
            case ADDITION:
                this.operator = Operators.ADDITION;
                this.precedence = 0;
                break;
            case SUBTRACTION:
                this.operator = Operators.SUBTRACTION;
                this.precedence = 0;
                break;
            default:
                throw new IllegalCharacterException();
        }
    }

    public OperatorToken(char op) throws IllegalCharacterException {
        switch (op) {
            case '^':
                this.operator = Operators.EXPONENT;
                this.precedence = 2;
                break;
            case '*':
                this.operator = Operators.MULTIPLICATION;
                this.precedence = 1;
                break;
            case '/':
                this.operator = Operators.DIVISION;
                this.precedence = 1;
                break;
            case '+':
                this.operator = Operators.ADDITION;
                this.precedence = 0;
                break;
            case '-':
                this.operator = Operators.SUBTRACTION;
                this.precedence = 0;
                break;
            default:
                throw new IllegalCharacterException();
        }
    }

    public Operators getOperator() { return operator; }

    @Override
    public int getPrecendence() {
        return precedence + precendenceModifier;
    }

    @Override
    public int getPrecendenceModifier() {
        return precendenceModifier;
    }

    @Override
    public void setPrecendenceModifier(int x) {
        precendenceModifier = x;
    }

    @Override
    public String getTokenValue() {
        return String.valueOf(operator.label);
    }
}
