package lv.javaguru.java2.services.validate;

import lv.javaguru.java2.domain.ResourceType;

import java.time.LocalDate;

/**
 * Created by user on 11.04.2017.
 */
public class ResourceValidatorImpl implements ResourceValidator{

    @Override
    public void validate(ResourceType type, String title, String author, int releaseYear){
        validateResourceType(type);
        validateTitle(title);
        validateAuthor(author);
        validateReleaseYear(releaseYear);
    }

    private void validateResourceType(ResourceType type){
        if (type == null) {
            throw new IllegalArgumentException("Resource Type must be not empty!");
        }
    }

    private void validateTitle(String title){
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Resource title must be not empty!");
        }
    }

    private void validateAuthor(String author){
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Resource Author must be not empty!");
        }
    }

    private void validateReleaseYear(int releaseYear){
        if (releaseYear < 1 || releaseYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Release Year can not be 0 or greater than current year!");
        }
    }
}
