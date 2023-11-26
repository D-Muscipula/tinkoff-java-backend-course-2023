package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Tasks {
    private final static int HUNDRED_CENTIMETERS = 100;

    public static List<Animal> getSortedByHeightAnimalList(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> getKTheHeaviest(List<Animal> animals, int k) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> getQuantityOfDifferentAnimals(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(x -> 1)));
    }

    public static Animal getAnimalWithTheLongestName(List<Animal> animals) {
        return animals
            .stream()
            .max(Comparator.comparing(x -> x.name().length()))
            .orElse(null);
    }

    public static Animal.Sex getSexWithBiggestQuantity(List<Animal> animals) {
        int countM = (int) animals
            .stream()
            .filter(x -> x.sex() == Animal.Sex.M)
            .count();
        int countF = (int) animals
            .stream()
            .filter(x -> x.sex() == Animal.Sex.F)
            .count();
        if (countF > countM) {
            return Animal.Sex.F;
        }
        return Animal.Sex.M;

    }

    public static Map<Animal.Type, Animal> getTheHeaviestOfEachType(List<Animal> animals) {
        Comparator<Animal> animalComparator = Comparator.comparing(Animal::weight);
        return animals
            .stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(), BinaryOperator.maxBy(animalComparator)));
    }

    public static Animal getTheKOldestAnimal(List<Animal> animals, int k) {
        if (animals.size() < k || k <= 0) {
            throw new IllegalArgumentException();
        }
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .limit(k)
            .min(Comparator.comparing(Animal::age)).orElse(null);
    }

    public static Optional<Animal> theHeaviestUnderKofHeight(List<Animal> animals, int k) {
        return animals
            .stream()
            .filter(x -> x.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer getQuantityOfPaws(List<Animal> animals) {
        return animals
            .stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> getAnimalsWhichAgeNotEqualsPaws(List<Animal> animals) {
        return animals
            .stream()
            .filter(x -> x.age() != x.paws()).toList();
    }

    public static List<Animal> getAnimalsWhichCanBiteAndUponHundred(List<Animal> animals) {
        return animals
            .stream()
            .filter(Animal::bites)
            .filter(x -> x.height() > HUNDRED_CENTIMETERS)
            .toList();
    }

    public static Integer getQuantityAnimalsWhichWeighIsBiggerThanHeight(List<Animal> animals) {
        return Math.toIntExact(animals
            .stream()
            .filter(x -> x.weight() > x.height())
            .count());
    }

    public static List<Animal> getAnimalsWithThreeAndMoreWordsName(List<Animal> animals) {
        return animals
            .stream()
            .filter(x -> x.name().split(" ").length > 2)
            .toList();
    }

    public static boolean isThereADogWithHeightUponKcm(List<Animal> animals, int k) {
        return animals
            .stream()
            .anyMatch(x -> x.type() == Animal.Type.DOG && x.height() > k);
    }

    public static Map<Animal.Type, Integer> getSumWeight(List<Animal> animals, int k, int l) {
        return animals
            .stream()
            .filter(x -> x.age() >= k && x.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortedList(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .toList();
    }

    public static boolean doSpidersBiteMoreOften(List<Animal> animals) {
        int spiderBitesCount = (int) animals
            .stream()
            .filter(x -> x.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .count();
        int dogBitesCount = (int) animals
            .stream()
            .filter(x -> x.type() == Animal.Type.DOG)
            .filter(Animal::bites)
            .count();
        return spiderBitesCount > dogBitesCount;
    }

    @SafeVarargs
    public static Animal findTheHeaviestFish(List<Animal>... animals1) {
        var stream = animals1[0]
            .stream()
            .filter(x -> x.type() == Animal.Type.FISH);
        for (int i = 1; i < animals1.length; i++) {
            stream = Stream.concat(stream, animals1[i]
                .stream()
                .filter(x -> x.type() == Animal.Type.FISH));
        }
        return stream
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> getMapOfErrors(List<Animal> animals) {
        return animals
            .stream()
            .filter(x -> !AnimalValidation.resultCheck(x).isEmpty())
            .collect(Collectors.toMap(Animal::name, AnimalValidation::resultCheck
            ));
    }

    public static Map<String, String> getMapOfErrorsString(List<Animal> animals) {
        return animals
            .stream()
            .filter(x -> !AnimalValidation.resultCheck(x).isEmpty())
            .collect(Collectors.toMap(Animal::name, AnimalValidation::stringResultCheck
            ));
    }

    private Tasks() {
    }
}
