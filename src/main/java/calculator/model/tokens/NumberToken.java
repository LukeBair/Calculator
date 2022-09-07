package calculator.model.tokens;

public class NumberToken extends TreeToken {
    public final int precedence = 4;
    protected int precedenceModifier = 0;
    private final Double value;

    public NumberToken(Double value) {
        this.value = value;
    }

    public Double getValue() { return value; }

    @Override
    public int getPrecendence() {
        return precedence + precedenceModifier;
    }

    @Override
    public int getPrecendenceModifier() {
        return precedenceModifier;
    }

    @Override
    public void setPrecendenceModifier(int x) {
        precedenceModifier = x;
    }

    @Override
    public String getTokenValue() {
        return String.valueOf(getValue());
    }
}
