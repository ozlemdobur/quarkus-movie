package org.gs.movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieView movieEntityToMovieView(MovieEntity movieEntity);
   // MovieEntity movieViewToMovieEntity(MovieView movieView);
}
