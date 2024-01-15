package ru.stepup.task.service;

import ru.stepup.task.loadfile.ReadFl;
import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;
import ru.stepup.task.repository.LoginsRepository;
import ru.stepup.task.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.task.utils.Getable;
import ru.stepup.task.utils.UtilsRep;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
public class ProcessService {

    final private UsersRepository usersRepository;
    final private LoginsRepository loginsRepository;

    public void start(String dirStr) throws IOException {
        Getable readFl = UtilsRep.cashe(new ReadFl());
        HashMap<Logins, Users> dataFl = readFl.getFileName(dirStr);

        Iterator<Map.Entry<Logins, Users>> itr = dataFl.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Logins, Users> entry = itr.next();
//            Pair pair = entry.getValue();
            System.out.println("Save");
            Optional<Users> user = usersRepository.findByUsername(entry.getValue().getUsername());
            Users currentUser = user.orElseGet(() -> {
                System.out.println("Create new user");
                return usersRepository.save(Users.builder()
                        .fio(entry.getValue().getFio())
                        .username(entry.getValue().getUsername()).build());}
            );

            entry.getKey().setUser(currentUser);
            loginsRepository.save(entry.getKey());

            System.out.println("currentUser id="+currentUser.getId());

            System.out.println(currentUser.getLogins());

        }

    }


}
