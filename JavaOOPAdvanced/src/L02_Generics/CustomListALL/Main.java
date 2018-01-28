package L02_Generics.CustomListALL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CustomList<String> list = new CustomList<>();
        while (true){
            String[] line = reader.readLine().split("\\s+");
            if ("end".equalsIgnoreCase(line[0])){
                break;
            }

            switch (line[0]) {
                case "Add":
                    list.add(line[1]);
                    break;
                case "Remove":
                    list.remove(Integer.parseInt(line[1]));
                    break;
                case "Contains":
                    System.out.println(list.contains(line[1]));
                    break;
                case "Swap":
                    list.swap(Integer.valueOf(line[1]),Integer.valueOf(line[2]));
                    break;
                case "Greater":
                    System.out.println(list.countGreaterThan(line[1]));
                    break;
                case "Max":
                    System.out.println(list.getMax());
                    break;
                case "Min":
                    System.out.println(list.getMin());
                    break;
                case "Print":
                    for (String s :list.getMyList()) {
                        System.out.println(s);
                    }
                case "Sort":
                    Sorter.sort(list);
                    break;
            }
        }
    }
}
