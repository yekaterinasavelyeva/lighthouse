package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by VBarbasins on 27/03/17.
 */
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ResourceID", columnDefinition = "int(11)")
    private Long resourceID;

    @Column(name="ResourceType", columnDefinition = "enum(`BOOK`, `ARTICLE`, `MAGAZINE`, `NEWSPAPER`, `JOURNAL`)")
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @Column(name="Title", columnDefinition = "char(32)")
    private String title;

    @Column(name="Author", columnDefinition = "char(32)")
    private String author;

    @Column(name="ReleaseYear", columnDefinition = "int(7)")
    private int releaseYear;

    public Long getResourceId() {
        return resourceID;
    }

    public void setResourceId(Long resourceId) {
        this.resourceID = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ResourceType getResourceType() {
        return type;
    }

    public void setResourceType(ResourceType resourceType) {
        this.type = resourceType;
    }
}
