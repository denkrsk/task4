package ru.stepup.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.task.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

}
