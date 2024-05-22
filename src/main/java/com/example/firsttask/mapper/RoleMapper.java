package com.example.firsttask.mapper;


import com.example.firsttask.dao.entity.RoleEntity;
import com.example.firsttask.model.auth.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity mapDtoToEntity(RoleDto roleDto);

    RoleDto mapEntityToDto(RoleEntity roleEntity);
}