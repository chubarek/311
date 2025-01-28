package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(int id);

    void save(User user);

    void update(User user, int id);

    void removeUserWithId(int id);
}
