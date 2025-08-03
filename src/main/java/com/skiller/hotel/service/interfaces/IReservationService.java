package com.skiller.hotel.service.interfaces;

import com.skiller.hotel.dto.ReservationDTO;
import com.skiller.hotel.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IReservationService extends ICRUDService<Reservation, Integer>{
    Page<Reservation> listPage(Pageable pageable);
    Reservation create(ReservationDTO dto) throws Exception;
    Reservation update(Integer id, ReservationDTO dto) throws Exception;
}
