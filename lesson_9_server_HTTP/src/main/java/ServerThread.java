import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    public Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream inS = client.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inS));

            OutputStream outS = client.getOutputStream();
            PrintWriter out = new PrintWriter(outS, true);
            String request;

            request = in.readLine();
            System.out.println(request);

            String page = "<!DOCTYPE><html>" +
                    "<body>" +
                    "<center><div style=\"width: 200px; height: 60px; " +
                    "border: 5px solid; " +
                    "border-radius: 0 10px 20px;" +
                    "color: #74c3d2;" +
                    "font-family: Arial, Helvetica, sans-serif;" +
                    " " +
                    "\"><h3 style=\"color: #ABABAB;\">Hello, Client<h3></div></center><br>" +
                    "</body>" +
                    "</html>";
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + page.length() + "\r\n" +
                    "\r\n" +
                    page;

            out.println(response);
            client.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
