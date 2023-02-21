package org.gs.movie;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.gs.director.DirectorEntity;

import java.util.Objects;

@Table(name = "movie")
@Entity
public class MovieEntity {

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private DirectorEntity directorEntity;

    @Id
    @GeneratedValue(generator = "seq_movie")
    @SequenceGenerator(name = "seq_movie", sequenceName = "seq_movie", allocationSize = 1, initialValue = 1)
    private Long id;
    @Column(length = 100)
    private String title;
    @Column(length = 200)
    private String description;
    private String country;

    //private List<Actor> actors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DirectorEntity getDirector() {
        return directorEntity;
    }

    public void setDirector(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity movieEntity = (MovieEntity) o;
        return directorEntity.equals(movieEntity.directorEntity) && id.equals(movieEntity.id) && Objects.equals(title, movieEntity.title) && Objects.equals(description, movieEntity.description) && Objects.equals(country, movieEntity.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorEntity, id, title, description, country);
    }


/*    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }*/
}
