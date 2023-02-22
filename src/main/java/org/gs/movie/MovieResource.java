package org.gs.movie;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gs.director.DirectorEntity;
import org.gs.director.DirectorView;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    @Inject
    MovieService movieService;
    @GET
    public Response getAll() {
        List<MovieEntity> movieEntities = movieService.listAll();
        return Response.ok(MovieMapper.INSTANCE.movieEntityToMovieViews(movieEntities)).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return movieService.findByIdOptional(id)
                .map(movie -> MovieMapper.INSTANCE.movieEntityToMovieView(movie))
                .map(movie->Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("title/{title}")
    public Response getByTitle(@PathParam("title") String title){
        return movieService.find(title)
                .map(movie->MovieMapper.INSTANCE.movieEntityToMovieView(movie))
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }
    @POST
    @Transactional
    public Response create(MovieEntity movieEntity){
        MovieEntity createdMovieEntity = movieService.persist(movieEntity);
        MovieView movieView = MovieMapper.INSTANCE.movieEntityToMovieView(createdMovieEntity);
        if(movieService.isPersistent(movieEntity)){
            return Response.created(URI.create("/movies/"+ movieView.getId())).entity(movieView).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("country/{country}")
    public Response getByCountry(@PathParam("country") String country){
        List<MovieEntity> movieEntities = movieService.findByCountry(country);
        if(movieEntities.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(MovieMapper.INSTANCE.movieEntityToMovieViews(movieEntities)).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = movieService.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam("id") Long id, MovieView movieView){

        if (movieView == null || movieView.getId() == null) {
            return Response.status(NOT_FOUND).build();
        }
        Optional<MovieEntity> optionalMovieEntity = movieService.findByIdOptional(id);
        if(!optionalMovieEntity.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!id.equals(movieView.getId())){
            return Response.status(Response.Status.CONFLICT).build();
        }

        MovieEntity movieEntity = optionalMovieEntity.get();
        movieEntity.setDescription(movieView.getDescription());
        movieEntity.setCountry(movieView.getCountry());
        movieEntity.setTitle(movieView.getTitle());
        movieEntity.setDirector(new DirectorEntity(movieView.getDirector().getId(),
                movieView.getDirector().getFirstName(),
                movieView.getDirector().getLastName(),
                movieView.getDirector().getCountry()
                ));

        MovieEntity updatedMovieEntity = movieService.persist(movieEntity);
        return Response.ok(MovieMapper.INSTANCE.movieEntityToMovieView(updatedMovieEntity)).build();

    }

}
