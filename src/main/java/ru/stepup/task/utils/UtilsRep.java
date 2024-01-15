package ru.stepup.task.utils;

import ru.stepup.task.log.LogTransformation;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class UtilsRep {

    public static <T> T cashe(T obj) {

        ClassLoader objClassloader = obj.getClass().getClassLoader();

        Class[] interfaces = obj.getClass().getInterfaces();
        CashHandler cashHandler = new CashHandler(obj);

        T proxyObj = (T) Proxy.newProxyInstance(objClassloader, interfaces, cashHandler);

        return proxyObj;

    }

}

class CashHandler implements InvocationHandler {
    private Object object;

    public CashHandler(Object object) {
        this.object = object;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method tmp = object.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        LogTransformation logTransformation = tmp.getAnnotation(LogTransformation.class);

        Object retObj = method.invoke(object, args);
        if (tmp.isAnnotationPresent(LogTransformation.class)) {

            try (FileWriter writer = new FileWriter(logTransformation.fileName(), true)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                AtomicReference<String> txt = new AtomicReference<>(formatter.format(date) + '\n');
                txt.set(txt + tmp.getClass().getName() + " " + method.getName() + '\n');
                txt.set(txt + "IN: ");
                Arrays.stream(args).forEach(s -> txt.set(txt + s.toString()));
                txt.set(txt + " " + '\n');
                txt.set(txt + "OUT: " + retObj.toString() + '\n');
                writer.write(txt.get());
                writer.flush();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }

        return retObj;
    }


}





