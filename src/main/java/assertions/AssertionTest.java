package assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * a program to demonstrate the proper use of java assertions
 * @author kamar baraka
 * @since 18/06/2023*/
public class AssertionTest {

    public static void main(String[] args) throws IOException {
        //set the logging configuration
        var file = "src/main/resources/logging.properties";
        System.setProperty("java.util.logging.config.file", file);
        LogManager.getLogManager().readConfiguration();

        var logger = Logger.getGlobal();
        logger.entering("assertions.AssertionTest", "main", args);
        logger.severe("the first log");

        var cl = ClassLoader.getPlatformClassLoader();
        cl.setDefaultAssertionStatus(true);
        logger.warning("hello, warning");

        logger.fine("its fine");
        var intArray = new double[] {25, 8, 4, -16, -24, 144};
        var ass = new Assertion(intArray);
        logger.info("just info");

        System.out.println(ass.sqrt());
        logger.exiting("assertions.AssertionTest", "main");
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
        var logger = Logger.getLogger("assertion.Assertion");
        logger.entering("assertion.Assertion", "sqrt", this.intArray);
        var arayList = new ArrayList<Double>();

        for (double i : this.intArray) {
            assert i > 0;
            arayList.add(i);
        }
        arayList.forEach(each -> each = Math.sqrt(each)); //must be positive
        System.out.println(arayList);

        logger.exiting("assertion.Assertion", "sqrt", arayList);
        return arayList;
    }
}
