package edu.hw10.Task1.generators;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
