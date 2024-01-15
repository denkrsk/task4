package ru.stepup.task.utils;

import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface Getable {
    HashMap<Logins, Users>  getFileName(String filePath) throws IOException;
}
