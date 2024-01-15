package ru.stepup.task.loadfile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.stepup.task.model.Logins;
import ru.stepup.task.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoadFile{
    private static HashMap<Logins, Users> fileStr =  new HashMap<>();

    public static HashMap<Logins, Users> getFileName(String filePath) throws IOException {


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
                    String[] arrLst = line.split(" ");
                    Users usr = new Users();
                    Logins lgns = new Logins();
                    for (int i=0; i < arrLst.length; i++) {
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
                   fileStr.put(lgns, usr);
                }
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }

    public static Timestamp getTmStmp(String str) {
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
