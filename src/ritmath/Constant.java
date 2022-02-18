package ritmath;

public class Constant extends AbstractFunction{
    private double val;

    public Constant(double val){
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public double evaluate(double x) {
        return val;
    }

    @Override
    public MathFunction derivative() {
        return new Constant(0);
    }

    @Override
    public boolean isConstant() {
        return true;
    }
}
