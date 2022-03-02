package ritmath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractFunction implements MathFunction, Iterable{
    /**
     * children stores the children of a function
     */
    private ArrayList<MathFunction> children;

    /**
     * Constructor adds children to the child array list
     * @param children the children for the new function
     */
    protected AbstractFunction(MathFunction... children){
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    /**
     * Checks if the function is constant by recursively checking all children
     * @return boolean for whether or not the function is constant
     */
    @Override
    public boolean isConstant() {
        for (MathFunction child: children){
            if (!child.isConstant()){
                return false;
            }
        }
        return true;
    }

    /**
     * returns a human readable string representation of the function
     * @return the string
     */
    @Override
    public abstract String toString();

    /**
     * gets the child at a particular index
     * @param i the index of that child
     * @return the child at that index
     */
    protected MathFunction get(int i){
        return children.get(i);
    }

    /**
     * returns the number of children
     * @return the number of children
     */
    protected int numChildren(){
        return children.size();
    }

    /**
     * sets the children arraylist to a new arraylist
     * @param children the new list of children
     */
    protected void setChildren(MathFunction[] children){
        this.children = new ArrayList<>(Arrays.asList(children));
    }

    /**
     * iterator does iteration things
     * @return the iterator object
     */
    public Iterator<MathFunction> iterator(){
        return children.iterator();
    }

    /**
     * integrates the function on an interval using trapezoid method approximation
     * @param a starting point
     * @param b ending point of interval
     * @param n number of intervals
     * @return the approximation of the integral
     */
    public double integral(double a, double b, int n){
        double delta = (b - a)/n;
        double total = 0.0;
        for (int i = 0; i < n; ++i){
            double j = (a + (delta*i));
            total += ((delta * (evaluate(j) + evaluate((j + delta))))/2);
        }
        return total;
    }
}
