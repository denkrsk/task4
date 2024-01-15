package ru.stepup.task.loadfile;

import ru.stepup.task.log.LogTransformation;
import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;
import ru.stepup.task.utils.Getable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadFl implements Getable {


    @Override
    @LogTransformation
    public HashMap<Logins, Users> getFileName(String filePath) throws IOException {
//        List<String> arrData = readFl.getFileName(dirStr);
        return ru.stepup.task.loadfile.LoadFile.getFileName(filePath);
    }
}
