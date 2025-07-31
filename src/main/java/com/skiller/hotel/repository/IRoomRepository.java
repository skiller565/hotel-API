package com.skiller.hotel.repository;

import com.skiller.hotel.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends IGenericRepository<Room, Integer> {
}
