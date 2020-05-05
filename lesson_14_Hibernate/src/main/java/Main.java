import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String check;
        System.out.println("[ 1 ] User    ");
        System.out.println("[ 2 ] Developer ");
        System.out.println("[ 3 ] Manager ");
        System.out.println("[ 4 ] Exit    ");
        System.out.print("-> ");
        check = in.nextLine();
        switch (check) {
            case "1":
                workTable("User");
                break;
            case "2":
                workTable("Developer");
                break;
            case "3":
                workTable("Manager");
                break;
            case "4":
                return;
            default:
                break;
        }

    }

    private static void workTable(String className) {
        Configuration configuration = new Configuration().configure();
        switch (className) {
            case "User":
                configuration.addAnnotatedClass(User.class);
                break;
            case "Developer":
                configuration.addAnnotatedClass(Developer.class);
                break;
            case "Manager":
                configuration.addAnnotatedClass(Manager.class);
                break;
        }
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Scanner in = new Scanner(System.in);
        String check;
        do {
            System.out.println("[ 1 ] Create   ");
            System.out.println("[ 2 ] Read     ");
            System.out.println("[ 3 ] Update   ");
            System.out.println("[ 4 ] Delete   ");
            System.out.println("[ 5 ] Read All ");
            System.out.println("[ 6 ] Exit     ");
            System.out.print("-> ");
            check = in.nextLine();
            switch (check) {
                case "1":
                    Session sessionCreate = sessionFactory.openSession();
                    Transaction transactionCreate = sessionCreate.beginTransaction();
                    switch (className) {
                        case "User":
                            sessionCreate.save(new User("Vyacheslav", "88005553535"));
                            break;
                        case "Developer":
                            sessionCreate.save(new Developer("Vyacheslav", "88005553535", "slava@gmail.ru"));
                            break;
                        case "Manager":
                            sessionCreate.save(new Manager("Vyacheslav", "88005553535", "slava@gmail.ru", "English"));
                            break;
                        default:
                            break;
                    }
                    transactionCreate.commit();
                    sessionCreate.close();
                    break;
                case "2":
                    Session sessionRead = sessionFactory.openSession();
                    Transaction transactionRead = sessionRead.beginTransaction();
                    System.out.print("-> ");
                    Integer primaryKey = in.nextInt();
                    switch (className) {
                        case "User":
                            User user = sessionRead.find(User.class, primaryKey);
                            System.out.println(user.toString());
                            break;
                        case "Developer":
                            Developer developer = sessionRead.find(Developer.class, primaryKey);
                            System.out.println(developer.toString());
                            break;
                        case "Manager":
                            Manager manager = sessionRead.find(Manager.class, primaryKey);
                            System.out.println(manager.toString());
                            break;
                        default:
                            break;
                    }
                    transactionRead.commit();
                    sessionRead.close();
                    break;
                case "3":
                    Session sessionUpdate = sessionFactory.openSession();
                    Transaction transactionUpdate = sessionUpdate.beginTransaction();
                    System.out.print("-> ");
                    primaryKey = in.nextInt();
                    switch (className) {
                        case "User":
                            User user = sessionUpdate.find(User.class, primaryKey);
                            user.setPhone("89134870834");
                            System.out.println(user.toString());
                            break;
                        case "Developer":
                            Developer developer = sessionUpdate.find(Developer.class, primaryKey);
                            developer.setEmail("good@gmail.com");
                            System.out.println(developer.toString());
                            break;
                        case "Manager":
                            Manager manager = sessionUpdate.find(Manager.class, primaryKey);
                            manager.setLang("Russia");
                            System.out.println(manager.toString());
                            break;
                        default:
                            break;
                    }
                    transactionUpdate.commit();
                    sessionUpdate.close();
                    break;
                case "4":
                    Session sessionDelete = sessionFactory.openSession();
                    Transaction transactionDelete = sessionDelete.beginTransaction();
                    System.out.print("-> ");
                    primaryKey = in.nextInt();
                    switch (className) {
                        case "User":
                            User user = sessionDelete.find(User.class, primaryKey);
                            sessionDelete.delete(user);
                            break;
                        case "Developer":
                            Developer developer = sessionDelete.find(Developer.class, primaryKey);
                            sessionDelete.delete(developer);
                            break;
                        case "Manager":
                            Manager manager = sessionDelete.find(Manager.class, primaryKey);
                            sessionDelete.delete(manager);
                            break;
                        default:
                            break;
                    }
                    transactionDelete.commit();
                    sessionDelete.close();
                    break;
                case "5":
                    Session sessionQuery = sessionFactory.openSession();
                    Query query;

                    switch (className) {
                        case "User":
                            query = sessionQuery.createQuery("FROM User");
                            List<User> user = query.getResultList();
                            for (User u : user) {
                                System.out.println(u.toString());
                            }
                            break;
                        case "Developer":
                            query = sessionQuery.createQuery("FROM Developer");
                            List<Developer> developer = query.getResultList();
                            for (User u : developer) {
                                System.out.println(u.toString());
                            }
                            break;
                        case "Manager":
                            query = sessionQuery.createQuery("FROM Manager");
                            List<Manager> manager = query.getResultList();
                            for (User u : manager) {
                                System.out.println(u.toString());
                            }
                            break;
                        default:
                            break;
                    }
                    sessionQuery.close();
                    break;
                case "6":
                    return;
                default:
                    break;
            }
        } while (true);

    }
}
