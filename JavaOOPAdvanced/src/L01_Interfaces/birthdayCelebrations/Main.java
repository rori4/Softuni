package L01_Interfaces.birthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Birthable> residentsWithBirthday = new ArrayList<>();
        List<Identifiable> residentsWithId = new ArrayList<>();

        String line;
        while (true){
            if("End".equalsIgnoreCase(line = reader.readLine())){
                break;
            }

            String[] tokens = line.split("\\s+");
            Identifiable resident = null;
            Birthable residentWithBirthday = null;

            switch (tokens[0]) {
                case "Citizen":
                    resident = new Citizen(tokens[1],Integer.parseInt(tokens[2]),tokens[3],tokens[4]);
                    residentWithBirthday = new Citizen(tokens[1],Integer.parseInt(tokens[2]),tokens[3],tokens[4]);
                    break;
                case "Robot":
                    resident = new Robot(tokens[1],tokens[2]);
                    break;
                case "Pet":
                    residentWithBirthday = new Pet(tokens[1],tokens[2]);
                    break;
            }
            if(residentWithBirthday != null){
                residentsWithBirthday.add(residentWithBirthday);

            }
            residentsWithId.add(resident);

        }

        String year = reader.readLine();
        residentsWithBirthday.stream()
                .filter(r -> r.getBirthday().endsWith(year))
                .forEach(r -> System.out.println(r.getBirthday()));
    }
}
