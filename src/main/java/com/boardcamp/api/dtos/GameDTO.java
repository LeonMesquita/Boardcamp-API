

package com.boardcamp.api.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class GameDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;

    private String image;

    @NotBlank(message = "Stock is required")
    private Integer stockTotal;

    @NotBlank(message = "Price is required")
    private Double pricePerDay;
}
