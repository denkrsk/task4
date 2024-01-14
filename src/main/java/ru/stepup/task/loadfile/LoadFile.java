package ru.stepup.task.loadfile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.stepup.task.log.LogTransformation;
import ru.stepup.task.service.ProcessService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadFile{
    private static List<String> fileStr =  new ArrayList<>();

    public static List<String> getFileName(String filePath) throws IOException {


        File dir = new File(filePath);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        for (File fl : lst) {
            String ss = fl.getCanonicalPath();
            getFileContent(ss.substring(ss.lastIndexOf('\\', ss.lastIndexOf('\\')-1)+1));
        }
        return fileStr;
    }
    public static void getFileContent(String filePath) throws IOException {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[]{});


            Resource resource = appContext.getResource(filePath);

            BufferedReader br = null;
            try {
                br = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                   fileStr.add(line);
                }
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
}
