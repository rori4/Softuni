package A.IntroToJava.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriangleArea {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] a = (reader.readLine().split("\\s+"));
        String[] b = (reader.readLine().split("\\s+"));
        String[] c = (reader.readLine().split("\\s+"));
        int Ax = Integer.valueOf(a[0]);
        int Ay = Integer.valueOf(a[1]);
        int Bx = Integer.valueOf(b[0]);
        int By = Integer.valueOf(b[1]);
        int Cx = Integer.valueOf(c[0]);
        int Cy = Integer.valueOf(c[1]);

        int area = (Ax*(By-Cy)+Bx*(Cy-Ay)+Cx*(Ay-By))/2;
        System.out.println(Math.abs(area));
    }
}
