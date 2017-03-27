package lv.javaguru.java2.domain;

/**
 * Created by VBarbasins on 27/03/17.
 */
public class ResourceBuilder {

    private ResourceType type;
    private String title;
    private String author;
    private int releaseYear;

    private ResourceBuilder() {}

    public static ResourceBuilder createResource() {
        return new ResourceBuilder();
    }

    public Resource build() {
        Resource resource = new Resource();
        resource.setResourceType(type);
        resource.setTitle(title);
        resource.setAuthor(author);
        resource.setReleaseYear(releaseYear);
        return resource;
    }

    public ResourceBuilder withResourceType(ResourceType type) {
        this.type = type;
        return this;
    }

    public ResourceBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ResourceBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public ResourceBuilder withReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }
}
