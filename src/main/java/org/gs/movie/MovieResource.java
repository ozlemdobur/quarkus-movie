package org.gs.movie;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
        List<MovieView> movieEntities = movieService.listAll();
        return Response.ok(movieEntities).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return movieService.findByIdOptional(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("title/{title}")
    public Response getByTitle(@PathParam("title") String title){
        return movieService.find("title", title)
                .singleResultOptional()
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("country/{country}")
    public Response getByCountry(@PathParam("country") String country){
        List<MovieEntity> movieEntities = movieService.findByCountry(country);
        if(movieEntities.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(movieEntities).build();

    }

    @POST
    @Transactional
    public Response create(MovieView movieView){
        movieService.persist(movieView);
        if(movieService.isPersistent(movieView)){
            return Response.created(URI.create("/movies/"+ movieView.getId())).entity(movieView).build();
        }
        return Response.status(BAD_REQUEST).build();
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
    public Response update (@PathParam("id") Long id, MovieEntity movieEntity){
        if (movieEntity == null || movieEntity.getId() == null) {
            return Response.status(NOT_FOUND).build();
        }
        Optional<MovieView> optionalMovie = movieService.findByIdOptional(id);
        if(!optionalMovie.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!id.equals(movieEntity.getId())){
            return Response.status(Response.Status.CONFLICT).build();
        }

        MovieView view = optionalMovie.get();


        view.setDescription(movieEntity.getDescription());
        view.setCountry(movieEntity.getCountry());
        view.setTitle(movieEntity.getTitle());
        //entity.setActors(movie.getActors());

        movieService.persist(view);

        return Response.ok(view).build();

    }

}
