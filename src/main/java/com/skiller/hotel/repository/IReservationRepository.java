package com.skiller.hotel.repository;

import com.skiller.hotel.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends IGenericRepository<Reservation, Integer> {
}
