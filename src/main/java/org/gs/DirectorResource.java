package org.gs;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;

@Path("/director")
public class DirectorResource {

    @Inject
    DirectorRepository repository;

    @POST
    @Transactional
    public Response create(Director director){
        repository.persist(director);
        if(repository.isPersistent(director)){
            return Response.created(URI.create("/movies/"+ director.getId())).entity(director).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

}
