package com.skiller.hotel.service.implement;

import com.skiller.hotel.dto.ReservationDTO;
import com.skiller.hotel.model.Reservation;
import com.skiller.hotel.model.Room;
import com.skiller.hotel.repository.IGenericRepository;
import com.skiller.hotel.repository.IReservationRepository;
import com.skiller.hotel.service.interfaces.IReservationService;
import com.skiller.hotel.service.interfaces.IRoomService;
import com.skiller.hotel.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class IReservationServiceImpl extends CRUDServiceImpl<Reservation, Integer> implements IReservationService {
    private final IReservationRepository roomRepository;

    private final IRoomService roomService;

    private final MapperUtil mapperUtil;

    @Override
    protected IGenericRepository<Reservation, Integer> getRepo() {
        return roomRepository;
    }

    @Override
    public Page<Reservation> listPage(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Reservation create(ReservationDTO dto) throws Exception {
        Room room = roomService.findById(dto.getRoomId());
        if(!room.getAvailable()){
            throw new IllegalArgumentException("La habitación ya esta reservada");
        }
        checkDateRange(dto.getCheckInDate(), dto.getCheckOutDate());
        roomService.updateAvailability(dto.getRoomId());
        return this.save(mapperUtil.map(dto, Reservation.class));
    }

    @Override
    public Reservation update(Integer id, ReservationDTO dto) throws Exception {
        Reservation reservation = this.findById(id);
        Room room = roomService.findById(dto.getRoomId());
        if(reservation.getRoom().getId() != room.getId() && Boolean.FALSE.equals(  room.getAvailable())){
            throw new IllegalArgumentException("La habitación ya esta reservada");
        }
        checkDateRange(dto.getCheckInDate(), dto.getCheckOutDate());
        roomService.updateAvailability(dto.getRoomId());
        dto.setId(reservation.getId());
        return this.save(mapperUtil.map(dto, Reservation.class));
    }

    public static void checkDateRange(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }

        LocalDate currentDate =  LocalDate.now();
        if (checkInDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la fecha actual");
        }

        if (checkOutDate.isBefore(checkInDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }
}

