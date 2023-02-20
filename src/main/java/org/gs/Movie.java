package org.gs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Movie {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }


    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Movie)){
            return false;
        }
        Movie other = (Movie) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode(){
        return 31;
    }

    /*    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }*/
}
