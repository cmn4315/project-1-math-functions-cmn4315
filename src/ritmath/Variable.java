package ritmath;

public class Variable extends AbstractFunction{
    public Variable(){
    }

    @Override
    public String toString() {
        return "x";
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public MathFunction derivative() {
        return new Constant(1);
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}
