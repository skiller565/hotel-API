package com.skiller.hotel.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Integer id;

    @NotBlank
    @Size(min = 1, max = 3, message = "{number.size}")
    private String number;

    @NotBlank
    @Size(min = 5, max = 10, message = "{type.size}")
    private String type;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Boolean available;
}
