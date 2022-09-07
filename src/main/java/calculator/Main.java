package calculator;

import calculator.model.Calculator;
import calculator.view.*;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        Calculator calculator = new Calculator();

        System.out.println("Enter equation:");
        String userInput = input.getInput();
        System.out.println(calculator.evaluate(userInput));
    }
}
