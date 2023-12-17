package edu.hw10.Task1.generators;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public class StringGenerator implements FieldsGenerator {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 10;

    public static final int CHAR_TOP_BOUND = 127;

    public static final int CHAR_BOTTOM_BOUND = 0;

    @Override
    public Object generate(Annotation[] annotations) {
        int min = MIN_LENGTH;
        int max = MAX_LENGTH;
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNull = false;
            } else if (annotation instanceof Min minAnnotation) {
                min = (int) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = (int) maxAnnotation.value();
            }
        }
        if (!isNull) {
            return null;
        }
        long length = ThreadLocalRandom.current().nextLong(min, max);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) ThreadLocalRandom.current().nextInt(CHAR_BOTTOM_BOUND, CHAR_TOP_BOUND));
        }
        return stringBuilder.toString();
    }
}

