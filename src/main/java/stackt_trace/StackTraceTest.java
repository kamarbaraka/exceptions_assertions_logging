package stackt_trace;

import java.util.Objects;
import java.util.Scanner;

/**
 * a program to compute factorial of a number
 * hence demonstrating walking through a stack trace
 * @author kamar baraka
 * @since 18/06/2023*/

public class StackTraceTest {

    /**
     * the method to compute factorials
     * @param n the number to compute factorial of
     * @return an int value of the factorial*/
    public static int factorial(int n){
        System.out.printf("factorial (%s): \n", n);
        var walker = StackWalker.getInstance();
        walker.forEach(System.out::println);

        int factorial;
        if (n <= 1) factorial = 1;
        else factorial = n * factorial(n - 1);
        System.out.println("return " + factorial);

        return factorial;
    }

    public static void main(String[] args) {

        try (var input = new Scanner(System.in)){
            factorial(input.nextInt());
        }
    }
}
