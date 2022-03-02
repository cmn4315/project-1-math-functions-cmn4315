package ritmath;

public class Cosine extends AbstractFunction{

    /**
     * constructor makes a cos object
     * @param term the term to be used inside the cos
     */
    public Cosine(MathFunction term){
        super(term);
    }

    /**
     * returns a human readable string of the cos
     * @return the string
     */
    @Override
    public String toString() {
        return "cos( " + get(0).toString() + " )";
    }

    /**
     * evaluates at a certain x value
     * @param x the x value
     * @return the result of the evaluation
     */
    @Override
    public double evaluate(double x) {
        return Math.cos(get(0).evaluate(x));
    }

    /**
     * computes the derivative, making use of product rule
     * @return the derivative
     */
    @Override
    public MathFunction derivative() {
        return FunctionFactory.product(FunctionFactory.constant((-1)), FunctionFactory.sine(get(0)), get(0).derivative());
    }
}