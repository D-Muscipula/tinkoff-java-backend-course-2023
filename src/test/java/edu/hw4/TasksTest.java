package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TasksTest {
    @Test
    void getSortedByHeightAnimalListTest() {
        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("1", Animal.Type.SPIDER, Animal.Sex.M, 3, 2, 3, false));
            add(new Animal("2", Animal.Type.SPIDER, Animal.Sex.M, 3, 25, 3, false));
            add(new Animal("3", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 3, false));
            add(new Animal("4", Animal.Type.SPIDER, Animal.Sex.M, 3, 3, 3, false));
            add(new Animal("5", Animal.Type.SPIDER, Animal.Sex.M, 3, 120, 3, false));
            add(new Animal("6", Animal.Type.SPIDER, Animal.Sex.M, 3, 20, 3, false));
        }};
        animalList = Tasks.getSortedByHeightAnimalList(animalList);
        Assertions.assertEquals("3", animalList.getFirst().name());
        Assertions.assertEquals("1", animalList.get(1).name());
        Assertions.assertEquals("4", animalList.get(2).name());
        Assertions.assertEquals("6", animalList.get(3).name());
        Assertions.assertEquals("2", animalList.get(4).name());
        Assertions.assertEquals("5", animalList.getLast().name());

    }

    @Test
    void getKTheHeaviestTest() {
        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("1", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 31, false));
            add(new Animal("2", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 3, false));
            add(new Animal("3", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 36, false));
            add(new Animal("4", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 30, false));
            add(new Animal("5", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 33, false));
            add(new Animal("6", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 2, false));
            add(new Animal("7", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 30, false));
            add(new Animal("8", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 33, false));
            add(new Animal("9", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 2, false));
        }};
        animalList = Tasks.getKTheHeaviest(animalList, 5);
        Assertions.assertEquals(5, animalList.size());
        Assertions.assertEquals("3", animalList.getFirst().name());
        Assertions.assertEquals(33, animalList.get(1).weight());
        Assertions.assertEquals(33, animalList.get(2).weight());
        Assertions.assertEquals(31, animalList.get(3).weight());
        Assertions.assertEquals(30, animalList.getLast().weight());

    }

    @Test
    void getQuantityOfDifferentAnimalsTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 45, 15, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 5, 50, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 11, 700, 235, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 25, 70, 15, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 8, 100, 33, true));
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 45, 15, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 5, 50, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 11, 700, 235, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 25, 70, 15, true));

        var animals = Tasks.getQuantityOfDifferentAnimals(animalList);
        Assertions.assertEquals(4, animals.get(Animal.Type.FISH));
        Assertions.assertEquals(2, animals.get(Animal.Type.BIRD));
        Assertions.assertEquals(2, animals.get(Animal.Type.CAT));
        Assertions.assertEquals(1, animals.get(Animal.Type.SPIDER));
    }

    @Test
    void getAnimalWithTheLongestNameTest() {
        Animal animal = new Animal("Gyarados123", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true);
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(animal);
        animalList.add(new Animal("Meowth1", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true));

        Assertions.assertEquals(animal, Tasks.getAnimalWithTheLongestName(animalList));
    }

    @Test
    @DisplayName("При равенстве возвращается male")
    void getSexWithBiggestQuantityTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        Assertions.assertEquals(Animal.Sex.M, Tasks.getSexWithBiggestQuantity(animalList));

        animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        Assertions.assertEquals(Animal.Sex.F, Tasks.getSexWithBiggestQuantity(animalList));

        animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.M, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.F, 3, 3, 3, true));
        Assertions.assertEquals(Animal.Sex.M, Tasks.getSexWithBiggestQuantity(animalList));
    }

    @Test
    void getTheHeaviestOfEachTypeTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 10, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 15, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 51, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 10, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 10, true));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 10, true));

        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 5, true));
        animalList.add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 10, false));
        animalList.add(new Animal("Gyarados1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 101, true));
        animalList.add(new Animal("Meowth1", Animal.Type.CAT, Animal.Sex.M, 3, 3, 5, true));
        animalList.add(new Animal("Ariados1", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 5, true));
        animalList.add(new Animal("Myghtyena1", Animal.Type.DOG, Animal.Sex.F, 3, 3, 1, true));

        animalList.add(new Animal("Magikarp2", Animal.Type.FISH, Animal.Sex.M, 3, 3, 15, true));
        animalList.add(new Animal("Pidgey2", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 5, false));
        animalList.add(new Animal("Gyarados2", Animal.Type.FISH, Animal.Sex.M, 3, 3, 151, true));
        animalList.add(new Animal("Meowth2", Animal.Type.CAT, Animal.Sex.M, 3, 3, 15, true));
        animalList.add(new Animal("Ariados2", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 15, true));

        var animals = Tasks.getTheHeaviestOfEachType(animalList);
        Assertions.assertEquals(animals.get(Animal.Type.FISH).name(), "Gyarados2");
        Assertions.assertEquals(animals.get(Animal.Type.BIRD).name(), "Pidgey");
        Assertions.assertEquals(animals.get(Animal.Type.CAT).name(), "Meowth2");
        Assertions.assertEquals(animals.get(Animal.Type.DOG).name(), "Myghtyena");
        Assertions.assertEquals(animals.get(Animal.Type.SPIDER).name(), "Ariados2");

    }

    @Test
    @DisplayName("Если K-животное отсутствует, то кидается исключение")
    void getTheKOldestAnimalTest() {
        ArrayList<Animal> animalList = new ArrayList<>() {{
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
            add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.M, 5, 3, 3, false));
            add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 1, 3, 3, true));
            add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.F, 25, 3, 3, true));
            add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 2, 3, 3, true));
            add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.F, 10, 3, 3, true));

        }};

        Assertions.assertEquals("Meowth", Tasks.getTheKOldestAnimal(animalList, 1).name());
        Assertions.assertEquals("Magikarp1", Tasks.getTheKOldestAnimal(animalList, 2).name());
        Assertions.assertEquals("Pidgey", Tasks.getTheKOldestAnimal(animalList, 3).name());
        Assertions.assertEquals("Magikarp", Tasks.getTheKOldestAnimal(animalList, 4).name());
        Assertions.assertEquals("Ariados", Tasks.getTheKOldestAnimal(animalList, 5).name());
        Assertions.assertEquals("Gyarados", Tasks.getTheKOldestAnimal(animalList, 6).name());
        Assertions.assertThrows(IllegalArgumentException.class, () -> Tasks.getTheKOldestAnimal(animalList, 7).name());
    }

    @Test
    void theHeaviestUnderKofHeightTest() {
        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 1, 10, true));
            add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 2, 15, false));
            add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 51, true));
            add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 4, 11, true));
            add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 5, 12, true));
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 6, 16, true));

        }};

        Assertions.assertFalse(Tasks.theHeaviestUnderKofHeight(animalList, 1).isPresent());
        Assertions.assertEquals("Magikarp", Tasks.theHeaviestUnderKofHeight(animalList, 2).orElseThrow().name());
        Assertions.assertEquals("Pidgey", Tasks.theHeaviestUnderKofHeight(animalList, 3).orElseThrow().name());
        Assertions.assertEquals("Gyarados", Tasks.theHeaviestUnderKofHeight(animalList, 4).orElseThrow().name());
        Assertions.assertEquals("Gyarados", Tasks.theHeaviestUnderKofHeight(animalList, 5).orElseThrow().name());
        Assertions.assertEquals("Gyarados", Tasks.theHeaviestUnderKofHeight(animalList, 6).orElseThrow().name());
        Assertions.assertEquals("Gyarados", Tasks.theHeaviestUnderKofHeight(animalList, 7).orElseThrow().name());
    }

    @Test
    void getQuantityOfPawsTest() {
        Assertions.assertEquals(
            4,
            Tasks.getQuantityOfPaws(List.of(new Animal[] {new Animal("", Animal.Type.DOG, null, 0, 0, 0, false)}))
        );
        Assertions.assertEquals(
            4,
            Tasks.getQuantityOfPaws(List.of(new Animal[] {new Animal("", Animal.Type.CAT, null, 0, 0, 0, false)}))
        );
        Assertions.assertEquals(
            2,
            Tasks.getQuantityOfPaws(List.of(new Animal[] {new Animal("", Animal.Type.BIRD, null, 0, 0, 0, false)}))
        );
        Assertions.assertEquals(
            8,
            Tasks.getQuantityOfPaws(List.of(new Animal[] {new Animal("", Animal.Type.SPIDER, null, 0, 0, 0, false)}))
        );
        Assertions.assertEquals(
            0,
            Tasks.getQuantityOfPaws(List.of(new Animal[] {new Animal("", Animal.Type.FISH, null, 0, 0, 0, false)}))
        );

        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 1, 10, true));
            add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 2, 15, false));
            add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 51, true));
            add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 4, 11, true));
            add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 5, 12, true));
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 6, 16, true));

        }};
        Assertions.assertEquals(18, Tasks.getQuantityOfPaws(animalList));
    }

    @Test
    void getAnimalsWhichAgeNotEqualsPawsTest() {
        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 0, 3, 3, true));
            add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
            add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
            add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true));
            add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 8, 3, 3, true));
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 4, 3, 3, true));
        }};

        List<Animal> animals = Tasks.getAnimalsWhichAgeNotEqualsPaws(animalList);
        Assertions.assertEquals("Pidgey", animals.getFirst().name());
        Assertions.assertEquals("Gyarados", animals.get(1).name());
        Assertions.assertEquals("Meowth", animals.get(2).name());
        Assertions.assertEquals(3, animals.size());
    }

    @Test
    void getAnimalsWhichCanBiteAndUponHundredTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 100, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 101, 3, false));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 101, 3, true));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 103, 3, true));
        List<Animal> animals = Tasks.getAnimalsWhichCanBiteAndUponHundred(animalList);
        Assertions.assertEquals("Ariados", animals.getFirst().name());
        Assertions.assertEquals("Myghtyena", animals.getLast().name());
        Assertions.assertEquals(2, animals.size());
    }

    @Test
    void getQuantityAnimalsWhichWeighIsBiggerThanHeight() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 1, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 101, 3, false));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 101, 3, true));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 103, 3, true));
        Assertions.assertEquals(1, Tasks.getQuantityAnimalsWhichWeighIsBiggerThanHeight(animalList));
    }

    @Test
    void getAnimalsWithThreeAndMoreWordsNameTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Pidgey P", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false));
        animalList.add(new Animal("Gyarados K", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, false));
        animalList.add(new Animal("Ariados A A", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Myghtyena M M M M", Animal.Type.DOG, Animal.Sex.F, 3, 3, 3, true));

        List<Animal> animals = Tasks.getAnimalsWithThreeAndMoreWordsName(animalList);
        Assertions.assertEquals("Ariados A A", animals.getFirst().name());
        Assertions.assertEquals("Myghtyena M M M M", animals.getLast().name());
        Assertions.assertEquals(2, animals.size());
    }

    @Test
    void isThereADogWithHeightUponKcmTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 3, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 35, 3, true));
        Assertions.assertFalse(Tasks.isThereADogWithHeightUponKcm(animalList, 5));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 6, 3, true));
        Assertions.assertTrue(Tasks.isThereADogWithHeightUponKcm(animalList, 5));
    }

    @Test
    void getSumWeightTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 2, 3, 10, true));
        animalList.add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 11, false));
        animalList.add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 1, 3, 12, true));
        animalList.add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 13, true));
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 14, true));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));

        animalList.add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 20, true));
        animalList.add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 21, false));
        animalList.add(new Animal("Gyarados1", Animal.Type.FISH, Animal.Sex.M, 5, 3, 22, true));
        animalList.add(new Animal("Meowth1", Animal.Type.CAT, Animal.Sex.M, 3, 3, 23, true));
        animalList.add(new Animal("Ariados1", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 24, true));
        animalList.add(new Animal("Myghtyena1", Animal.Type.DOG, Animal.Sex.F, 3, 3, 25, true));

        animalList.add(new Animal("Magikarp2", Animal.Type.FISH, Animal.Sex.M, 2, 3, 30, true));
        animalList.add(new Animal("Pidgey2", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 31, false));
        animalList.add(new Animal("Gyarados2", Animal.Type.FISH, Animal.Sex.M, 1, 3, 32, true));
        animalList.add(new Animal("Meowth2", Animal.Type.CAT, Animal.Sex.M, 3, 3, 33, true));
        animalList.add(new Animal("Ariados2", Animal.Type.SPIDER, Animal.Sex.F, 1, 3, 34, true));
        animalList.add(new Animal("Myghtyena2", Animal.Type.DOG, Animal.Sex.F, 3, 3, 35, true));
        var animals = Tasks.getSumWeight(animalList, 2, 3);
        Assertions.assertEquals(animals.get(Animal.Type.FISH), (10 + 30) * 3 / 2);
        Assertions.assertEquals(animals.get(Animal.Type.BIRD), (11 + 31) * 3 / 2);
        Assertions.assertEquals(animals.get(Animal.Type.CAT), (13 + 33) * 3 / 2);
        Assertions.assertEquals(animals.get(Animal.Type.SPIDER), (14 + 24) * 2 / 2);
        Assertions.assertEquals(animals.get(Animal.Type.DOG), (15 + 35) * 3 / 2);

    }

    @Test
    void sortedListTest() {
        List<Animal> animalList = new ArrayList<>();
        Animal first = new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 3, true);
        Animal second = new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 3, true);
        Animal third = new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3, false);
        Animal fourth = new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 3, 3, 3, true);
        Animal fifth = new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 2, 3, 3, true);
        Animal sixth = new Animal("A", Animal.Type.FISH, Animal.Sex.F, 3, 3, 3, true);
        Animal seventh = new Animal("B", Animal.Type.FISH, Animal.Sex.F, 3, 3, 3, true);
        Animal eighth = new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 3, true);

        animalList.add(seventh);
        animalList.add(fifth);
        animalList.add(third);
        animalList.add(fourth);
        animalList.add(first);
        animalList.add(eighth);
        animalList.add(second);
        animalList.add(sixth);
        var animals = Tasks.sortedList(animalList);

        Assertions.assertEquals(first, animals.getFirst());
        Assertions.assertEquals(second, animals.get(1));
        Assertions.assertEquals(third, animals.get(2));
        Assertions.assertEquals(fourth, animals.get(3));
        Assertions.assertEquals(fifth, animals.get(4));
        Assertions.assertEquals(sixth, animals.get(5));
        Assertions.assertEquals(seventh, animals.get(6));
        Assertions.assertEquals(eighth, animals.getLast());
        Assertions.assertEquals(8, animals.size());

    }

    @Test
    void doSpidersBiteMoreOftenTest() {
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 14, true));
        animalList.add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));
        Assertions.assertFalse(Tasks.doSpidersBiteMoreOften(animalList));
        animalList.add(new Animal("Ariados1", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 14, true));
        Assertions.assertTrue(Tasks.doSpidersBiteMoreOften(animalList));
        animalList.add(new Animal("Myghtyena1", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, false));
        Assertions.assertTrue(Tasks.doSpidersBiteMoreOften(animalList));
        animalList.add(new Animal("Myghtyena2", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));
        Assertions.assertFalse(Tasks.doSpidersBiteMoreOften(animalList));
    }

    @Test
    void findTheHeaviestFishTest() {
        List<Animal> animalList = new ArrayList<>() {{
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 2, 3, 10, true));
            add(new Animal("Pidgey", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 1112, false));
            add(new Animal("Gyarados", Animal.Type.FISH, Animal.Sex.M, 1, 3, 12, true));
            add(new Animal("Meowth", Animal.Type.CAT, Animal.Sex.M, 3, 3, 13, true));
            add(new Animal("Ariados", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 14, true));
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));

        }};

        List<Animal> animalList1 = new ArrayList<>() {{
            add(new Animal("Magikarp1", Animal.Type.FISH, Animal.Sex.M, 3, 3, 20, true));
            add(new Animal("Pidgey1", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 2112, false));
            add(new Animal("Gyarados1", Animal.Type.FISH, Animal.Sex.M, 5, 3, 220, true));
            add(new Animal("Meowth1", Animal.Type.CAT, Animal.Sex.M, 3, 3, 23, true));
            add(new Animal("Ariados1", Animal.Type.SPIDER, Animal.Sex.F, 3, 3, 24, true));
            add(new Animal("Myghtyena1", Animal.Type.DOG, Animal.Sex.F, 3, 3, 25, true));

        }};

        List<Animal> animalList2 = new ArrayList<>() {{
            add(new Animal("Magikarp2", Animal.Type.FISH, Animal.Sex.M, 2, 3, 30, true));
            add(new Animal("Pidgey2", Animal.Type.BIRD, Animal.Sex.F, 3, 3, 3112, false));
            add(new Animal("Gyarados2", Animal.Type.FISH, Animal.Sex.M, 1, 3, 32, true));
            add(new Animal("Meowth2", Animal.Type.CAT, Animal.Sex.M, 3, 3, 33, true));
            add(new Animal("Ariados2", Animal.Type.SPIDER, Animal.Sex.F, 1, 3, 34, true));
            add(new Animal("Myghtyena2", Animal.Type.DOG, Animal.Sex.F, 3, 3, 35, true));

        }};
        Assertions.assertEquals("Gyarados1", Tasks.findTheHeaviestFish(animalList, animalList1, animalList2).name());
    }

    @Test
    void singleErrorsTest() {
        Animal errorOfNature = new Animal("", null, null, -1, 0, 0, true);
        Animal errorOfNature1 = new Animal("a", null, null, -1, -1, -1, true);
        Animal errorOfNature2 = new Animal("abcxcb", null, null, -1, -1, -1, true);

        Assertions.assertEquals(
            ValidationError.ErrorType.NAME_ERROR,
            Objects.requireNonNull(AnimalValidation.checkName(errorOfNature)).errorType()
        );
        Assertions.assertEquals(
            "Отсутствует имя",
            Objects.requireNonNull(AnimalValidation.checkName(errorOfNature)).errorMessage()
        );
        Assertions.assertEquals(
            ValidationError.ErrorType.NAME_ERROR,
            Objects.requireNonNull(AnimalValidation.checkName(errorOfNature)).errorType()
        );
        Assertions.assertEquals(
            "Слишком короткое имя",
            Objects.requireNonNull(AnimalValidation.checkName(errorOfNature1)).errorMessage()
        );
        Assertions.assertEquals(
            "Имя пишется с большой буквы",
            Objects.requireNonNull(AnimalValidation.checkName(errorOfNature2)).errorMessage()
        );

        Assertions.assertEquals(
            ValidationError.ErrorType.TYPE_ERROR,
            Objects.requireNonNull(AnimalValidation.checkType(errorOfNature)).errorType()
        );

        Assertions.assertEquals(
            ValidationError.ErrorType.SEX_ERROR,
            Objects.requireNonNull(AnimalValidation.checkSex(errorOfNature)).errorType()
        );

        Assertions.assertEquals(
            ValidationError.ErrorType.AGE_ERROR,
            Objects.requireNonNull(AnimalValidation.checkAge(errorOfNature)).errorType()
        );

        Assertions.assertEquals(
            ValidationError.ErrorType.HEIGHT_ERROR,
            Objects.requireNonNull(AnimalValidation.checkHeight(errorOfNature)).errorType()
        );

        Assertions.assertEquals(
            ValidationError.ErrorType.WEIGHT_ERROR,
            Objects.requireNonNull(AnimalValidation.checkWeight(errorOfNature)).errorType()
        );
    }

    @Test
    void getMapOfErrorsTest() {
        Animal errorOfNature = new Animal("", null, null, -1, 0, 0, true);
        Animal errorOfNature1 = new Animal("Aadfs", null, Animal.Sex.M, 1, 1, 1, true);
        Animal errorOfNature2 = new Animal("abcxcb", Animal.Type.FISH, Animal.Sex.F, 1, 1, 1, true);

        List<Animal> animalList = new ArrayList<>() {{
            add(errorOfNature);
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 2, 3, 10, true));
            add(errorOfNature1);
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));
            add(errorOfNature2);
        }};

        Map<String, Set<ValidationError>> map = Tasks.getMapOfErrors(animalList);
        Assertions.assertEquals(3, map.size());

        Set<ValidationError> emptyErrors = map.get("");
        Assertions.assertEquals(6, emptyErrors.size());
        ArrayList<ValidationError> errorsList = new ArrayList<>(emptyErrors);
        Assertions.assertEquals(ValidationError.ErrorType.NAME_ERROR, errorsList.getFirst().errorType());
        Assertions.assertEquals(ValidationError.ErrorType.TYPE_ERROR, errorsList.get(1).errorType());
        Assertions.assertEquals(ValidationError.ErrorType.SEX_ERROR, errorsList.get(2).errorType());
        Assertions.assertEquals(ValidationError.ErrorType.AGE_ERROR, errorsList.get(3).errorType());
        Assertions.assertEquals(ValidationError.ErrorType.HEIGHT_ERROR, errorsList.get(4).errorType());
        Assertions.assertEquals(ValidationError.ErrorType.WEIGHT_ERROR, errorsList.get(5).errorType());

        Set<ValidationError> errorsOfAadfs = map.get("Aadfs");
        Assertions.assertEquals(1, errorsOfAadfs.size());
        ArrayList<ValidationError> errorsList1 = new ArrayList<>(errorsOfAadfs);
        Assertions.assertEquals(ValidationError.ErrorType.TYPE_ERROR, errorsList1.getFirst().errorType());

        Set<ValidationError> errorsOfabcxcb = map.get("abcxcb");
        Assertions.assertEquals(1, errorsOfabcxcb.size());
        ArrayList<ValidationError> errorsList2 = new ArrayList<>(errorsOfabcxcb);
        Assertions.assertEquals(ValidationError.ErrorType.NAME_ERROR, errorsList2.getFirst().errorType());
    }

    @Test
    void getMapOfErrorsStringTest() {
        Animal errorOfNature = new Animal("", null, null, -1, 0, 0, true);
        Animal errorOfNature1 = new Animal("Aadfs", null, Animal.Sex.M, 1, 1, 1, true);
        Animal errorOfNature2 = new Animal("abcxcb", Animal.Type.FISH, Animal.Sex.F, 1, 1, 1, true);

        List<Animal> animalList = new ArrayList<>() {{
            add(errorOfNature);
            add(new Animal("Magikarp", Animal.Type.FISH, Animal.Sex.M, 2, 3, 10, true));
            add(errorOfNature1);
            add(new Animal("Myghtyena", Animal.Type.DOG, Animal.Sex.F, 3, 3, 15, true));
            add(errorOfNature2);
        }};

        Map<String, String> map = Tasks.getMapOfErrorsString(animalList);
        Assertions.assertEquals(3, map.size());

        String emptyErrors = map.get("");
        String emptyErrorsString = "Ошибки в полях: name(Отсутствует имя) type(Тип не определен) sex" +
            "(Пол не определен) age(Отрицательный возраст) height(Нулевой рост) weight(Нулевой вес)";
        Assertions.assertEquals(emptyErrorsString, emptyErrors);

        String errorsOfAadfs = map.get("Aadfs");
        String errorsOfAadfsString = "Ошибки в полях: type(Тип не определен)";
        Assertions.assertEquals(errorsOfAadfsString, errorsOfAadfs);

        String errorsOfabcxcb = map.get("abcxcb");
        String errorsOfabcxcbString = "Ошибки в полях: name(Имя пишется с большой буквы)";
        Assertions.assertEquals(errorsOfabcxcbString, errorsOfabcxcb);
    }
}
