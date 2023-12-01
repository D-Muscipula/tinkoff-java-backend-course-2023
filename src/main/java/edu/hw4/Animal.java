package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    static final int CAT_AND_DOGS_QUANTITY_OF_PAWS = 4;
    static final int BIRD_QUANTITY_OF_PAWS = 2;
    static final int FISH_QUANTITY_OF_PAWS = 0;
    static final int SPIDER_QUANTITY_OF_PAWS = 8;

     public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CAT_AND_DOGS_QUANTITY_OF_PAWS;
            case BIRD -> BIRD_QUANTITY_OF_PAWS;
            case FISH -> FISH_QUANTITY_OF_PAWS;
            case SPIDER -> SPIDER_QUANTITY_OF_PAWS;
        };
    }
}
