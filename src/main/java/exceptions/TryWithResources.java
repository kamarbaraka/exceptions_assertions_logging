package exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * a program to demonstrate the try-with-resources exception handling
 * @author kamar baraka
 * @since 17/06/2023*/
public class TryWithResources {

    private String inFileName;
    private String outFileName;

    public TryWithResources(String inFileName, String outFileName){

        this.inFileName = inFileName;
        this.outFileName = outFileName;
    }

    public String getInFileName(){
        return this.inFileName;
    }

    public void read(){

        var filePath = Path.of(this.inFileName);

        try (var fileContents = new Scanner(filePath, StandardCharsets.UTF_8)){
            while (fileContents.hasNext())
                System.out.println(fileContents.nextLine());
        }
        catch (IOException exception){
            System.err.println("error from read of TWR class");
        }
    }

    public void log(){
        Logger.getLogger("log");
    }

    public void printOut(){
        var inFilePath = Path.of(inFileName);

        try (var in = new Scanner(inFilePath, StandardCharsets.US_ASCII);
             var out = new PrintWriter(outFileName, StandardCharsets.UTF_8)){

            while (in.hasNext())
                out.println(in.nextLine());
        }
        catch (IOException exception){
            System.out.println("i'm very pretty");
        }
    }

    public static void main(String[] args) {

        var file = new TryWithResources("src/main/resources/test.txt", "src/main/resources/out.txt");

        file.read();
        file.printOut();
    }
}

class TryWithResourcesTest{

    public static void main(String[] args) {

        var file = new TryWithResources("src/main/resources/test.txt", "src/main/resources/out.txt");

        file.read();
        file.printOut();
    }
}