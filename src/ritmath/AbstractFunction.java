package ritmath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractFunction implements MathFunction, Iterable{
    private ArrayList<MathFunction> children;

    protected AbstractFunction(MathFunction... children){
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public boolean isConstant() {
        for (MathFunction child: children){
            if (!child.isConstant()){
                return false;
            }
        }
        return true;
    }

    @Override
    public abstract String toString();

    protected MathFunction get(int i){
        return children.get(i);
    }

    protected int numChildren(){
        return children.size();
    }

    protected void setChildren(MathFunction[] children){
        this.children = new ArrayList<>(Arrays.asList(children));
    }

    public Iterator<MathFunction> iterator(){
        return children.iterator();
    }
}
