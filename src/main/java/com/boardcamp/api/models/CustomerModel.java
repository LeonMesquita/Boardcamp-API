
package com.boardcamp.api.models;
import com.boardcamp.api.dtos.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerModel {
    public CustomerModel(CustomerDTO dto) {
        this.name = dto.getName();
        this.cpf = dto.getCpf();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<RentalModel> rentals;

}
