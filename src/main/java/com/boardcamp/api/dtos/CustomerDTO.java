package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 50)
    private String name;

    @NotBlank(message = "CPF is required")
    @Size(min = 11, max = 11)
    private String cpf;
}
