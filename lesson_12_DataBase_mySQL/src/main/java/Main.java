import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/app.properties"));

        Connection connection = DriverManager.getConnection(
                properties.getProperty("db.host"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n\n            [MySQL Console]     ");
            System.out.println("[ 1 ] Добавить 1к автокоммитом      ");
            System.out.println("[ 2 ] Добавить 1к без автокоммита   ");
            System.out.println("[ 3 ] Просмотр таблицы              ");
            System.out.println("[ 4 ] Очистить таблицу              ");
            System.out.println("[ 5 ] Задать права                  ");
            System.out.println("[ 6 ] Убрать права                  ");
            System.out.println("[ 7 ] Просмотреть права             ");
            System.out.println("[ 8 ] Выход                         ");
            System.out.print("-> ");
            String ch = in.next();
            switch (ch) {
                case "1":
                    add_commit(connection, true);
                    break;
                case "2":
                    add_commit(connection, false);
                    break;
                case "3":
                    print_table(connection);
                    break;
                case "4":
                    delete_table(connection);
                    break;
                case "5":
                    prop_table(connection, properties);
                    break;
                case "6":
                    repvoke_table(connection, properties);
                    break;
                case "7":
                    info_table(connection, properties);
                    break;
                case "8":
                    return;
                default:
                    break;
            }
        }

    }

    private static void info_table(Connection connection, Properties properties) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "SHOW GRANTS FOR " + properties.getProperty("db.user") + "@localhost";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Grants for " + properties.getProperty("db.user") + "@localhost"));
        }

        System.out.println("Operation: OK, successful");
        statement.close();
    }

    private static void repvoke_table(Connection connection, Properties properties) throws SQLException {
        String query = "";
        System.out.println("[ 1 ] SELECT   ");
        System.out.println("[ 2 ] INSERT   ");
        System.out.println("[ 3 ] UPDATE   ");
        System.out.println("[ 4 ] DELETE   ");
        System.out.println("[ 5 ] ALL      ");
        System.out.println("[ 6 ] Quit     ");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        switch (in.next()) {
            case "1":
                query = "REVOKE SELECT ON user FROM " + properties.getProperty("db.user") + "@localhost";
                break;
            case "2":
                query = "REVOKE INSERT ON user FROM " + properties.getProperty("db.user") + "@localhost";
                break;
            case "3":
                query = "REVOKE UPDATE ON user FROM " + properties.getProperty("db.user") + "@localhost";
                break;
            case "4":
                query = "REVOKE DELETE ON user FROM " + properties.getProperty("db.user") + "@localhost";
                break;
            case "5":
                query = "REVOKE DELETE, SELECT, INSERT, UPDATE ON user FROM " + properties.getProperty("db.user") + "@localhost";
                break;
            case "6":
                return;
        }

        Statement statement = connection.createStatement();
        statement.execute(query);

        query = "SHOW GRANTS FOR " + properties.getProperty("db.user") + "@localhost";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Grants for " + properties.getProperty("db.user") + "@localhost"));
        }

        System.out.println("Operation: OK, successful");
        statement.close();
    }

    private static void prop_table(Connection connection, Properties properties) throws SQLException {
        String query = "";
        System.out.println("[ 1 ] SELECT   ");
        System.out.println("[ 2 ] INSERT   ");
        System.out.println("[ 3 ] UPDATE   ");
        System.out.println("[ 4 ] DELETE   ");
        System.out.println("[ 5 ] ALL      ");
        System.out.println("[ 6 ] Quit     ");
        System.out.print("-> ");
        Scanner in = new Scanner(System.in);
        switch (in.next()) {
            case "1":
                query = "GRANT SELECT ON user TO " + properties.getProperty("db.user") + "@localhost";
                break;
            case "2":
                query = "GRANT INSERT ON user TO " + properties.getProperty("db.user") + "@localhost";
                break;
            case "3":
                query = "GRANT UPDATE ON user TO " + properties.getProperty("db.user") + "@localhost";
                break;
            case "4":
                query = "GRANT DELETE ON user TO " + properties.getProperty("db.user") + "@localhost";
                break;
            case "5":
                query = "GRANT DELETE, SELECT, INSERT, UPDATE ON user TO " + properties.getProperty("db.user") + "@localhost";
                break;
            case "6":
                return;
        }

        Statement statement = connection.createStatement();
        statement.execute(query);

        query = "SHOW GRANTS FOR " + properties.getProperty("db.user") + "@localhost";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Grants for " + properties.getProperty("db.user") + "@localhost"));
        }

        System.out.println("Operation: OK, successful");
        statement.close();
    }

    private static void add_commit(Connection connection, boolean flag_auto_commit) throws SQLException {
        connection.setAutoCommit(flag_auto_commit);

        String query = "INSERT INTO user (id, name, phone) VALUES (?, ?, ?)";
        PreparedStatement val = connection.prepareStatement(query);

        int min = 97, max = 122, num = 10;
        int minInt = 49, maxInt = 57, numInt = 12;
        int N = 1_000;

        long start = System.nanoTime();
        if (flag_auto_commit) {
            for (int i = 0; i < N; i++) {
                val.setInt(1, i + 1);

                val.setString(2, "YA");
                val.setString(3, "Mana");
//                Random rand = new Random();
//                val.setString(2, rand.ints(min,
//                        max + 1)
//                        .limit(num)
//                        .collect(StringBuilder::new,
//                                StringBuilder::appendCodePoint,
//                                StringBuilder::append)
//                        .toString());
//                val.setString(3, rand.ints(minInt,
//                        maxInt + 1)
//                        .limit(numInt)
//                        .collect(StringBuilder::new,
//                                StringBuilder::appendCodePoint,
//                                StringBuilder::append)
//                        .toString());
                val.executeUpdate();
            }
        } else {
            for (int i = 0; i < N; i++) {
                val.setInt(1, i + 1);
                val.setString(2, "YA");
                val.setString(3, "Mana");
//                Random rand = new Random();
//                val.setString(2, rand.ints(min,
//                        max + 1)
//                        .limit(num)
//                        .collect(StringBuilder::new,
//                                StringBuilder::appendCodePoint,
//                                StringBuilder::append)
//                        .toString());
//                val.setString(3, rand.ints(minInt,
//                        maxInt + 1)
//                        .limit(numInt)
//                        .collect(StringBuilder::new,
//                                StringBuilder::appendCodePoint,
//                                StringBuilder::append)
//                        .toString());
                val.executeUpdate();
                connection.commit();
            }
        }
        long end = System.nanoTime();
        System.out.println("Timework: " + (double)(end - start) / 1_000_000_000 + "sec");
        System.out.println("Operation: OK, successful");
        val.close();
    }

    private static void delete_table(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "TRUNCATE TABLE user";
        statement.execute(query);
        System.out.println("Operation: OK, successful");
        statement.close();
    }

    private static void print_table(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");

            System.out.println(id + " " + name + " " + phone);
        }
        System.out.println("Operation: OK, successful");
        resultSet.close();
        statement.close();
    }
}