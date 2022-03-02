package ritmath;

public class Variable extends AbstractFunction{
    // constructor makes a new variable
    public Variable(){
    }

    /**
     * human readable string for variables
     * @return the string
     */
    @Override
    public String toString() {
        return "x";
    }

    /**
     * evaluates at a given variable
     * @param x the value to evaluate at
     * @return the value of the evaluation
     */
    @Override
    public double evaluate(double x) {
        return x;
    }

    /**
     * derivative of the thing
     * @return the derivative
     */
    @Override
    public MathFunction derivative() {
        return new Constant(1);
    }

    /**
     * tests if variable is constant
     * @return boolean value of constant status
     */
    @Override
    public boolean isConstant() {
        return false;
    }
}
