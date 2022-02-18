package ritmath;

public class Sum extends AbstractFunction{
    public Sum(MathFunction... terms){
        super(terms);
    }

    @Override
    public String toString() {
        String str = "";
        if (numChildren() == 1){
            return get(0).toString();
        }
        for (int i = 0; i < numChildren(); ++i) {
            str += get(i).toString();
            if (i < (numChildren() - 1)){
                str += " + ";
            }
        }
        return "( " + str + " )";
    }

    @Override
    public double evaluate(double x) {
        double total = 0;
        for (int i = 0; i < numChildren(); ++i) {
            total += get(i).evaluate(x);
        }
        return total;
    }

    @Override
    public MathFunction derivative() {
        MathFunction[] derivs = new MathFunction[numChildren()];
        for (int i = 0; i < numChildren(); ++i) {
            derivs[i] = get(i).derivative();
        }
        Sum derivSum = new Sum(derivs);
        if (derivSum.isConstant()){
            return new Constant(derivSum.evaluate(0));
        }
        return derivSum;
    }

    @Override
    public boolean isConstant() {
        return super.isConstant();
    }
}
