import java.util.Comparator;

public class UserCompare implements Comparator<User> {
    public int compare(User user1, User user2) {
        if (user1.name.compareTo(user2.name) < 0) {
            return -1;
        } else if (user1.name.compareTo(user2.name) == 0) {
            return 0;
        } else {
            return  1;
        }


    }
}
