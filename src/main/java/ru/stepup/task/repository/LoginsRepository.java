package ru.stepup.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.task.log.LogTransformation;
import ru.stepup.task.model.Logins;

public interface LoginsRepository extends JpaRepository<Logins, Long> {
    @Override
    @LogTransformation
    <S extends Logins> S save(S entity);
}