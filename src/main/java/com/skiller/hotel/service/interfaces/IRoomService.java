package com.skiller.hotel.service.interfaces;

import com.skiller.hotel.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomService extends ICRUDService<Room, Integer>{
    Page<Room> listPage(Pageable pageable);
}
