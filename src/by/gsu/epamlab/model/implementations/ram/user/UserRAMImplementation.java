package by.gsu.epamlab.model.implementations.ram.user;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.user.Role;
import by.gsu.epamlab.model.user.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class UserRAMImplementation implements IUserDAO {

    private static final String ADMIN = "admin";

    private static final Map<User, String> memory = new HashMap<>();

    private static final UserRAMImplementation userRAMImplementation = new UserRAMImplementation();

    private UserRAMImplementation() {}

    static {
        memory.put(new User(Constants.ZERO, ADMIN, Role.ADMIN), ADMIN);
    }

    @Override
    public User getUser(String login, char [] password) {

        User user = new User(Constants.ZERO, login, Role.USER);

        String stringPassword = memory.get(user);

        char [] truePassword = new char[0];

        if(stringPassword != null) {
            truePassword = stringPassword.toCharArray();
        }

        if(truePassword.length == 0 || !Arrays.equals(truePassword, password)) {
            user = null;
        }
        return user;
    }

    @Override
    public boolean setUser(String login, char [] password) {

        boolean isAdded = false;

        User user = new User(Constants.ZERO, login, Role.USER);

        synchronized (memory) {
            if(!memory.containsKey(user)) {
                memory.put(user, new String(password));
                isAdded = true;
            }
        }

        return isAdded;
    }

    public static UserRAMImplementation getUserRAMImplementation() {
        return userRAMImplementation;
    }
}
