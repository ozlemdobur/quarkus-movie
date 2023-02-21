package org.gs.actor;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gs.actor.Actor;

@ApplicationScoped
public class ActorRepository implements PanacheRepository<Actor> {
}
