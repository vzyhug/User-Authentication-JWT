package com.example.day1_connectdb.service;

import com.example.day1_connectdb.dto.request.UserCreationRequest;
import com.example.day1_connectdb.dto.request.UserUpdateQuest;
import com.example.day1_connectdb.dto.resonse.UserResponse;
import com.example.day1_connectdb.entity.User;
import com.example.day1_connectdb.exception.APIErrorCode;
import com.example.day1_connectdb.exception.AppException;
import com.example.day1_connectdb.mapper.UserMapper;
import com.example.day1_connectdb.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    //@Autowired
    UserRepository userRepository;
    //@Autowired
    UserMapper userMapper;
    public User createRequest(UserCreationRequest request) {


        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(APIErrorCode.USER_EXISTED);
        }

//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setBirthDate(request.getBirthDate());
//        User user = userMapper.toUser(request);
        User user = userMapper.toUser(request);
        // encrypt password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(APIErrorCode.NOTFOUND)));
    }
    public UserResponse updateUser(String id, UserUpdateQuest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(APIErrorCode.NOTFOUND));
        userMapper.updateUser(user, request);
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setBirthDate(request.getBirthDate());
        if(user.getPassword().length() <= 2) {
            throw new AppException(APIErrorCode.PASSWORD_INVALID);
        }
        return userMapper.toUserResponse(user);
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
