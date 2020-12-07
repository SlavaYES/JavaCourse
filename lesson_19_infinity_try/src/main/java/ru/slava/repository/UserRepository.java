package ru.slava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.slava.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Update table row
     * @param id Index change row
     * @param name Name field
     * @param value Value for name field
     */
    default void updateById(Integer id, String name, String value) {
        User user = findById(id).get();

        if (name.equals("id") && findById(Integer.parseInt(value)).isPresent()) {
            return;
        }

        deleteById(id);
        user.setValueByName(name, value);
        save(user);
    }
}
