import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

public class Server {
    private int port;
    private ServerSocket server;
    private HashMap<String, String> supportedConentTypes;

    public Server(int port) {
        this.port = port;
        this.supportedConentTypes = new HashMap<>();
        this.seedSupportedContentTypes();
    }

    private void seedSupportedContentTypes() {
        this.supportedConentTypes.put("png", "image/png");
        this.supportedConentTypes.put("jpg", "image/jpeg");
        this.supportedConentTypes.put("jpeg", "image/jpeg");
        this.supportedConentTypes.put("html", "text/html");
    }

    public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        System.out.println("Listening on port: " + this.port);
        while (true) {
            Socket clientSocket = this.server.accept();
            BufferedReader requestStream
                    = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()
                    )
            );

            DataOutputStream responseStream
                    = new DataOutputStream(
                    clientSocket.getOutputStream()
            );

            StringBuilder requestContent = new StringBuilder();

            String line = "";

            while ((line = requestStream.readLine()) != null && line.length() > 0) {
                requestContent.append(line).append(" ");
            }

            byte[] requstResult = this.handleRequst(requestContent.toString());
            byte[] responseContent = this.constructResponse(requestContent.toString(), requstResult);

            responseStream.write(responseContent);

            responseStream.close();
            requestStream.close();
        }
    }

    private byte[] handleRequst(String requestContent) {
        String requestMethod = this.extractRequestMethod(requestContent);
        String requestResource = this.extractRequestResource(requestContent);
        if (requestMethod.equals("GET")) {
            byte[] fileData = this.get(requestResource);
            return fileData;
        }
        return null;
    }


    private byte[] constructResponse(String requestContent, byte[] requstResult) {
        String resourceName = this.extractRequestResource(requestContent);
        String resourceExtension = resourceName.substring(resourceName.lastIndexOf(".") + 1);

        StringBuilder responseHeaders = new StringBuilder()
                .append("HTTP/1.1 200 OK").append(System.lineSeparator())
                .append("Server: ").append("Javache/-1.0.0").append(System.lineSeparator())
                .append("Date: ").append(new Date()).append(System.lineSeparator())
                .append("Content-Disposition: ").append("inline").append(System.lineSeparator())
                .append("Conent-Lenght: ").append(requstResult.length).append(System.lineSeparator())
                .append("Content-Type: ").append(this.getContentTyoe(resourceExtension)).append(System.lineSeparator()).append(System.lineSeparator());

        byte[] headerAsBytes = responseHeaders.toString().getBytes();

        byte[] fullResonseByteData = new byte[headerAsBytes.length + requstResult.length];

        for (int i = 0; i < headerAsBytes.length; i++) {
            fullResonseByteData[i] = headerAsBytes[i];
        }

        for (int i = 0; i < requstResult.length; i++) {
            fullResonseByteData[i + headerAsBytes.length] = requstResult[i];
        }

        return fullResonseByteData;
    }

    private byte[] get(String requestResource) {
        byte[] fileByteData = null;
        try {
            fileByteData = Files.readAllBytes(Paths.get("D:\\OneDrive\\Softuni\\WebServer\\src\\resources\\" + requestResource));
        } catch (NoSuchFileException e){
            return ("File not found!").getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByteData;
    }

    private String getContentTyoe(String resourceExtension) {
        if (this.supportedConentTypes.containsKey(resourceExtension)) {
            return this.supportedConentTypes.get(resourceExtension);
        }
        return "text/plain";
    }

    private String extractRequestResource(String requestContent) {
        return requestContent.split("\\s")[1];
    }

    private String extractRequestMethod(String requestContent) {
        return requestContent.split("\\s")[0];
    }
}

