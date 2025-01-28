package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User getUserById(int id);

    void save(User user);

    void update(User user, int id);

    void removeUserWithId(int id);
}
