package com.example.notes.mapper;

import com.example.notes.domain.User;
import com.example.notes.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);

    Iterable<UserDto> map(Iterable<User> users);
}
