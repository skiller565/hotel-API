package com.skiller.hotel.service.implement;

import com.skiller.hotel.model.Reservation;
import com.skiller.hotel.repository.IGenericRepository;
import com.skiller.hotel.repository.IReservationRepository;
import com.skiller.hotel.service.interfaces.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IReservationServiceImpl extends CRUDServiceImpl<Reservation, Integer> implements IReservationService {
    private final IReservationRepository roomRepository;

    @Override
    protected IGenericRepository<Reservation, Integer> getRepo() {
        return roomRepository;
    }

    @Override
    public Page<Reservation> listPage(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }
}

