package org.gs.movie;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.gs.director.DirectorView;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@ApplicationScoped
public class MovieService {

    private MovieRepository movieRepository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieView> listAll(){
        throw new RuntimeException("Not Implemented");
    }
    public Optional<MovieView> findByIdOptional(Long id){
       Optional<MovieEntity> optionalMovieEntity = movieRepository.findByIdOptional(id);
       if(optionalMovieEntity.isEmpty()){
           return Optional.empty();
       }
       // Map MovieEntity to MovieView
        MovieView movieView = new MovieView();
       movieView.setId(optionalMovieEntity.get().getId());
       movieView.setCountry(optionalMovieEntity.get().getCountry());
       movieView.setDescription(optionalMovieEntity.get().getDescription());
       movieView.setTitle(optionalMovieEntity.get().getTitle());

        DirectorView directorView = new DirectorView();
        directorView.setId(optionalMovieEntity.get().getDirector().getId());

        movieView.setDirector(directorView);
       return Optional.of(movieView);

   }


    public PanacheQuery<Object> find(String title, String title1) {
        throw new RuntimeException("Not Implemented");
    }

    public List<MovieEntity> findByCountry(String country) {
        throw new RuntimeException("Not Implemented");
    }

    public boolean isPersistent(MovieView movieView) {
        throw new RuntimeException("Not Implemented");
    }

    public void persist(MovieView movieView) {
        throw new RuntimeException("Not Implemented");
    }

    public boolean deleteById(Long id) {
        throw new RuntimeException("Not Implemented");
    }
}
