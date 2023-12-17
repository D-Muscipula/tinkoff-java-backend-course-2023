package edu.hw10.Task1.generators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class ByteGenerator implements FieldsGenerator {

    @Override
    public Object generate(Annotation[] annotations) {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (byte) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = (byte) maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
