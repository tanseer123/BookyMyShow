package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    @Override
    ShowSeat save(ShowSeat showSeat);
    // Update+ Insert => upsert

}
