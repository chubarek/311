package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getById(Long id);

    void save(User user);

    void delete(Long id);

    void update(User user);
}
