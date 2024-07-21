package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.SignUpRequestDto;
import com.example.bookmyshow.dtos.SignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

    //EnableJpaAuditing
    // keeping eye, jpa will keep on auditing all the changes. if new entity is getting created or updated
    private UserController userController;

    public BookMyShowApplication(UserController userController) {
        this.userController = userController;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

        SignUpRequestDto requestDto = new SignUpRequestDto();
        requestDto.setName("def");
        requestDto.setEmail("def@gmail.com");
        requestDto.setPassword("abc#1234");

        SignUpResponseDto responseDto = userController.signUp(requestDto);
        System.out.println(responseDto.getResponseStatus());

    }
}
