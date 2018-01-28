package A.IntroToJava.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RectangleArea {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");
        double a = Double.valueOf(input[0]);
        double b = Double.valueOf(input[1]);
        double result = a*b;
        System.out.println(String.format("%.2f",result));
    }
}
