package com.skiller.hotel.config;

import com.skiller.hotel.dto.ReservationDTO;
import com.skiller.hotel.model.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
