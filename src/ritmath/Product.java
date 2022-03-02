package ritmath;

public class Product extends AbstractFunction {
    /**
     * Constructor makes a new product object
     * @param args the children of the project
     */
    public Product(MathFunction... args){
        super(args);
        if (!isConstant()){normalize();}
    }

    /**
     * returns a human readable string representation of the product
     * @return The string
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
                str += " * ";
            }
        }
        return "( " + str + " )";
    }

    /**
     * evaluates the product at a certain value, multiplying terns and plugging in the value for variables
     * @param x the variable value to evaluate
     * @return the result
     */
    @Override
    public double evaluate(double x) {
        double total = 1;
        for (MathFunction child : (Iterable<MathFunction>) this) {
            total *= child.evaluate(x);
        }
        return total;
    }

    /**
     * computed the derivative using the product rule
     * @return the function representation of the derivative
     */
    @Override
    public MathFunction derivative() {
        if (numChildren() == 0){
            return FunctionFactory.constant(0.0);
        }
        if (numChildren() > 1){
            MathFunction[] rest = new MathFunction[numChildren()-1];
            for (int i = 1; i < numChildren(); ++i){
                rest[i-1] = get(i);
            }
            MathFunction restProduct = FunctionFactory.product(rest);
            MathFunction first = get(0);
            return FunctionFactory.sum(FunctionFactory.product(first, restProduct.derivative()), FunctionFactory.product(first.derivative(), restProduct));
        } else {
            return get(0).derivative();
        }
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
            realListFinal[0] = new Constant(0);
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
            Product constProd = new Product(realListConstants);
            Constant constant = new Constant(constProd.evaluate(0));
            if (constant.evaluate(0) != 1) {
                listFinal[finI] = constant;
                ++finI;
            }
            realListFinal = new MathFunction[finI];
            for(int i = 0; i < finI; ++i){
                if (listFinal[i].evaluate(1) == 0){
                    realListFinal = new MathFunction[1];
                    realListFinal[0] = FunctionFactory.constant(0);
                    break;
                }
                realListFinal[i] = listFinal[i];
            }
        }
        setChildren(realListFinal);
    }
}
