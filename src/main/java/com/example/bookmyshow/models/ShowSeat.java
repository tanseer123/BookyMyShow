package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

   /*
     1            1
    ShowSeat ---  Show
     M            1
     show -x1
     seat - a1,a2,a3
     x1a1,x1a2,x1a3 x2a1,x2a2

      1            1
    ShowSeat --- Seat
      M            1
   */

}
