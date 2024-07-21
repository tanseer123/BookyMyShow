package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToMany
    private List<ShowSeat> showSeat;

    @OneToMany
    private List<Payment> payments;

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}

/*
Booking -- user => M:1

   1             M
Booking ----- ShowSeat => M:M
   M              1
 M pnly if someone canled the same showseat
 */
