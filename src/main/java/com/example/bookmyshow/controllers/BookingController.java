package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.CreateBookingRequestDto;
import com.example.bookmyshow.dtos.CreateBookingResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@Controller
public class BookingController {
    private BookingService bookingService;
    //No two classes should contact each other directly Dependency Inversion rule, should contact via interface
    //core framework of spring in Dependency Injection,  create objects of all the classes dependent on ech other use @service
    // spring will create auto at the time of build or compile time store it in container(application context)
    //Component,Controller all are same, use @service in service package classes
    //use @Controller in controller package

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto){
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();
        try {
            Booking booking = bookingService.createBooking(requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIds()
            );
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setBooking(booking);

        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;

    }
}
