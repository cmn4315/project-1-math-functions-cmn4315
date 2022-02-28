package ritmath;

import java.util.ArrayList;

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
        MathFunction sum = null;
        MathFunction[] listConstants = new MathFunction[args.length];
        MathFunction[] listFinal = new MathFunction[args.length];
        int constI = 0;
        int finI = 0;

        if (args.length == 0){
            sum = new Constant(0);
        } else {
            for (MathFunction child: args){
                if (child.isConstant()){
                    listConstants[constI] = child;
                    ++constI;
                } else {
                    listFinal[finI] = child;
                    ++finI;
                }
            }
            Sum constSum = new Sum(listConstants);
            Constant constant = new Constant(constSum.evaluate(0));
            if (constant.evaluate(0) != 0) {
                listFinal[finI] = constant;
            }
            MathFunction[] realListFinal = new MathFunction[finI];
            for(int i = 0; i < finI; ++i){
                realListFinal[i] = listFinal[i];
            }
            sum = new Sum(realListFinal);
        }

        if (sum.isConstant()){
            return new Constant(sum.evaluate(0));
        }
        return sum;
    }
}
