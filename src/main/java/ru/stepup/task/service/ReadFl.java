package ru.stepup.task.service;

import ru.stepup.task.log.LogTransformation;

import java.io.IOException;
import java.util.List;

public class ReadFl implements Getable{


    @Override
    @LogTransformation
    public List<String> getFileName(String filePath) throws IOException {
//        List<String> arrData = readFl.getFileName(dirStr);
        return ru.stepup.task.loadfile.LoadFile.getFileName(filePath);
    }
}
