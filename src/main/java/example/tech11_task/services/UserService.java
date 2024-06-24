package example.tech11_task.services;

import example.tech11_task.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String, User> users = new HashMap<>();

    private UserService() {}

    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public void updateUser(String email, User user) {
        users.put(email, user);
    }

}
