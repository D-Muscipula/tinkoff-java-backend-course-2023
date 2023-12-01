package edu.hw4;

public record ValidationError(ErrorType errorType, String errorMessage) {
    public enum ErrorType {
        NAME_ERROR("name"), TYPE_ERROR("type"), SEX_ERROR("sex"), AGE_ERROR("age"),
        HEIGHT_ERROR("height"), WEIGHT_ERROR("weight");

        private final String title;

        ErrorType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
