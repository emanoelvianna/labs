import java.lang.reflect.Array;
import java.util.*;

public class Capitulo1 {
    public static void main(String[] args) {
        User user1 = new User("Paulo", 150);
        User user2 = new User("Maria", 350);

        List<User> users = Arrays.asList(user1, user2);

        for(User user : users) {
            System.out.println(user.getName());
        }

    }
}