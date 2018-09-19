package lv.javaguru.java2.domain;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * Created by VBarbasins on 27/03/17.
 */
@Entity
@Table(name = "resources")
@TypeDef(
        name = "pgsql_enum_res",
        typeClass = PostgreSQLEnumType.class
)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ResourceID", columnDefinition = "serial")
    private Long resourceID;

    @Column(name="ResourceType", columnDefinition = "resourcetype")
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum_res" )
    private ResourceType type;

    @Column(name="Title", columnDefinition = "bpchar")
    private String title;

    @Column(name="Author", columnDefinition = "bpchar")
    private String author;

    @Column(name="ReleaseYear", columnDefinition = "int4")
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
