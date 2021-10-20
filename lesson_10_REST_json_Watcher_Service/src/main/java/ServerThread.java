import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServerThread implements Runnable {
    public Socket client;

    public ServerThread() {

    }

    public ServerThread(Socket client) {
        this.client = client;
    }

    public void myStart() throws IOException {
        Scanner in = new Scanner(client.getInputStream());
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        String recuest = in.nextLine();
        String[] recuestList = recuest.split(" ");

        Path dir = Path.of(".");
        ObjectMapper mapper = new ObjectMapper();
        MyWatch  watch = new MyWatch();

        System.out.println(recuest);
        if (recuest.equals("GET / HTTP/1.1")) {

            String index = "<!DOCTYPE><html><body><dev style=\"background: #4a76a8; color: #ABABAB; font-family: Arial; font-size: 20px;\"><center>HELLO</center></dev><br>" +
                    "<dev style=\"color: #ABABAB; font-family: Arial; font-size: 20px;\"><center>WELCOME to SlavaAPI</center></dev><br>" +
                    "<dev style=\"color: #4a76a8; font-family: Arial; font-size: 20px;\">Using commands:<br>" +
                    " /info<br> /create/'namefile'<br> /delete/'namefile'<br>" +
                    " /copy/'namefile'/'namefile'<br> /move/'namefile'/'namefile'</dev>" +
                    "</body></html>";
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + index.length() + "\r\n" +
                    "\r\n" +
                    index;
            out.println(response);
        }

        if (recuestList[0].equals("GET")) {
            String[] method = recuestList[1].split("/");
            for (String m : method) {
                System.out.println(m + ";");
            }
            switch (method[1]) {
                case "info":
                    watch.setStatus("ok");
                    break;
                case "create":
                    if (!method[2].isEmpty()) {
                        Files.createFile(Path.of(method[2]));
                        watch.setStatus("ok");
                    }
                    break;
                case "delete":
                    if (!method[2].isEmpty()) {
                        Files.delete(Path.of(method[2]));
                        watch.setStatus("ok");
                    }
                    break;
                case "move":
                    if (!method[2].isEmpty() && !method[3].isEmpty()) {
                        Files.move(Path.of(method[2]), Path.of(method[3]), StandardCopyOption.REPLACE_EXISTING);
                        watch.setStatus("ok");
                    }
                    break;
                case "copy":
                    if (!method[2].isEmpty() && !method[3].isEmpty()) {
                        Files.copy(Path.of(method[2]), Path.of(method[3]), StandardCopyOption.REPLACE_EXISTING);
                        watch.setStatus("ok");
                    }
                    break;
                default:
                    watch.setStatus("fail");
                    break;
            }
            watch.setFiles(Files.walk(dir, 1)
                    .filter(Files::isRegularFile).
                            map(Path::toString).
                            collect(Collectors.toList()));
            String jsonFile = mapper.writeValueAsString(watch);
            System.out.println(jsonFile);

            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: " + jsonFile.length() + "\r\n" +
                    "\r\n" +
                    jsonFile;
            out.println(response);
        }
        client.close();
    }

    @Override
    public void run() {
        try {
            myStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
