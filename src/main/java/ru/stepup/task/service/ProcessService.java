package ru.stepup.task.service;

import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;
import ru.stepup.task.repository.LoginsRepository;
import ru.stepup.task.repository.UsersRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProcessService {

    final private UsersRepository usersRepository;
    final private LoginsRepository loginsRepository;

    public void start() {
        String username="Petr";
        String fio= "Петров Петр Петрович";
        String application="Google";

        Optional<Users> user = usersRepository.findByUsername(username);
        Users currentUser;


        if (user.isEmpty()) {
            currentUser = usersRepository.save(Users.builder()
                    .fio(fio)
                    .username(username)
                    .build());
        } else {
            currentUser = user.get();
        }

        Logins logins = loginsRepository.save(Logins.builder()
                .user(currentUser)
                .application(application)
                .build());

        System.out.println("currentUser id="+currentUser.getId());
        System.out.println("logins id="+logins.getId());

        System.out.println(currentUser.getLogins());


    }

}
