import example.tech11_task.models.User;
import example.tech11_task.resources.UserResource;
import example.tech11_task.inmemory.UserStorage;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private UserResource userResource;

    @BeforeEach
    public void setUp() {
        UserStorage.getInstance().clear();
        userResource = new UserResource();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthday("1990-01-01");
        user.setPassword("password123");

        Response response = userResource.addUser(user);
        assertEquals(201, response.getStatus());
        List<User> users = (List<User>) userResource.getUsers().getEntity();
        assertEquals(1, users.size());
        assertEquals("john.doe@example.com", users.get(0).getEmail());
    }

    @Test
    public void testGetUsers() {
        User user1 = new User();
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setBirthday("1990-01-01");
        user1.setPassword("password123");

        User user2 = new User();
        user2.setFirstname("Jane");
        user2.setLastname("Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setBirthday("1992-02-02");
        user2.setPassword("password456");

        userResource.addUser(user1);
        userResource.addUser(user2);

        List<User> users = (List<User>) userResource.getUsers().getEntity();
        assertEquals(2, users.size());
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthday("1990-01-01");
        user.setPassword("password123");

        userResource.addUser(user);

        Response response = userResource.getUser("john.doe@example.com");
        User retrievedUser = (User) response.getEntity();
        assertNotNull(retrievedUser);
        assertEquals("john.doe@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthday("1990-01-01");
        user.setPassword("password123");

        userResource.addUser(user);

        User updatedUser = new User();
        updatedUser.setFirstname("John");
        updatedUser.setLastname("Doe");
        updatedUser.setEmail("john.doe@example.com");
        updatedUser.setBirthday("1990-01-01");
        updatedUser.setPassword("newpassword123");

        Response response = userResource.updateUser("john.doe@example.com", updatedUser);
        assertEquals(200, response.getStatus());

        Response getUserResponse = userResource.getUser("john.doe@example.com");
        User retrievedUser = (User) getUserResponse.getEntity();
        assertNotNull(retrievedUser);
        assertEquals("newpassword123", retrievedUser.getPassword());
    }
}
