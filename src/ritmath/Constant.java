package ritmath;

public class Constant extends AbstractFunction{
    /**
     * val is the value of the constant
     */
    private double val;

    /**
     * constructor makes the new onstant object
     * @param val da val yoo for da tingy
     */
    public Constant(double val){
        this.val = val;
    }

    /**
     * returns a human readable string of the constant
     * @return the string
     */
    @Override
    public String toString() {
        return String.valueOf(val);
    }

    /**
     * evaluates the constant
     * @param x variable value to evaluate at
     * @return the value of the constant
     */
    @Override
    public double evaluate(double x) {
        return val;
    }

    /**
     * Derivative of the constant
     * @return 0, the derivative of all constants
     */
    @Override
    public MathFunction derivative() {
        return new Constant(0.0);
    }

    /**
     * test for constant status
     * @return true
     */
    @Override
    public boolean isConstant() {
        return true;
    }
}
