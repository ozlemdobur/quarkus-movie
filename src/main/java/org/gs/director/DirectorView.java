package org.gs.director;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectorView {

    private Long id;
    private String firstName;
    private String lastName;
    private String country;


    public DirectorView() {
    }

    public DirectorView(Long id) {
        this.id = id;
    }

    public DirectorView(Long id, String firstName, String lastName, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
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
}
