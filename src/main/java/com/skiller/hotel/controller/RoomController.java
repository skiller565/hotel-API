package com.skiller.hotel.controller;

import com.skiller.hotel.dto.RoomDTO;
import com.skiller.hotel.model.Room;
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
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final IRoomService service;

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll() throws Exception{
        List<RoomDTO> list = mapperUtil.mapList(service.findAll(), RoomDTO.class);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable("id") Integer id) throws Exception{
        RoomDTO obj = mapperUtil.map(service.findById(id), RoomDTO.class);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RoomDTO dto) throws Exception{
        Room obj = service.save(mapperUtil.map(dto, Room.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@Valid @RequestBody RoomDTO dto, @PathVariable("id") Integer id) throws Exception{
        Room room = service.findById(id);
        dto.setId(room.getId());
        Room obj = service.update(mapperUtil.map(dto, Room.class), id);
        return ResponseEntity.ok().body(mapperUtil.map(obj, RoomDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Room>> listPage(Pageable pageable) throws Exception{
        Page<Room> page = service.listPage(pageable);

        return ResponseEntity.ok(page);
    }
}
