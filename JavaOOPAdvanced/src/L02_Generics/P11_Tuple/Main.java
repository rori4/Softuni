package L02_Generics.P11_Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = reader.readLine().split("\\s+");
        Tuple<String, String,String> t1 = new Tuple<>(line1[0]+" "+line1[1],line1[2],line1[3]);
        String[] line2 = reader.readLine().split("\\s+");
        Tuple<String, Integer,Boolean> t2 = new Tuple<>(line2[0],Integer.valueOf(line2[1]), (Objects.equals(line2[2], "drunk")));
        String[] line3 = reader.readLine().split("\\s+");
        Tuple<String,Double,String> t3 = new Tuple<>(line3[0],Double.valueOf(line3[1]),line3[2]);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}
