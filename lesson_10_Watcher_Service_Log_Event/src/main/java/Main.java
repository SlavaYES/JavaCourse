import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter path directory[Watch Service]: ");
        String nameDir = in.nextLine();
        System.out.println("[Check]: " + nameDir +
                " [Path exist, Find Successful]");
        System.out.println("[Look] Log -> log.txt");
        System.out.println("[Look] Directory's content -> content.txt");

        Path dir = Path.of(nameDir);

        Stream<Path> walkDir  = Files.walk(dir, 1);
        List<String> allDir = walkDir.filter(Files::isDirectory).map(Path::toString).collect(Collectors.toList());

        Path content = Path.of("content.txt");
        if (Files.exists(content)) {
            Files.delete(content);
        }
        Files.createFile(content);
        Files.writeString(content, dir.toString() + "\n\n");

        for (String curDir : allDir) {
            Path curName = Path.of(curDir);
            if (Files.isDirectory(curName)) {
                Files.writeString(content, "/", StandardOpenOption.APPEND);
            }
            Files.writeString(content, curName.getFileName().toString() + "\n", StandardOpenOption.APPEND);
        }

        Stream<Path> walkFile  = Files.walk(dir, 1);
        List<String> allFiles = walkFile.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
        for (String curFile : allFiles) {
            Path curName = Path.of(curFile);
            if (Files.isDirectory(curName)) {
                Files.writeString(content, "/", StandardOpenOption.APPEND);
            }
            Files.writeString(content, curName.getFileName().toString() + "\n", StandardOpenOption.APPEND);
        }

        WatchService watchService = FileSystems.getDefault().newWatchService();
        dir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE);

        Path log = Path.of("log.txt");
        if (Files.exists(log)) {
            Files.delete(log);
        }
        Files.createFile(log);
        Files.writeString(log, dir.toString() + "\n\n");
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.context().equals(log)) {
                    break;
                }
                String info = "[" + event.context() +
                        ", " +
                        event.kind().name() +
                        ", " + new Date() + "]\n";
                Files.writeString(log, info, StandardOpenOption.APPEND);
            }
            key.reset();
        }
    }
}
