package com.example.bookmyshow.models;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String name;
    private int rowNum;
    private int colNum;

    @ManyToOne
    private SeatType seatType;
    //private Status status; -- status should not be part of seat, it should be part of Show + Seat



}
