package com.boardcamp.api.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RentalDTO {

    @NotBlank(message = "daysRented rented is required")
    private Integer daysRented;

    private LocalDate returnDate;

    @NotBlank(message = "originalPrice rented is required")
    private Integer originalPrice;

    private Double delayFee;

    @NotBlank(message = "rentDate rented is required")
    private LocalDate rentDate;
}
