package com.example.day1_connectdb.mapper;
import com.example.day1_connectdb.dto.request.UserUpdateQuest;
import com.example.day1_connectdb.dto.resonse.UserResponse;
import com.example.day1_connectdb.entity.User;

import com.example.day1_connectdb.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateQuest request);
}
