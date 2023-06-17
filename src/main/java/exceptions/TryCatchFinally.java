package exceptions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class TryCatchFinally {

    private String filename;

    /**
     * the constructor for the class that initialises the class
     * @param filename the filepath of the file*/
    public TryCatchFinally(String filename){

        this.filename = filename;

    }

    /**
     * method to read the contents of a file
     * and print them on the output stream*/
    public void read()
            throws WrapperException{

        var filePath = Path.of(filename);
        try{
            var fileContents = new Scanner(filePath);
            while (fileContents.hasNext()){

                System.out.println(fileContents.nextLine());
            }
        }
        catch (IOException original){
            var exception = new WrapperException("from TryCatchFinally");
            exception.initCause(original);
            throw exception;
        }
        finally {
            System.out.println("thank you");
        }
    }

    /**
     * getter for the instance's filename
     * @return string filename*/
    public String getFilename(){

        return this.filename;
    }
}

class TryCatchFinallyTest{

    public static void main(String... args)
            throws WrapperException{

        var file = new TryCatchFinally("src/main/resources/test.txt");
        file.read();
    }
}