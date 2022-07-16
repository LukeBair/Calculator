package calculator.view;

// Import all the stuff from the Calculator.Model package
import calculator.model.*;
import java.util.Scanner;

public final class Input {
    public String getInput() {
        Scanner newScanner = new Scanner(System.in);
        return newScanner.nextLine();
    }
}