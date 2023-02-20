package org.gs;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @SequenceGenerator(name = "seq_director", sequenceName = "seq_director", allocationSize = 1, initialValue = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    //orphanRemoval = true,
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    List<Movie> movies;

    public Director() {
    }

    public Director(String firstName, String lastName, String country, List<Movie> movies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.movies = movies;
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


    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Director)){
            return false;
        }
        Director other = (Director) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode(){
        return 31;
    }

}
