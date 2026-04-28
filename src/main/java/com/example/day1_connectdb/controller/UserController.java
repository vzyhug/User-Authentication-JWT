package com.example.day1_connectdb.controller;
import com.example.day1_connectdb.dto.resonse.APIResponse;
import com.example.day1_connectdb.dto.request.UserCreationRequest;
import com.example.day1_connectdb.dto.request.UserUpdateQuest;
import com.example.day1_connectdb.dto.resonse.UserResponse;
import com.example.day1_connectdb.entity.User;
import com.example.day1_connectdb.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    //@Autowired
    UserService userService;
    @PostMapping("/users")
    APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        APIResponse<User> response = new APIResponse<>();
//        if(response.isSuccess()==true) {
//            response.setMessage("User created successfully");
//        }
        response.setResult(userService.createRequest(request));
        return response;
    }
    @GetMapping("/users")
    List<User> getUsers() {

        return userService.getUsers();
    }
    @GetMapping("/{userid}")
    UserResponse getUser(@PathVariable("userid") String userid) {
        return userService.getUser(userid);
    }
    @PutMapping("{userid}")
    UserResponse updateUser(@PathVariable String userid,@RequestBody UserUpdateQuest request){
        return userService.updateUser(userid,request);
    }
    @DeleteMapping("{userid}")
    String deleteUser(@PathVariable String userid) {
        userService.deleteUser(userid);
        return "User with id " + userid + " has been deleted.";
    }
}
