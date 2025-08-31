package com.skiller.hotel.controller;

import com.skiller.hotel.dto.ReservationDTO;
import com.skiller.hotel.model.Reservation;
import com.skiller.hotel.model.Room;
import com.skiller.hotel.service.interfaces.IReservationService;
import com.skiller.hotel.service.interfaces.IRoomService;
import com.skiller.hotel.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService service;
    private final IRoomService roomService;

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() throws Exception{
        List<ReservationDTO> list = mapperUtil.mapList(service.findAll(), ReservationDTO.class);
        for (ReservationDTO reservation : list) {
            Room room = roomService.findById(reservation.getRoomId());
            reservation.setRoomNumber(room.getNumber());
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ReservationDTO obj = mapperUtil.map(service.findById(id), ReservationDTO.class);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReservationDTO dto) throws Exception{
        Reservation reservation = service.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@Valid @RequestBody ReservationDTO dto, @PathVariable("id") Integer id) throws Exception{
        Reservation reservation = service.update(id,dto);
        return ResponseEntity.ok().body(mapperUtil.map(reservation, ReservationDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Reservation>> listPage(Pageable pageable) throws Exception{
        Page<Reservation> page = service.listPage(pageable);

        return ResponseEntity.ok(page);
    }
}
