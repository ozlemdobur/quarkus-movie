package org.gs.director;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DirectorMapper {

    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    DirectorView directorEntityToDirectorView(DirectorEntity directorEntity);
    DirectorEntity directorViewToDirectorEntity(DirectorView directorView);
}
