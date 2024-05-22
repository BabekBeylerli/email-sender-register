package com.example.firsttask.mapper;

import com.example.firsttask.dao.entity.UserEntity;
import com.example.firsttask.model.UserDto;
import com.example.firsttask.model.auth.UserRegisterRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapEntityToDto(UserEntity userEntity);

    UserEntity mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);

    UserEntity mapDtoToEntity(UserDto userDto);

    UserEntity mapDtoToEntity(UserDto userDto, Integer userId);

    List<UserDto> mapEntityToDtos(List<UserEntity> userEntities);
}
