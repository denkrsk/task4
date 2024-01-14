package ru.stepup.task.service;

import ru.stepup.task.model.Logins;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface Getable {
    List<String> getFileName(String filePath) throws IOException;
}
