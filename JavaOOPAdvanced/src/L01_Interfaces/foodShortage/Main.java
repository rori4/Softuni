package L01_Interfaces.foodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        Map<String, Buyer> residents = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            if (residents.containsKey(tokens[0])){
                continue;
            }
            Buyer buyer = null;
            if (tokens.length == 4){
                buyer = new Citizen(tokens[0],Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
            } else {
                buyer = new Rebel(tokens[0], Integer.parseInt(tokens[1]),tokens[2]);
            }
            residents.putIfAbsent(tokens[0],buyer);
        }
        String name;
        while (true){
            if ("End".equalsIgnoreCase(name = reader.readLine())){
                break;
            }
            if (residents.containsKey(name)){
                residents.get(name).buyFood();
            }
        }

        System.out.println(residents.values().stream()
                .mapToInt(Buyer::getFood)
                .sum());

    }
}
