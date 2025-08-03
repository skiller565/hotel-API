package com.skiller.hotel.service.implement;

import com.skiller.hotel.model.Room;
import com.skiller.hotel.repository.IGenericRepository;
import com.skiller.hotel.repository.IRoomRepository;
import com.skiller.hotel.service.interfaces.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IRoomServiceImpl extends CRUDServiceImpl<Room, Integer> implements IRoomService {
    private final IRoomRepository roomRepository;

    @Override
    protected IGenericRepository<Room, Integer> getRepo() {
        return roomRepository;
    }

    @Override
    public Page<Room> listPage(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public Room updateAvailability(Integer id) throws Exception {
        Room room = this.findById(id);
        room.setAvailable(false);
        return this.save(room);
    }
}
