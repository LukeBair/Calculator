package calculator.model.tokens;

import org.jetbrains.annotations.NotNull;

public abstract class TreeToken implements Comparable, Token{
    public abstract int getPrecendence();
    public abstract int getPrecendenceModifier();
    public abstract void setPrecendenceModifier(int x);
    public abstract String getTokenValue();

    @Override
    public int compareTo(@NotNull Object o) {
        if(((TreeToken)o).getPrecendence() > this.getPrecendence()) return 1;
        else if(((TreeToken)o).getPrecendence() < this.getPrecendence()) return -1;
        else return 0;
    }
}
