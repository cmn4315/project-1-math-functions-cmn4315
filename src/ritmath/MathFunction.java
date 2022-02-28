package ritmath;

public interface MathFunction {
    public double evaluate(double x);

    public MathFunction derivative();

    public boolean isConstant();

    public double integral(double a, double b, int n);
}
