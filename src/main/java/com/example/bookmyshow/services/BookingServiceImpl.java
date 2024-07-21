package com.example.bookmyshow.services;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.BookingRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculationService priceCalculationService;
    private BookingRepository bookingRepository;
    //injecting object

    BookingServiceImpl(UserRepository userRepository,
                       ShowRepository showRepository,
                       ShowSeatRepository showSeatRepository,
                       PriceCalculationService priceCalculationService,
                       BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculationService = priceCalculationService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    //No two classes should contact each other directly Dependency Inversion rule, should contact via interface
    //core framework of spring in Dependency Injection,  create objects of all the classes dependent on ech other use @service
    // spring will create auto at the time of build or compile time store it in container(application context)
    //Component,Controller all are same, use @service in service classes
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds) {

        /*
        -> Get the use from user id from the database
        -> Get the show details from should from the data base
        -> Show seat details from showseatid,
        -> Check if all the seats are available or not
        -> If not available throw an exception
        -> else, change the showSeat status to BLOCKED
        -> save the changes to db
        -> create the booking object with pending status and save to db
        -> move to payment
        -> Transactional, complete methode will run inside the lock
        all ACID properties will be applicable
         */
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException(("User with id " + userId + " not found"));
        }
        User user = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
            throw new RuntimeException(("Show with id " + showId + " not found"));
        }
        Show show = showOptional.get();

        //get all the showSeatObjects from showSeatIds from DB
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new RuntimeException("Show seat not available");
            }
        }

        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //Crate Booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeat(showSeats);
        booking.setAmount(priceCalculationService.calculateAmount(showSeats));

        return bookingRepository.save(booking);
    }
}
