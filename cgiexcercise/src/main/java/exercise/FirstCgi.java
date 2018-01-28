package exercise;

public class FirstCgi  {
    public static void main(String[] args) {
        setContentType();
        setHtmlBody();
    }

    public static void  setContentType(){
        String contentType = "Content-type: text/html \n";
        System.out.println(contentType);
    }

    public static void setHtmlBody(){
        String body = "<html><p>Exercise</p></html>";
        System.out.println(body);
    }
}
