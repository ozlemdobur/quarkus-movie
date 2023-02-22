package org.gs.movie;

import org.gs.director.DirectorEntity;
import org.gs.director.DirectorView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieView movieEntityToMovieView(MovieEntity movieEntity);
    List<MovieView> movieEntityToMovieViews(List<MovieEntity> movieEntity);
    MovieEntity movieViewToMovieEntity(MovieView movieView);

    DirectorEntity toEntity(DirectorView directorView);
}
