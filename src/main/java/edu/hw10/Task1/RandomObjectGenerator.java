package edu.hw10.Task1;

import edu.hw10.Task1.generators.BooleanGenerator;
import edu.hw10.Task1.generators.ByteGenerator;
import edu.hw10.Task1.generators.DoubleGenerator;
import edu.hw10.Task1.generators.FieldsGenerator;
import edu.hw10.Task1.generators.IntegerGenerator;
import edu.hw10.Task1.generators.LongGenerator;
import edu.hw10.Task1.generators.ShortGenerator;
import edu.hw10.Task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RandomObjectGenerator {
    public final static Map<Object, FieldsGenerator> GENERATOR_MAP = new HashMap<>() {{
        put(boolean.class, new BooleanGenerator());
        put(byte.class, new ByteGenerator());
        put(double.class, new DoubleGenerator());
        put(int.class, new IntegerGenerator());
        put(long.class, new LongGenerator());
        put(short.class, new ShortGenerator());
        put(String.class, new StringGenerator());
    }};

    public <T> T nextObject(Class<T> classOrRecord) {
        Constructor<?> maxArgumentConstructor = Arrays
            .stream(classOrRecord.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow(RuntimeException::new);
        Object[] parameters = new Object[maxArgumentConstructor.getParameterCount()];
        int i = 0;
        for (Parameter parameter : maxArgumentConstructor.getParameters()) {
            var type = parameter.getType();
            FieldsGenerator fieldGenerator =
                GENERATOR_MAP.get(type);
            parameters[i] = fieldGenerator.generate(parameter.getAnnotations());
            i++;
        }
        try {
            return classOrRecord.cast(maxArgumentConstructor.newInstance(parameters));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> classOrRecord, String methodName) {
        if (methodName == null) {
            return nextObject(classOrRecord);
        }
        try {
            Method method = classOrRecord.getMethod(methodName);
            method.setAccessible(true);
            try {
                return classOrRecord.cast(method.invoke(classOrRecord));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
