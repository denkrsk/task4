package ru.stepup.task.loadfile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoadFile {

    public static void getFileName(String filePath) throws IOException {

        File dir = new File(filePath);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        for (File fl : lst) {
            String ss = fl.getCanonicalPath();
            System.out.println(ss.substring(ss.lastIndexOf('\\', ss.lastIndexOf('\\')-1)+1));
            System.out.println(getFileContent(ss.substring(ss.lastIndexOf('\\', ss.lastIndexOf('\\')-1)+1)));
        }
    }
    public static String getFileContent(String filePath) throws IOException {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[]{});


            Resource resource = appContext.getResource(filePath);

            StringBuilder sb = new StringBuilder();

            BufferedReader br = null;
            try {
//              System.out.println(resource.get);
                br = new BufferedReader(
                        new InputStreamReader(resource.getInputStream(), "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return sb.toString();
    }
}
