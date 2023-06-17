package exceptions;

import java.io.IOException;

/**
 * a wrapper exception that wraps exceptions
 * @author kamar baraka
 * @since 16/06/2023
 * */

public class WrapperException
        extends IOException {

    /**
     * the basic constructor*/
    public WrapperException(){}

    /**
     * the detailed constructor
     * @param message the detailed message*/
    public WrapperException(String message){

        super(message);
    }
}
