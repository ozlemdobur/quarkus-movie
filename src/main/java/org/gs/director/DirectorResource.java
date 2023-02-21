package org.gs.director;

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
    public Response create(DirectorEntity directorEntity){
        repository.persist(directorEntity);
        if(repository.isPersistent(directorEntity)){
            return Response.created(URI.create("/movies/"+ directorEntity.getId())).entity(directorEntity).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

}
