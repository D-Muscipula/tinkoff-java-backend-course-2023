package edu.hw10.Task1.generators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ShortGenerator implements FieldsGenerator {
    @Override
    public Object generate(Annotation[] annotations) {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (short) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = (short) maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
