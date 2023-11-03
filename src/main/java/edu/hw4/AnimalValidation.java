package edu.hw4;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public final class AnimalValidation {

    public static Set<ValidationError> resultCheck(Animal animal) {
        LinkedHashSet<ValidationError> validationErrorHashSet = new LinkedHashSet<>() {{
            add(checkName(animal));
            add(checkType(animal));
            add(checkSex(animal));
            add(checkAge(animal));
            add(checkHeight(animal));
            add(checkWeight(animal));
        }};
        validationErrorHashSet.remove(null);
        return validationErrorHashSet;
    }

    public static String stringResultCheck(Animal animal) {
        HashSet<ValidationError> validationErrorHashSet = (HashSet<ValidationError>) resultCheck(animal);
        StringBuilder stringBuilder = new StringBuilder("Ошибки в полях:");
        for (var element : validationErrorHashSet) {
            stringBuilder.append(" ").append(element.errorType().getTitle()).append("(").append(element.errorMessage())
                .append(")");
        }
        return stringBuilder.toString();
    }

    public static ValidationError checkName(Animal animal) {
        if (animal.name() == null || animal.name().isBlank()) {
            return new ValidationError(ValidationError.ErrorType.NAME_ERROR, "Отсутствует имя");
        } else if (animal.name().length() == 1) {
            return new ValidationError(ValidationError.ErrorType.NAME_ERROR, "Слишком короткое имя");
        } else if (animal.name().charAt(0) == Character.toLowerCase(animal.name().charAt(0))) {
            return new ValidationError(ValidationError.ErrorType.NAME_ERROR, "Имя пишется с большой буквы");
        }
        return null;
    }

    public static ValidationError checkType(Animal animal) {
        if (animal.type() == null) {
            return new ValidationError(ValidationError.ErrorType.TYPE_ERROR, "Тип не определен");
        }
        return null;
    }

    public static ValidationError checkSex(Animal animal) {
        if (animal.sex() == null) {
            return new ValidationError(ValidationError.ErrorType.SEX_ERROR, "Пол не определен");
        }
        return null;
    }

    public static ValidationError checkAge(Animal animal) {
        if (animal.age() < 0) {
            return new ValidationError(ValidationError.ErrorType.AGE_ERROR, "Отрицательный возраст");
        } else if (animal.age() == 0) {
            return new ValidationError(ValidationError.ErrorType.AGE_ERROR, "Нулевой возраст");
        }
        return null;
    }

    public static ValidationError checkHeight(Animal animal) {
        if (animal.height() < 0) {
            return new ValidationError(ValidationError.ErrorType.HEIGHT_ERROR, "Отрицательный рост");
        } else if (animal.height() == 0) {
            return new ValidationError(ValidationError.ErrorType.HEIGHT_ERROR, "Нулевой рост");
        }
        return null;
    }

    public static ValidationError checkWeight(Animal animal) {
        if (animal.weight() < 0) {
            return new ValidationError(ValidationError.ErrorType.WEIGHT_ERROR, "Отрицательный вес");
        } else if (animal.weight() == 0) {
            return new ValidationError(ValidationError.ErrorType.WEIGHT_ERROR, "Нулевой вес");
        }
        return null;
    }

    private AnimalValidation() {
    }
}
