package by.gsu.epamlab.implementations;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.beans.user.Role;
import by.gsu.epamlab.beans.user.User;

import java.util.HashMap;
import java.util.Map;

public final class UserRAMImplementation implements IUserDAO {

    private static final Map<User, String> memory = new HashMap<>();

    private static final UserRAMImplementation userRAMImplementation = new UserRAMImplementation();

    private UserRAMImplementation() {}

    static {
        memory.put(new User(0,"admin", Role.ADMIN), "admin");
    }

    @Override
    public User getUser(String login, String password) {
        User user = new User(0,login, Role.USER);
        String truePassword = memory.get(user);
        if(truePassword == null || !truePassword.equals(password)) {
            user = null;
        }
        return user;
    }

    @Override
    public User setUser(String login, String password) {
        User user = new User(0, login, Role.USER);
        synchronized (memory) {
            if(!memory.containsKey(user)) {
                memory.put(user, password);
            }
        }
        return user;
    }

    public static UserRAMImplementation getUserRAMImplementation() {
        return userRAMImplementation;
    }
}
