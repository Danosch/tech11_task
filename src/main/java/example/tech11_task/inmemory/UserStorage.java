package example.tech11_task.inmemory;

import example.tech11_task.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static UserStorage instance;
    private List<User> users;

    private UserStorage() {
        users = new ArrayList<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public boolean updateUser(String email, User updatedUser) {
        User user = findUserByEmail(email);
        if (user != null) {
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setBirthday(updatedUser.getBirthday());
            user.setPassword(updatedUser.getPassword());
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email) {
        return users.removeIf(user -> user.getEmail().equals(email));
    }

    public void clear() {
        users.clear();
    }
}
