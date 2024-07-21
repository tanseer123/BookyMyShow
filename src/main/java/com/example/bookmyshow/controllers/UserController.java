package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.dtos.SignUpRequestDto;
import com.example.bookmyshow.dtos.SignUpResponseDto;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){
        SignUpResponseDto responseDto = new SignUpResponseDto();


        try{
           User user= userService.signUp(signUpRequestDto.getName(),
                   signUpRequestDto.getEmail(),
                   signUpRequestDto.getPassword()
           );
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
           responseDto.setUser(user);




        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);

        }
        return responseDto;

    }
}
