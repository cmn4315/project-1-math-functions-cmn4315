package ritmath;

public class FunctionFactory {
    private static Variable var;

    public static MathFunction constant(double arg){
        return new Constant(arg);
    }

    public static MathFunction x(){
        if (var == null){
            var = new Variable();
        }
        return var;
    }

    public static MathFunction sum(MathFunction... args){
        Sum sum = new Sum(args);
        if (sum.isConstant()){
            return new Constant(sum.evaluate(0));
        }
        return sum;
    }
}
