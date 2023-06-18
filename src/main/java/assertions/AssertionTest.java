package assertions;

import java.util.ArrayList;

/**
 * a program to demonstrate the proper use of java assertions
 * @author kamar baraka
 * @since 18/06/2023*/
public class AssertionTest {

    public static void main(String[] args) {
        var cl = ClassLoader.getPlatformClassLoader();
        cl.setDefaultAssertionStatus(true);

        var intArray = new double[] {25, 8, 4, -16, -24, 144};
        var ass = new Assertion(intArray);

        System.out.println(ass.sqrt());
    }
}

class Assertion{

    private double[] intArray;

    /**
     * the constructor, takes an integer array of positive integers
     * @param intArray must be an array of positive integers*/
    public Assertion(double[] intArray){
        this.intArray = intArray;
    }

    public ArrayList<Double> sqrt(){
        var arayList = new ArrayList<Double>();

        for (double i : this.intArray) {
            assert i > 0;
            arayList.add(i);
        }
        arayList.forEach(each -> each = Math.sqrt(each)); //must be positive
        System.out.println(arayList);

        return arayList;
    }
}
