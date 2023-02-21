package org.gs.movie;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<MovieEntity> {
    public List<MovieEntity> findByCountry(String country) {
        return list("SELECT m FROM Movie m WHERE m.country = ?1 ORDER BY id DESC", country );
    }
}
