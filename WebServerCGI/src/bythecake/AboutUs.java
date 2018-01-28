package bythecake;

public class AboutUs {
    public static void main(String[] args) {
        String type = "Content-Type: text/html"
                + System.lineSeparator();
        String output =
                ""
                        + "<html>"
                        + HtmlElements.HEADER_ELEMENT
                        + "<body>"
                        + "<a href=\"/cgi-bin/menu.cgi\">Back to Menu</a>"
                        + "<h2>Home</h2>";

        //OUR CAKES SECTION
        output += "" +
                "<dl>" +
                    "<dt>ByTheCake EOOD</dt>" +
                    "<dd>Name of the company</dd>" +
                    "<dt>Rangel Stoilov</dt>" +
                    "<dd>Owner</dd>" +
                "</dl>";

        output += "" +
                "<pre style=\"width: 100%; background-color: #f94f80;\">" +
                "City: HongKong                  " + "City: Salzburg\n" +
                "Address: ChoCoLad 18            " + "Address: SchokoLeiden 73\n" +
                "Phone: +78952804429             " + "Phone: +49241432990" +
                "</pre>";

        output +=
                HtmlElements.FOOTER_ELEMENT
                        + "</body>"
                        + "</html>";

        System.out.println(type);
        System.out.println(output);
    }
}
