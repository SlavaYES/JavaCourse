package ru.slava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.slava.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
