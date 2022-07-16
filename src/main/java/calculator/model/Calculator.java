/*
This class will handle the math part of the model, the addition, multiplcation, exponents, etc.
This class will not handle the splicing of the input string, or the equation tree we came up with.
 */

package calculator.model;

/*
 Final here just means that there should not be any
 child classes of Calculator though this could change at some point.
 The "extends (interface)" means that this class will have to implement any method stated
 in the interface.
 */
public final class Calculator implements CalculatorInterface {

    public double Evaluate(String input) {

        return 0;
    }


    // The implementation of the method "Add" found in the CalculatorInterface
    public double Add(double a, double b) {
        return a + b;
    }
}