package org.gs.movie;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.gs.director.DirectorView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@ApplicationScoped
public class MovieService {

/*    @Inject
    MovieMapper movieMapper;*/
    private MovieRepository movieRepository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> listAll() {
        List<MovieEntity> movieEntities = movieRepository.listAll();
        return movieEntities;
    }

    public Optional<MovieEntity> findByIdOptional(Long id) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.findByIdOptional(id);
        if (optionalMovieEntity.isEmpty()) {
            return Optional.empty();
        }

        return optionalMovieEntity;
    }

    public Optional<MovieEntity> find(String title) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.find("title", title).singleResultOptional();
        if (optionalMovieEntity.isEmpty()) {
            return Optional.empty();
        }
        return optionalMovieEntity;
    }

    public List<MovieEntity> findByCountry(String country) {
        List<MovieEntity> movieEntity = movieRepository.findByCountry(country);
        if (movieEntity.isEmpty()) {
            return List.of();
        }
        return movieEntity;
    }

    public boolean isPersistent(MovieEntity movieEntity) {
       //MovieEntity movieEntity = MovieMapper.INSTANCE.movieViewToMovieEntity(movieView);
        return movieRepository.isPersistent(movieEntity);
    }


    @Transactional
    public MovieEntity persist(MovieEntity movieEntity) {
        //MovieEntity movieEntity = MovieMapper.INSTANCE.movieViewToMovieEntity(movieView);
        movieRepository.persist(movieEntity);
        return movieEntity;
    }

    public boolean deleteById(Long id) {
        boolean isDeleted = movieRepository.deleteById(id);
        return isDeleted;
    }
}
