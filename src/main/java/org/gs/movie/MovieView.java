package org.gs.movie;

import org.gs.director.DirectorView;

public class MovieView {

    private Long id;
    private String title;
    private String description;
    private String country;
    private DirectorView director;

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

    public DirectorView getDirector() {
        return director;
    }

    public void setDirector(DirectorView director) {
        this.director = director;
    }
}
