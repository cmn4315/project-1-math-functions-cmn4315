package ritmath;

public class Sum extends AbstractFunction{
    /**
     * Constructor makes a new sum object
     * @param terms the children of the project
     */
    public Sum(MathFunction... terms){
        super(terms);
        if (!isConstant()){normalize();}
    }

    /**
     * makes a human readable string representing the sum
     * @return the string
     */
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

    /**
     * evaluates the sum at a given variable value
     * @param x the variable value to be used
     * @return the result of the evauation
     */
    @Override
    public double evaluate(double x) {
        double total = 0;
        for (MathFunction child : (Iterable<MathFunction>) this) {
            total += child.evaluate(x);
        }
        return total;
    }

    /**
     * computes the derivative of the sum
     * @return the derivative represented as a MathFunction
     */
    @Override
    public MathFunction derivative() {
        MathFunction[] derivs = new MathFunction[numChildren()];
        for (int i = 0; i < numChildren(); ++i) {
            derivs[i] = get(i).derivative();
        }
        MathFunction derivSum = FunctionFactory.sum(derivs);
        if (derivSum.isConstant()){
            return FunctionFactory.constant(derivSum.evaluate(0));
        }
        return derivSum;
    }

    /**
     * normalizes the children, combining constants into one term
     */
    void normalize(){
        MathFunction[] listConstants = new MathFunction[numChildren()];
        MathFunction[] listFinal = new MathFunction[numChildren()];
        MathFunction[] realListFinal;
        int constI = 0;
        int finI = 0;

        if (numChildren() == 0){
            realListFinal = new MathFunction[1];
            realListFinal[0] = FunctionFactory.constant(0);
        } else {
            for (MathFunction child: (Iterable<MathFunction>) this){
                if (child.isConstant()){
                    listConstants[constI] = child;
                    ++constI;
                } else {
                    listFinal[finI] = child;
                    ++finI;
                }
            }
            MathFunction[] realListConstants = new MathFunction[constI];
            System.arraycopy(listConstants, 0, realListConstants, 0, constI);
            MathFunction constSum = FunctionFactory.sum(realListConstants);
            MathFunction constant = FunctionFactory.constant(constSum.evaluate(0));
            if (constant.evaluate(0) != 0) {
                listFinal[finI] = constant;
                ++finI;
            }
            realListFinal = new MathFunction[finI];
            System.arraycopy(listFinal, 0, realListFinal, 0, finI);
        }
        setChildren(realListFinal);
    }
}
