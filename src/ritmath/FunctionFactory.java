package ritmath;

public class FunctionFactory {
    /**
     * the single variable to be used for all functions
     */
    private static Variable var;

    /**
     * creates a constant object
     * @param arg the value for the constant
     * @return the constant object
     */
    public static MathFunction constant(double arg){
        return new Constant(arg);
    }

    /**
     * returns the variable object
     * @return the variable object
     */
    public static MathFunction x(){
        if (var == null){
            var = new Variable();
        }
        return var;
    }

    /**
     * creates a sum object, with some error checking
     * @param args the children of the sum to be made
     * @return the sum object, or a constant if it is constant
     */
    public static MathFunction sum(MathFunction... args){
        if (args.length == 1){
            return args[0];
        }
        MathFunction sum = new Sum(args);
        if (sum.isConstant()){
            return constant(sum.evaluate(0));
        }
        return sum;
    }

    /**
     * creates a product object, with some error checking
     * @param args the children of the product to be made
     * @return the product object, or a constant if it is constant
     */
    public static MathFunction product(MathFunction... args){
        if (args.length == 0){
            return constant(0.0);
        }
        Product product = new Product(args);
        if (product.isConstant()){
            return constant(product.evaluate(0));
        }
        if (product.numChildren() == 1){
            return product.get(0);
        }
        return product;
    }

    public static MathFunction sine(MathFunction args){
        MathFunction sine = new Sine(args);
        if (sine.isConstant()){
            return constant(sine.evaluate(0));
        }
        return sine;
    }

    public static MathFunction cosine(MathFunction args){
        MathFunction cosine = new Cosine(args);
        if (cosine.isConstant()){
            return constant(cosine.evaluate(0));
        }
        return cosine;
    }
}
