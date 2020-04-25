import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws
            IOException {

        ServerSocket server = new ServerSocket(8081);


        while (true) {
            Socket client = server.accept();
            new Thread(new ServerThread(client)).start();
        }

        //server.close();
    }
}
