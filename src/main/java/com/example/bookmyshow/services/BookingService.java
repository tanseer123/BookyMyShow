package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Booking;

import java.util.List;

public interface BookingService {
   public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds);
}
