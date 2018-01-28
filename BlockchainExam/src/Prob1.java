import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean found = false;
        int combinations = 0;
        int startInterval  = Integer.parseInt(reader.readLine());
        int endInterval   = Integer.parseInt(reader.readLine());
        int magicNumber  = Integer.parseInt(reader.readLine());

        viciousCycle:
        for (int i = startInterval; i <= endInterval; i++) {
            for (int j = startInterval; j <= endInterval; j++) {
                combinations++;
                if (i+j==magicNumber){
                    System.out.println(String.format("Combination N:%d (%d + %d = %d)",combinations,i,j,magicNumber));
                    found = true;
                    break viciousCycle;
                }
            }
        }
        if (!found){
            System.out.println(String.format("%d combinations - neither equals %d",combinations,magicNumber));
        }
    }
}
