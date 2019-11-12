package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class outputTxt {
    public static void log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
        out.write(message);
        out.close();
    }

    public static void logWithoutPre(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output.txt", false), true);
        out.write(message);
        out.close();
    }
}
