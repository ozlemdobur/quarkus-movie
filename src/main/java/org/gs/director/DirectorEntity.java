package org.gs.director;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.gs.movie.MovieEntity;

import java.util.List;
import java.util.Objects;

@Table(name = "director")
@Entity
public class DirectorEntity {
    @Id
    @GeneratedValue(generator = "seq_director")
    @SequenceGenerator(name = "seq_director", sequenceName = "seq_director", allocationSize = 1, initialValue = 1)
    private Long id;

    private String firstName;
    private String lastName;
    private String country;
    //orphanRemoval = true,
    @OneToMany(mappedBy = "directorEntity", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    List<MovieEntity> movieEntities;

    public DirectorEntity() {
    }

    public DirectorEntity(String firstName, String lastName, String country, List<MovieEntity> movieEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.movieEntities = movieEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public List<MovieEntity> getMovies() {
        return movieEntities;
    }
    public void setMovies(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorEntity directorEntity = (DirectorEntity) o;
        return id.equals(directorEntity.id) && Objects.equals(firstName, directorEntity.firstName) && Objects.equals(lastName, directorEntity.lastName) && Objects.equals(country, directorEntity.country) && Objects.equals(movieEntities, directorEntity.movieEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, country, movieEntities);
    }
}
