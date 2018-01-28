package L01_Interfaces.borderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Identifiable> residents = new ArrayList<>();

        String line;
        while (true){
            if("End".equalsIgnoreCase(line = reader.readLine())){
                break;
            }

            String[] tokens = line.split("\\s+");
            Identifiable resident = null;
            if(tokens.length == 3){
                resident = new Citizen(tokens[0],Integer.parseInt(tokens[1]),tokens[2]);
            } else {
                resident = new Robot(tokens[0],tokens[1]);
            }
            residents.add(resident);
        }

        String lastDigitsOfId = reader.readLine();
        residents.stream()
                .filter(r -> r.getId().endsWith(lastDigitsOfId))
                .forEach(r -> System.out.println(r.getId()));
    }
}
