/*
 An interface is just a way to make multiple classes have the same methods,
 So this is mostly for kicks.
 */

package calculator.model;

interface CalculatorInterface {
    // Any class inheriting (extending) from this interface will have a method
    // called "Add" that will return a double and have 2 inputs of type double.
    public double Add(double a, double b);
}