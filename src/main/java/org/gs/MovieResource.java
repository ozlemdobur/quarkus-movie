package org.gs;

import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.annotations.NotFound;

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
    MovieRepository repository;
    @GET
    public Response getAll() {
        List<Movie> movies = repository.listAll();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return repository.findByIdOptional(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("title/{title}")
    public Response getByTitle(@PathParam("title") String title){
        return repository.find("title", title)
                .singleResultOptional()
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("country/{country}")
    public Response getByCountry(@PathParam("country") String country){
        List<Movie> movies = repository.findByCountry(country);
        if(movies.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(movies).build();

    }

    @POST
    @Transactional
    public Response create(Movie movie){
        repository.persist(movie);
        if(repository.isPersistent(movie)){
            return Response.created(URI.create("/movies/"+ movie.getId())).entity(movie).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = repository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update (@PathParam("id") Long id, Movie movie){
        if (movie == null || movie.getId() == null) {
            return Response.status(NOT_FOUND).build();
        }
        Optional<Movie> optionalMovie = repository.findByIdOptional(id);
        if(!optionalMovie.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!id.equals(movie.getId())){
            return Response.status(Response.Status.CONFLICT).build();
        }

        Movie entity = optionalMovie.get();


        entity.setDescription(movie.getDescription());
        entity.setCountry(movie.getCountry());
        entity.setTitle(movie.getTitle());
        //entity.setActors(movie.getActors());

        repository.persist(entity);

        return Response.ok(entity).build();

    }

}
