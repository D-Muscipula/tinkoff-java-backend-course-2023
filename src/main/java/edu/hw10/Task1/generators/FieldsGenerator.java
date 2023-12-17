package edu.hw10.Task1.generators;

import java.lang.annotation.Annotation;

public interface FieldsGenerator {
    Object generate(Annotation[] annotations);
}
