package ritmath;

public class Sine extends AbstractFunction{

    /**
     * constructor makes a sin object
     * @param term the term to be used inside the sin
     */
    public Sine(MathFunction term){
        super(term);
    }

    /**
     * returns a human readable string of the sin
     * @return the string
     */
    @Override
    public String toString() {
        return "sin( " + get(0).toString() + " )";
    }

    /**
     * evaluates at a certain x value
     * @param x the x value
     * @return the result of the evaluation
     */
    @Override
    public double evaluate(double x) {
        return Math.sin(get(0).evaluate(x));
    }

    /**
     * computes the derivative, making use of product rule
     * @return the derivative
     */
    @Override
    public MathFunction derivative() {
        return FunctionFactory.product(FunctionFactory.cosine(get(0)), get(0).derivative());
    }
}
