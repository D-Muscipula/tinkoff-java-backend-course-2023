package edu.hw10.Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object proxiedObject;
    public final File file;
    private Map<String, Object> dataFromFile = new HashMap<>();

    public CacheInvocationHandler(Object proxiedObject, String path) {
        this.proxiedObject = proxiedObject;
        this.file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        String key = formKey(method, args);
        if (!method.getAnnotation(Cache.class).persist()) {
            try {
                return method.invoke(proxiedObject, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        dataFromFile = getCache();
        if (dataFromFile.containsKey(key)) {
            return dataFromFile.get(key);
        }
        try {
            Object result = method.invoke(proxiedObject, args);
            putResultInCache(key, result);
            return result;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public String formKey(Method method, Object[] args) {
        StringBuilder key = new StringBuilder();
        key.append("Class: ").append(method.getDeclaringClass().getName())
            .append("Method: ").append(method.getName())
            .append("Args:");
        for (Object arg : args) {
            key.append(" ").append(arg);
        }
        return key.toString();
    }

    public Map<String, Object> getCache() {
        if (file.length() == 0) {
            return dataFromFile;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, Object>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void putResultInCache(String key, Object result) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            dataFromFile.put(key, result);
            objectOutputStream.writeObject(dataFromFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
