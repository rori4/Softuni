package L04_AnnotationsAndEnum.P13_CustomAnnotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true){
            if("END".equalsIgnoreCase(line = reader.readLine())){
                break;
            }
            CustomAnnotation annotation = Wepon.class.getAnnotation(CustomAnnotation.class);
            switch (line) {
                case "Author":
                    System.out.println(String.format("Author: %s",annotation.author()));
                    break;
                case "Revision":
                    System.out.println(String.format("Revision: %s",annotation.revision()));
                    break;
                case "Description":
                    System.out.println(String.format("Class description: %s",annotation.description()));
                    break;
                case "Reviewers":
                    System.out.println(String.format("Reviewers: %s",String.join(", ",annotation.reviewers())));
                    break;
            }
        }
    }
}
