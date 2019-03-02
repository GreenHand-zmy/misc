import bean.User;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HiddenFile {
    public static ArrayList<User> init() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("zmy", 22));
        userList.add(new User("zcl", 26));
        return userList;
    }

    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        File[] files = new File(".").listFiles(File::isHidden);
        List<User> userList = init();
        List<User> filter = filter(userList, (User user) -> user.getAge() > 24);
        System.out.println(filter);
        Runnable r = () -> System.out.println("hello world");
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
