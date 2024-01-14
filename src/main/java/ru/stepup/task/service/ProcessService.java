package ru.stepup.task.service;

import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;
import ru.stepup.task.repository.LoginsRepository;
import ru.stepup.task.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ProcessService {

    final private UsersRepository usersRepository;
    final private LoginsRepository loginsRepository;

    public void start(String dirStr) throws IOException {
        Getable readFl = UtilsRep.cashe(new ReadFl());
        List<String> arrData = readFl.getFileName(dirStr);
        for (String lst:arrData) {
            String[] arrLst = lst.split(" ");
            Users usr = new Users();
            Logins lgns = new Logins();
            for (int i=0; i < arrLst.length; i++) {
//                System.out.println(arrLst[i]);
                switch (i) {
                    case 0: usr.setUsername(arrLst[i]);
                        break;
                    case 1: usr.setFio(arrLst[1] + " " + arrLst[2] + " " + arrLst[3]);
                        break;
                    case 4: lgns.setAccessDate(getTmStmp(arrLst[i]));
                        break;
                    case 5: lgns.setApplication(arrLst[i].toUpperCase());
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Save");
            Optional<Users> user = usersRepository.findByUsername(usr.getUsername());
            Users currentUser = user.orElseGet(() -> {
                System.out.println("Create new user");
                return usersRepository.save(Users.builder()
                .fio(usr.getFio())
                        .username(usr.getUsername()).build());}
            );

            lgns.setUser(currentUser);
            loginsRepository.save(lgns);

            System.out.println("currentUser id="+currentUser.getId());

            System.out.println(currentUser.getLogins());
        }





    }
    public Timestamp getTmStmp (String str) {
        System.out.println("str = " + str);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
            return null;
        }
    }

}
